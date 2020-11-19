package deudoor.chatserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoorChatMessage implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    // i.e. 20172014
    private String userId;

    // i.e. 홍길동
    private String user;

    private Long timestamp;

    private String topic = "/topic/public";

    private String fileName;
    private String rawData;

    public DoorChatMessage(String message, String user) {
        this.user = user;
        this.message = message;
    }

    public DoorChatMessage(String fileName, String rawData, String user) {
        this.fileName = fileName;
        this.rawData = rawData;
    }

    public DoorChatMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DoorChatMessage [userId=" + userId + ", user=" + user + ", message=" + message + "]";
    }
}
