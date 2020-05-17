package simplequeue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = Connect.getConnetion();
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue1", false, false, false, null);
        /*定义消费者,指定消费channel中的数据*/
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        /*监听(就不用手动关闭channel和连接了）*/
        channel.basicConsume("queue1", true, queueingConsumer);//自动确认true
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("Received：" + message );
        }
    }
}
