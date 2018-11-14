package br.ufsc.ine5410.trade;

import br.ufsc.ine5410.bank.Account;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class TransactionProcessor implements AutoCloseable {

    public void process(@Nonnull final OrderBook orderBook,
                        @Nonnull final Transaction trans) {
        Order buy = trans.getBuy(), sell = trans.getSell();
        Account fromAcc = buy.getBroker().getAccount(buy.getInvestor());
        Account toAcc = sell.getBroker().getAccount(sell.getInvestor());

        // here there must be a thread created
        // maybe use Runnable ou create a
        // thread and implement Run();


        // handle missing accounts
        if (fromAcc == null) buy.notifyCancellation();
        if (toAcc == null) sell.notifyCancellation();
        if (fromAcc == null || toAcc == null) {
            //retry the order with account
            if (fromAcc != null) orderBook.post(buy);
            if (toAcc != null) orderBook.post(sell);
            return;
        }

        //buyer defines price
        double price = buy.getPrice();
        assert price >= sell.getPrice();

        // transfer the money
        // deadlock resolved as we did in the classroom,
        // locking the one with bigger Id then locking the other.
        if (fromAcc.getId() > toAcc.getId()) {
            fromAcc.getLock().lock();
            toAcc.getLock().lock();
        } else {
            toAcc.getLock().lock();
            fromAcc.getLock().lock();
        }
//cached thread pool
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
    }

    @Override
    public void close()  {
        /* quando chamado, deve garantir que nao existe mais nenhum
        TransactionProcesss rolando.
         */

    }

}
