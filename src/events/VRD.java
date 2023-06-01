package events;

public interface VRD extends Device{
    void setTick();
    boolean getTick();
    String getNumberOfMessages();
}
