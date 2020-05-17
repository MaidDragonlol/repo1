package simplequeue;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Connect {
    public static Connection getConnetion() throws IOException {
        /*建立连接工厂*/
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");//设置服务地址
        connectionFactory.setPort(5672);//设置AMQP连接端口
        connectionFactory.setUsername("admin");//设置用户名
        connectionFactory.setPassword("admin");//设置密码
        connectionFactory.setVirtualHost("testhost");//设置vhost
        /*通过工厂类建立连接*/
        Connection connection = connectionFactory.newConnection();
        return connection;


    }
}
