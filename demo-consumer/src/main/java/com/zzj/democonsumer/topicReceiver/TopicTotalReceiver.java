package com.zzj.democonsumer.topicReceiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.zzj.democonsumer.pojo.User;
import com.zzj.democonsumer.service.UserService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhaozj37918
 * @date 2022年03月02日 11:28
 */
@Component
@RabbitListener(queues = "topic.woman")
public class TopicTotalReceiver implements ChannelAwareMessageListener {
    @Autowired
    private UserService userService;

//    @RabbitHandler
//    public void process(Message message, Channel channel) throws Throwable {
//    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] msg = message.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(msg, User.class);
//            userService.add(user);
//            userService.query(user.getId());
//            userService.queryAll();
            userService.edit(user);
            userService.del(user.getId());
            System.out.println("TopicTotalReceiver消费者收到消息 :" + user.toString());
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }
}