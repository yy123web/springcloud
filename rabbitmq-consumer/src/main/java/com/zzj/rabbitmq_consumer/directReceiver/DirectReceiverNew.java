package com.zzj.rabbitmq_consumer.directReceiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhaozj37918
 * @date 2022年03月01日 15:28
 */
@Component
@RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
public class DirectReceiverNew {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("第二个 -DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

}