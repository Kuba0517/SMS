package events;

import models.Message;

public interface MessageListener {
    void message(Message messsage);
}
