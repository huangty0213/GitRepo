package com.huangty.rocketmq.General;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer3 {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroup2");
        consumer.setNamesrvAddr("39.100.138.218:9876");

        // TAG Selector 在同一个group中保持一致，不能随便变更
        consumer.subscribe("myTopic003", "TAG-TEST");

        // 集群消费模式，集群中有一个consumer消费即可，每条消息只会消费一次。不保证重复投递会到同一台服务器上。
        // 共有集群与广播两种消费模式，发送给所有订阅过的consumer
        // 广播不会重复投递
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for(MessageExt msg:list){
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("consumer start...");
    }
}
