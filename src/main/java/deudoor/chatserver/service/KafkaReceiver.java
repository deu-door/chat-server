package deudoor.chatserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import deudoor.chatserver.model.DoorChatMessage;
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

    @KafkaListener(id = "main-listener", topics = "lecture")
    public void receive(DoorChatMessage message) throws JsonProcessingException {
        logger.info("Receive data from Kafka");
        logger.info("  Topic={}", message.getTopic());
        logger.info("  Data={}", message);

        var msg = new HashMap<String, String>();
        msg.put("timestamp", Long.toString(message.getTimestamp()));
        msg.put("message", message.getMessage());
        msg.put("author", message.getUser());
        msg.put("authorId", message.getUserId());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(msg);

        template.convertAndSend(message.getTopic(), json);
    }
}
