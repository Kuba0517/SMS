package events;

import models.Message;

public interface MessageReceiverBSC {
    void receiveMessageBSC(Message message, String deviceNumber);
}
