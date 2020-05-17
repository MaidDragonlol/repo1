package routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import simplequeue.Connect;

import java.io.IOException;

public class Producer {
    private final static String exchangeName = "exchange2";

    public static void main(String[] args) throws IOException {
        /*建立连接*/
        Connection connection = Connect.getConnetion();
        /*在链接上创建通道*/
        Channel channel = connection.createChannel();//从连接中创建通道
        channel.exchangeDeclare(exchangeName, "direct");//设置交换模式
        String message = "删除商品";//用于测试的信息
        channel.basicPublish(exchangeName,"delete",null,message.getBytes());//声明队列及参数
        System.out.println("Send："+message);
        channel.close();//关闭
        connection.close();

    }
}
