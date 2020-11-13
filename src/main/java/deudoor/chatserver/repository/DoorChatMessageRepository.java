package deudoor.chatserver.repository;

import deudoor.chatserver.model.DoorChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoorChatMessageRepository extends JpaRepository<DoorChatMessage, Long> {

    List<DoorChatMessage> findByTopic(String topic);

}


