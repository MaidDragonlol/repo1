package simplequeue;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Producer {
    public static void main(String[] args) throws IOException {
        /*建立连接，通过factory将此类连接到vhost*/
        Connection connection = Connect.getConnetion();
        Channel channel = connection.createChannel();//从连接中创建通道
        channel.queueDeclare("queue1", false, false, false, null);//声明队列及参数
        String messages = "This Message Is From Producer!";//用于测试的信息
        channel.basicPublish("", "queue1", null, messages.getBytes());//channel完成发送操作
        System.out.println("Sent：" + messages);
        channel.close();//关闭通道
        connection.close();//关闭连接
    }

}
