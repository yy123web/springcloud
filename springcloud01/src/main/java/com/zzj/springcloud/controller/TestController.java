package com.zzj.springcloud.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
/**
 * @author zhaozj37918
 * @date 2022年02月24日 10:54
 */
public class TestController {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //设置 RabbitMQ 地址
        factory.setHost("localhost");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        Channel channel = conn.createChannel();
        //声明交换器
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);

        String routingKey = "hola1";
        //发布消息
        byte[] messageBodyBytes1 = "quit1".getBytes();
        byte[] messageBodyBytes2 = "quit2".getBytes();
        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes1);

        channel.close();
        conn.close();
    }
}
