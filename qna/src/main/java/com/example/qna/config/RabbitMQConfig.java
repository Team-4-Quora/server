package com.example.qna.config;

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
    private static final String ROUTING_QNA_ELASTIC ="routing.QnaElastic";

    private static final String ROUTING_QNA_ELASTIC_TWO ="routing.QnaElasticTwo";

    @Bean
    Queue queueQnaElastic(){
        return new Queue("queue.QnaElastic",false);
    }

    @Bean
    Queue queueQnaElasticTwo(){
        return new Queue("queue.QnaElasticTwo",false);
    }

    @Bean
    DirectExchange exchangeQnaElastic(){
        return new DirectExchange("direct.exchangeQnaElastic");
    }

    @Bean
    DirectExchange exchangeQnaElasticTwo(){
        return new DirectExchange("direct.exchangeQnaElasticTwo");
    }

    @Bean
    Binding bindingQnaElastic(Queue queueQnaElastic, DirectExchange exchangeQnaElastic){
        return BindingBuilder.bind(queueQnaElastic).to(exchangeQnaElastic).with(ROUTING_QNA_ELASTIC);
    }

    @Bean
    Binding bindingQnaElasticTwo(Queue queueQnaElasticTwo, DirectExchange exchangeQnaElasticTwo){
        return BindingBuilder.bind(queueQnaElasticTwo).to(exchangeQnaElasticTwo).with(ROUTING_QNA_ELASTIC_TWO);
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
