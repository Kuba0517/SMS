package events;

import models.Message;

public interface ControllerInterface {
    void onStatusChange(String status);
    void onFrequencyChange(int frequency);
    void onMessageAdd(Message message);
}
