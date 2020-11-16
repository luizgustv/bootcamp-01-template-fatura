package br.com.zup.fatura.configuration;


import br.com.zup.fatura.model.TransacaoCartao;
import br.com.zup.fatura.request.TransacaoCartaoRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    private final KafkaProperties kafkaProperties;

    public KafkaConfiguration(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    public Map<String, Object> consumerConfigurations(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset());

        return properties;
    }

    //consumer
    @Bean
    public ConsumerFactory<String, TransacaoCartaoRequest> transactionConsumerFactory(){

        StringDeserializer stringDeserializer = new StringDeserializer(); //chave

        JsonDeserializer<TransacaoCartaoRequest> jsonDeserializer =
                new JsonDeserializer<>(TransacaoCartaoRequest.class, false); //valor

        return new DefaultKafkaConsumerFactory<>(consumerConfigurations(),
                stringDeserializer, jsonDeserializer);
    }

    //listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransacaoCartaoRequest> kafkaListenerContainerFactory(){

        ConcurrentKafkaListenerContainerFactory<String, TransacaoCartaoRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(transactionConsumerFactory());

        return factory;
    }


}
