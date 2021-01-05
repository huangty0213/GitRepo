package com.huangty.rocketmq.General;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;

/**
 * 消息发送者
 */
public class Producer {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("HelloGroup");
        // Set names erver address
        producer.setNamesrvAddr("39.100.138.218:9876");
        producer.start();

        Message myTopic001 = new Message("myTopic001", "First topic".getBytes());
        // 同步消息发送
        SendResult send = producer.send(myTopic001);
        System.out.println(send);

        ArrayList<Message> msgs = new ArrayList<Message>();
        msgs.add(new Message("myTopic003", "hello1".getBytes()));
        msgs.add(new Message("myTopic003", "hello2".getBytes()));
        msgs.add(new Message("myTopic003", "hello3".getBytes()));

        SendResult send1 = producer.send(msgs);
        System.out.println(send1);

        producer.shutdown();

    }
}
