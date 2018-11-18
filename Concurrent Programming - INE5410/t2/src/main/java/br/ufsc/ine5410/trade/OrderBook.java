package br.ufsc.ine5410.trade;

import javax.annotation.Nonnull;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

import static br.ufsc.ine5410.trade.Order.Type.*;

public class OrderBook implements AutoCloseable {
    private final @Nonnull String stockCode;
    private final @Nonnull TransactionProcessor transactionProcessor;
    //private final @Nonnull PriorityQueue<Order> sellOrders, buyOrders;
    private final @Nonnull LinkedBlockingQueue<Order> sellOrders, buyOrders ;
    private boolean closed = false;
    private Lock lock;

    private ExecutorService service = Executors.newCachedThreadPool();

    public OrderBook(@Nonnull String stockCode,
                     @Nonnull TransactionProcessor transactionProcessor) {
        this.stockCode = stockCode;
        this.transactionProcessor = transactionProcessor;

        sellOrders =  new LinkedBlockingQueue<>();
        buyOrders  = new LinkedBlockingQueue<>();


        //        sellOrders = new PriorityQueue<>(100, new Comparator<Order>() {
        //   @Override
        //   public int compare(@Nonnull Order l, @Nonnull Order r) {
        //       return Double.compare(l.getPrice(), r.getPrice());
        //   }
        //});

        // buyOrders = new PriorityQueue<>(100, new Comparator<Order>() {
        //  @Override
        //  public int compare(@Nonnull Order l, @Nonnull Order r) {
        //      return Double.compare(r.getPrice(), l.getPrice());
        //  }
        //});

//        for(Order order : sellOrders) {
//            sellO.add(order);
//        }
//        for(Order order : buyOrders) {
//            buyO.add(order);
//        }
    }

    public synchronized void post(@Nonnull final Order order) {
        if (!order.getStock().equals(stockCode)) {
            String msg = toString() + " cannot process orders for " + order.getStock();
            throw new IllegalArgumentException(msg);
        }
        if (closed) {
            order.notifyCancellation();
            return;
        }


        (order.getType() == BUY ? buyOrders : sellOrders).add(order);

        order.notifyQueued();

        //service.submit(new Runnable() {
        	//@Override
            //public void run() {
            	tryMatch();
              //}
            //});
    }

    private void tryMatch() {  // se usar o service.submit acima, colocar synchronized
        Order sell, buy;

        while ((sell = sellOrders.peek()) != null && (buy = buyOrders.peek()) != null) {
            if (sell.getPrice() <= buy.getPrice()) {
                Transaction trans = new Transaction(sell, buy);
                Order removed = sellOrders.remove();
                //Order removed = sellO.remove();
                assert removed == sell;
                removed = buyOrders.remove();
                //removed = buyO.remove();
                assert removed == buy;

                sell.notifyProcessing();
                buy.notifyProcessing();
                transactionProcessor.process(OrderBook.this, trans);
            } else {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("OrderBook(%s)", stockCode);
    }

    @Override
    public void close()  {
        if (closed){
            return;
        }
        closed = true;
        //any future post() call will be a no-op

        for (Order order : sellOrders){
            order.notifyCancellation();
        }
        sellOrders.clear();
        for (Order order : buyOrders){
            order.notifyCancellation();
        }
        buyOrders.clear();

        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        for (Order order : sellO){
//            order.notifyCancellation();
//        }
//        sellO.clear();
//
//        for (Order order : buyO){
//            order.notifyCancellation();
//        }
//        buyO.clear();
    }
}
