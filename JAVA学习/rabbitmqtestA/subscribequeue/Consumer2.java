package subscribequeue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import simplequeue.Connect;

import java.io.IOException;

public class Consumer2 {
    private final static String queueName1 = "queue2";
    private final static String exchangeName1 = "exchange1";

    public static void main(String[] args) throws IOException, InterruptedException {
        /*建立连接和通道*/
        Connection connection = Connect.getConnetion();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName1, false, false, false, null);
        // 绑定队列到交换机
        channel.queueBind(queueName1, exchangeName1, "");
        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        /*监听队列*/
        channel.basicConsume(queueName1, false, queueingConsumer);
        while(true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("Send："+message);
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }
}
