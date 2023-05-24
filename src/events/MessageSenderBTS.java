package events;

import models.Message;

public interface MessageSenderBTS{
    void sendMessageBTS(Message message, String deviceNumber);
}
