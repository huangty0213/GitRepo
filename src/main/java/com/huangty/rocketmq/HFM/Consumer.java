package com.huangty.rocketmq.HFM;

import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.Set;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer helloConsumer = new DefaultMQPushConsumer("HelloConsumer");
        helloConsumer.setNamesrvAddr("39.100.138.218:9876");
        // 先订阅一个topic
        // 第二个参数为过滤器，"*"表示不过滤
        //helloConsumer.subscribe("myTopic003", "*");
        helloConsumer.subscribe("asyncTopic001", "*");
        helloConsumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg:list){
                    System.out.println(new String(msg.getBody()));
                }

                // 默认情况下，消息只会被一个consumer消费，点到点
                // 回复状态给broker进行状态修改
                // ack
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        helloConsumer.start();
        System.out.println("consumer starting...");

//        helloConsumer.shutdown();
    }
}