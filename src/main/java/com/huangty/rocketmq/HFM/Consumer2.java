package com.huangty.rocketmq.HFM;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer2 {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer helloConsumer = new DefaultMQPushConsumer("ConsumerGroup2");
        helloConsumer.setNamesrvAddr("39.100.138.218:9876");
        // 先订阅一个topic
        // 第二个参数为过滤器，"*"表示不过滤
        helloConsumer.subscribe("myTopic004", "*");
        /**
         * MessageListenerConcurrently: 会并发消费，开启多个线程
         * MessageListenerOrderly:对一个queue开启一个线程，多个queue开启多个线程
         *
         */
//        helloConsumer.setConsumeThreadMax();
//        helloConsumer.setConsumeThreadMin();
        helloConsumer.registerMessageListener(new MessageListenerOrderly() {
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt m:list){
                    System.out.println(new String(m.getBody()) + " Thread:"+ Thread.currentThread().getName());
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        helloConsumer.start();
        System.out.println("consumer starting...");

//        helloConsumer.shutdown();
    }
}