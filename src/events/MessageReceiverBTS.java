package events;

import models.Message;

public interface MessageReceiverBTS {
    void receiveMessageBTS(Message message, String deviceNumber);
}
