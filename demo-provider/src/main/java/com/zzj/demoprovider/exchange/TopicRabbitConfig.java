package com.zzj.demoprovider.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaozj37918
 * @date 2022年03月02日 11:17
 */
@Configuration
public class TopicRabbitConfig {
    @Bean
    public Queue firstQueue() {
        return new Queue("topic.man");
    }
    @Bean
    public Queue secondQueue() {
        return new Queue("topic.woman");
    }
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with("topic.man");
    }
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
