package com.huangty.rocketmq.General;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.Random;

public class Producer4 {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("StudentGroup");
        producer.setNamesrvAddr("39.100.138.218:9876");
        producer.start();

        // tag用来过滤消息，消息分组
        //
        ArrayList<Message> list = new ArrayList<Message>();
        for (int i = 0; i < 100; i++) {
            Random age = new Random();
            int age1 = age.nextInt(6)+16;

            Message msg = new Message("myTopic004", "TAG-Student", "KEY-Age", ("Hello"+i+":"+age1).getBytes());
            msg.putUserProperty("age", String.valueOf(age1));
            list.add(msg);
        }

        producer.send(list);
        producer.shutdown();
    }
}
