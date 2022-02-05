package com.example.userservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private static final String ROUTING_USER_ELASTIC ="routing.UserElastic";

    @Bean
    Queue queueUserElastic(){
        return new Queue("queue.UserElastic",false);
    }

    @Bean
    DirectExchange exchangeUserElastic(){
        return new DirectExchange("direct.exchangeUserElastic");
    }

    @Bean
    Binding bindingUserElastic(Queue queueUserElastic, DirectExchange exchangeUserElastic){
        return BindingBuilder.bind(queueUserElastic).to(exchangeUserElastic).with(ROUTING_USER_ELASTIC);
    }



    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
