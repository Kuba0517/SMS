package events;

import models.Message;

public interface MessageSenderBSC{
    void sendMessageBSC(Message message, String deviceNumber);
}
