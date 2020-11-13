package deudoor.chatserver.controller;

import deudoor.chatserver.repository.DoorChatMessageRepository;
import deudoor.chatserver.service.KafkaReceiver;
import deudoor.chatserver.model.DoorChatMessage;
import deudoor.chatserver.model.DoorChatRoom;
import deudoor.chatserver.model.DoorUser;
import deudoor.chatserver.repository.DoorChatRoomUserRepository;
import deudoor.chatserver.security.JwtUtilService;
import deudoor.chatserver.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class DoorChatController {

    @Autowired
    private KafkaSender sender;

    @Autowired
    private KafkaReceiver receiver;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private DoorChatRoomUserRepository doorChatRoomUserRepository;

    @Autowired
    private DoorChatMessageRepository doorChatMessageRepository;

    // Client --(/chat)--> Spring Boot --> Kafka
    @MessageMapping("/message")
    public void sendMessage(DoorChatMessage message) {
        sender.send(message);
    }

    @GetMapping("/history")
    public List<DoorChatMessage> getHistories(
            @RequestParam @NotBlank String topic
    ) {
        return doorChatMessageRepository.findByTopic(topic);
    }

}
