package subscribequeue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import simplequeue.Connect;

import java.io.IOException;


public class Producer {
    private final static String exchangeName = "exchange1";

    public static void main(String[] args) throws IOException {
        /*建立连接*/
        Connection connection = Connect.getConnetion();
        /*在链接上创建通道*/
        Channel channel = connection.createChannel();
        // 声明exchange
        channel.exchangeDeclare(exchangeName,"fanout");
        String messages = "This Message Is From Producer!";
        /*消息发送：Producer->channel->exchangeName*/
        channel.basicPublish(exchangeName,"",null,messages.getBytes());
        System.out.println("Send："+messages);
        channel.close();
        connection.close();
    }
}
