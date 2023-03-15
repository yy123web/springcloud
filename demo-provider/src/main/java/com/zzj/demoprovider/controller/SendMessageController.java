package com.zzj.demoprovider.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzj.demoprovider.pojo.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhaozj37918
 * @date 2022年03月02日 10:18
 */
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() throws JsonProcessingException {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPassword("123");
        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(user);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", msg);
        return "发送成功";
    }

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPassword("123");
        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(user);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", msg);
        return "发送成功";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2(@RequestBody User user) throws JsonProcessingException {
//        User user = new User();
//        user.setId(7);
//        user.setName("张三");
//        user.setPassword("123");
        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(user);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", msg);
        return "发送成功";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPassword("123");
        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(user);
        rabbitTemplate.convertAndSend("fanoutExchange", null, msg);
        return "发送成功";
    }
}
