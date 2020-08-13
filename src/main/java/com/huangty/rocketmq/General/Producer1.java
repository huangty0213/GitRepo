package com.huangty.rocketmq.General;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer1 {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("DefProGroup");
        producer.setNamesrvAddr("39.100.138.218:9876");
        producer.start();

        Message message = new Message("asyncTopic001", "Asynchronized message test.".getBytes());
        // 发送异步可靠消息
        // 不会阻塞，等待broker确认
        // 采用事件监听方式接受broker返回的确认
        producer.send(message, new SendCallback() {
            public void onSuccess(SendResult sendResult) {
                System.out.println("Send succeed.");
                System.out.println("sendResult:"+sendResult);
            }

            public void onException(Throwable throwable) {
                // 如果发生异常，尝试重连
                // 或调整业务逻辑
                System.out.println("Send exception");
                throwable.printStackTrace();
            }
        });

//        producer.shutdown();
    }
}
