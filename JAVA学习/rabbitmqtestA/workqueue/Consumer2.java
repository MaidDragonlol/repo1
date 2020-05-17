package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import simplequeue.Connect;

import java.io.IOException;


public class Consumer2 {
    private final static String queueName = "queue1";

    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = Connect.getConnetion();
        Channel channel = connection.createChannel();
        channel.basicQos(1);// 同一时刻服务器只会发一条消息给消费者
        channel.queueDeclare(queueName,false,false,false,null);
        /*定义消费者,指定消费channel中的数据*/
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        /*监听*/
        channel.basicConsume(queueName,false,queueingConsumer);//true自动确认,false手动确认
        while(true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            //表示使用手动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);//true自动应答，false手动应答
            String message = new String(delivery.getBody());
            System.out.println("Received：" + message);
           /* 停止10ms*/
            Thread.sleep(10);
        }
    }
}
