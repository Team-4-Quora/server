//package com.example.qna.kafka.config;
//
//import com.example.qna.kafka.dto.Pages;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//public class PagesConfig {
//    @Bean
//    public ProducerFactory<String, Pages>
//    producerFactory()
//    {
//        // Create a map of a string
//        // and object
//        Map<String, Object> config
//                = new HashMap<>();
//
//        config.put(
//                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                "localhost:9092");
//
//        config.put(
//                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                StringSerializer.class);
//
//        config.put(
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                JsonSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Pages>
//    kafkaTemplate()
//    {
//        return new KafkaTemplate<>(
//                producerFactory());
//    }
//}
