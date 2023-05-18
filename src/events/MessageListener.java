package events;

import models.Message;

public interface MessageListener {
    void addMessage(Message content);
}

