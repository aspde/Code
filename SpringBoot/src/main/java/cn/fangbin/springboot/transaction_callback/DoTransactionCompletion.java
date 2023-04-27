package cn.fangbin.springboot.transaction_callback;

import org.springframework.transaction.support.TransactionSynchronization;

/**
 * 事务完成后置回调函数定义
 */
public class DoTransactionCompletion implements TransactionSynchronization {

    private Runnable runnable;

    public DoTransactionCompletion(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void afterCompletion(int status) {
        if(status == TransactionSynchronization.STATUS_COMMITTED) {
            this.runnable.run();
        }
    }
}