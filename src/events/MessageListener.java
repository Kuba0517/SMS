package events;

import models.Message;

public interface MessageListener {
    void messageTo(Message message);
}
