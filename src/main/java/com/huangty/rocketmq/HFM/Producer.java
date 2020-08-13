package com.huangty.rocketmq.HFM;


import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 事务消息
 */
public class Producer {
    public static void main(String[] args) throws Exception {

        TransactionMQProducer producer= new TransactionMQProducer("TransactionGroup");
        // Set names erver address
        producer.setNamesrvAddr("39.100.138.218:9876");
        // 设置事务消息监听
        producer.setTransactionCheckListener(new TransactionCheckListener() {
            public LocalTransactionState checkLocalTransactionState(MessageExt messageExt) {
                // Broker 回调，检查事务
                System.out.println("=====checkLocalTransactionState=====");
                System.out.println("msg:"+messageExt.toString());
                return LocalTransactionState.UNKNOW;
//                return LocalTransactionState.ROLLBACK_MESSAGE;
//                return LocalTransactionState.UNKNOW;
            }
        });

        producer.start();

        TransactionSendResult sendResult = producer.sendMessageInTransaction(new Message("myTransTopic001", "Transaction topic test".getBytes()), new LocalTransactionExecuter() {
            public LocalTransactionState executeLocalTransactionBranch(Message message, Object o) {
                // 执行本地事务
                System.out.println("=====executeLocalTransactionBranch=====");
                System.out.println("msg:" + message.getBody());
                System.out.println("这里执行本地事务。");
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        }, null);


        System.out.println(sendResult);


//        producer.shutdown();

    }
}
