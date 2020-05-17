package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import simplequeue.Connect;

import java.io.IOException;


public class Producer {
    private final static String queueName = "queue1";

    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = Connect.getConnetion();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
        /*项消息队列发送100条消息*/
        for (int i = 0; i < 100; i++) {
            String message = "message:" + i;
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println("消息：" + message);
            Thread.sleep(10);
        }
        channel.close();
        connection.close();
    }
}
