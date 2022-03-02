package com.zzj.demoprovider.exchange;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaozj37918
 * @date 2022年03月02日 10:09
 */
@Configuration
public class DirectRabbitConfig {
    // 队列名
    @Bean
    public Queue TestDirectQueue() {
        return new Queue("TestDirectQueue", true);
    }

    // Direct交换机
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("TestDirectExchange", true, true);
    }

    // 将队列与交换机绑定
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }
}
