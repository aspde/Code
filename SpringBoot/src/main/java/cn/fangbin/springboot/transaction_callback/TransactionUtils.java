package cn.fangbin.springboot.transaction_callback;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionUtils {

    // 事务后置处理，可以优化大事务提升连接池性能，尽量保证分布式事务一致性
    public static void doAfterTransaction(Runnable runnable) {
        if(TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new DoTransactionCompletion(runnable));
        }
    }

    @Transactional
    public void doTx() {
        System.out.println("start tx");
        TransactionUtils.doAfterTransaction(() -> {
            System.out.println("after commit do..");
        });
        System.out.println("tx do end");
    }
}