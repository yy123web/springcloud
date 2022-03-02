package com.zzj.democonsumer.topicReceiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzj.democonsumer.pojo.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhaozj37918
 * @date 2022年03月02日 11:27
 */
@Component
@RabbitListener(queues = "topic.man")
public class TopicManReceiver {
    @RabbitHandler
    public void process(String msg) throws JsonProcessingException {
        System.out.println("TopicManReceiver消费者收到消息 :" + msg);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(msg, User.class);
        User user1 = new User();
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        System.out.println(user1);
        System.out.println("TopicManReceiver消费者收到消息 :" + user.toString());
    }
}
