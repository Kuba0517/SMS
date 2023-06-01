package events;

import models.Message;

public interface VBD extends Device{
    void setFrequency(int frequency);
    int getFrequency();
    void setStatus(String status);
    String getStatus();
    void setMessage(Message message);
    String getDeviceNumber();
}
