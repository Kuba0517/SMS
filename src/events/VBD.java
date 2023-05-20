package events;

import models.Message;

public interface VBD extends Device{
    void setFrequency(int frequency);
    void setStatus(String status);
    void setMessage(Message message);
    String getDeviceNumber();
}
