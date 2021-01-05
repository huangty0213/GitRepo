package com.huangty.rocketmq.HFM;


import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 事务消息
 */
public class Producer1 {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer= new DefaultMQProducer("DefaultGroup");
        // Set names erver address
        producer.setNamesrvAddr("39.100.138.218:9876");
        // 发送错误重试次数
        producer.setRetryTimesWhenSendFailed(3);
        producer.setRetryAnotherBrokerWhenNotStoreOK(true);
//        producer.setSendMsgTimeout(3);
        producer.start();


        Message message = new Message("myTopic004", "TAG-TEST", "KEY-RETRY", "hello".getBytes());
        SendResult send = producer.send(message);
        System.out.println(send);


//        producer.shutdown();

    }
}
