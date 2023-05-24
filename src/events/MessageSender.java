package events;

import models.Message;

public interface MessageSender{
    void sendMessage(Message message, String deviceNumber);
}
