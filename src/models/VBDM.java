package models;

public class VBDM implements Runnable{
    private int frequency;
    private String deviceNumber;
    private String activity;
    private boolean active;
    private Message message;

    public VBDM(Message message, int deviceNumber) {
        this.message = message;
        this.deviceNumber = String.valueOf(deviceNumber);
        frequency = 50; // Domyślna częstotliwość
        active = true;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setActivity(String activity){
        this.activity = activity;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public Message getMessage() {
        return message;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void run() {
        while(active) {
            sendMessage();
            try {
                Thread.sleep(1000 / frequency);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void sendMessage() {
        System.out.println("Device " + deviceNumber + " is sending a message at frequency " + frequency);
    }
}
