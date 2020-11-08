package deudoor.chatserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
public class DoorChatMessage implements Serializable {

    private String message;
    // i.e. 20172014
    private String userId;
    // i.e. 홍길동
    private String user;
    private Long timestamp;

    private String topic = "/topic/public";
    private Long roomId;

    private String fileName;
    private String rawData;

    public DoorChatMessage() {

    }

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
        return "DoorChatMessage [user=" + this.user + ", message=" + this.message + "]";
    }
}
