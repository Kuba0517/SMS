package events;

import models.Message;

public interface MessageReceiver {
    void receiveMessage(Message message, String deviceNumber);
}
