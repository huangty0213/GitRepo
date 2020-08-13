package com.huangty.rocketmq.HFM;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 事务消息
 */
public class Producer2 {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer= new DefaultMQProducer("DefaultGroup");
        // Set names erver address
        producer.setNamesrvAddr("39.100.138.218:9876");
        // 发送错误重试次数
        producer.setRetryTimesWhenSendFailed(3);

        producer.start();

        for (int i = 0; i < 20; i++) {
            Message message = new Message("myTopic004", "TAG-TEST", "KEY-ORDER", ("hello"+i).getBytes());
//            producer.send(message, new SelectMessageQueueByHash(), 1);
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    MessageQueue messageQueue = list.get(0);
                    return messageQueue;
                }
            }, 1, 2000);

            System.out.println(sendResult);

        }

//        producer.shutdown();

    }
}
