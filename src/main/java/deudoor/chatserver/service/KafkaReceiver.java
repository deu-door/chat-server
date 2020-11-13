package deudoor.chatserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import deudoor.chatserver.model.DoorChatMessage;
import deudoor.chatserver.repository.DoorChatMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class KafkaReceiver {

    private static final Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private DoorChatMessageRepository doorChatMessageRepository;

    @KafkaListener(id = "main-listener", topics = "courses")
    public void receive(DoorChatMessage message) throws JsonProcessingException {
        logger.info("Receive data from Kafka");
        logger.info("  Topic={}", message.getTopic());
        logger.info("  Data={}", message);

        doorChatMessageRepository.save(message);

        template.convertAndSend(message.getTopic(), new ObjectMapper().writeValueAsString(message));
    }
}
