package br.ufsc.ine5410.trade;

import br.ufsc.ine5410.bank.Account;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class TransactionProcessor implements AutoCloseable {

    private ExecutorService service = Executors.newCachedThreadPool();
    private boolean closed = false;

    public void process(@Nonnull final OrderBook orderBook,
                        @Nonnull final Transaction trans) {
        final Order buy = trans.getBuy(), sell = trans.getSell();
        final Account fromAcc = buy.getBroker().getAccount(buy.getInvestor());
        final Account toAcc = sell.getBroker().getAccount(sell.getInvestor());


        if(closed) {
            buy.notifyCancellation();
            sell.notifyCancellation();
            return;
        }


        // handle missing accounts
        if (fromAcc == null) buy.notifyCancellation();
        if (toAcc == null) sell.notifyCancellation();
        if (fromAcc == null || toAcc == null) {
            //retry the order with account
            if (fromAcc != null) orderBook.post(buy);
            if (toAcc != null) orderBook.post(sell);
            return;
        }

        try {
            //cached thread pool
            service.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        //buyer defines price
                        double price = buy.getPrice();
                        assert price >= sell.getPrice();


                        // deadlock resolved as we did in the classroom,
                        // locking the one with bigger Id then locking the other.
                        if (fromAcc.getId() > toAcc.getId()) {
                            fromAcc.getLock().lock();
                            toAcc.getLock().lock();
                        } else {
                            toAcc.getLock().lock();
                            fromAcc.getLock().lock();
                        }

                        // transfer the money
                        try {
                            boolean ok = fromAcc.withdraw(price);
                            if (!ok) {
                                orderBook.post(sell);     //try later
                                buy.notifyCancellation(); //fail
                            } else {
                                try {
                                    //throws if client does not have the stock
                                    toAcc.removeStock(buy.getStock());

                                    toAcc.deposit(price);
                                    fromAcc.addStock(buy.getStock());
                                    sell.notifyExecution(); //done!
                                    buy.notifyExecution();  //done!
                                } catch (NoSuchStockException e) {
                                    fromAcc.deposit(price);
                                    sell.notifyCancellation(); //fail
                                    orderBook.post(buy);       //try later
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        toAcc.getLock().unlock();
                        fromAcc.getLock().unlock();
                    } catch (RejectedExecutionException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            trans.getBuy().notifyCancellation();
            trans.getSell().notifyCancellation();
            return;
        }
    }

    @Override
    public void close() {
        if(closed) {
            return;
        }
        closed = true;
        service.shutdown();
        try{
            //shutdown should wait for all tasks to end, before terminating the executor
            service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
