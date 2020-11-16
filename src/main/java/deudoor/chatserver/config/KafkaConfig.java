package deudoor.chatserver.config;

import com.google.common.collect.ImmutableMap;
import deudoor.chatserver.model.DoorChatMessage;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${server.kafka.host}")
    private String kafkaHost;

    @Value("${server.kafka.group-id}")
    private String kafkaGroupId;

    @Bean
    public ProducerFactory<String, DoorChatMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs(), null, new JsonSerializer<DoorChatMessage>());
    }

    @Bean
    public KafkaTemplate<String, DoorChatMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        return ImmutableMap.<String, Object>builder()
                .put("bootstrap.servers", kafkaHost)
                .put("key.serializer", IntegerSerializer.class)
                .put("value.serializer", JsonSerializer.class)
                .put("group.id", kafkaGroupId)
                .build();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DoorChatMessage> kafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, DoorChatMessage>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, DoorChatMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), null, new JsonDeserializer<>(DoorChatMessage.class));
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        return ImmutableMap.<String, Object>builder()
                .put("bootstrap.servers", kafkaHost)
                .put("key.deserializer", IntegerDeserializer.class)
                .put("value.deserializer", JsonDeserializer.class)
                .put("group.id", kafkaGroupId)
                .build();
    }
}
