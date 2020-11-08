package deudoor.chatserver.service;

import deudoor.chatserver.model.DoorChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, DoorChatMessage> kafkaTemplate;

    public void send(DoorChatMessage message) {
        message.setTimestamp(System.currentTimeMillis());
        logger.info("Send data to Kafka");
        logger.info("  Topic={}", message.getTopic());
        logger.info("  Data={}", message);
        kafkaTemplate.send("lecture", message);
    }
}
