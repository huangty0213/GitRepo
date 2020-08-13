package com.huangty.rocketmq.General;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class Producer2 {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("DefProGroup");
        producer.setNamesrvAddr("39.100.138.218:9876");
        producer.start();

        // tag用来过滤消息，消息分组
        //
        Message message = new Message("asyncTopic001", "Asynchronized message test.".getBytes());
        // 发送异步消息，不可靠
        // 单向消息，发送效率最高
        // 消息发布三种方式：同步消息（等返回）、异步消息（有回调）、单向消息
        producer.sendOneway(message);
        
//        producer.shutdown();
    }
}
