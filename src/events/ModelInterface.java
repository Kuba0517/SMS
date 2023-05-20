package events;

import models.Message;

public interface ModelInterface {
    void setStatus(String status);
    void setFrequency();
    void onMessageAdd(Message message);
}
