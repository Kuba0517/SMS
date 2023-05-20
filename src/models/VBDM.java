package models;

import events.Device;
import events.RemoveListener;
import events.MessageReceiver;
import java.util.Random;

public class VBDM implements Runnable, Device {
    private static final Random RANDOM = new Random();
    private String status;
    private int frequency;
    private Message message;
    private boolean active;
    private int deviceNumber;
    private RemoveListener removeListener;
    private MessageReceiver messageReceiver;

    public VBDM(Message message, RemoveListener removeListener, MessageReceiver messageReceiver){
        this.status = "WAITING";
        this.frequency = RANDOM.nextInt(10) + 1;
        this.message = message;
        this.deviceNumber = (int) (Math.random() * 11);
        this.active = false;
        this.removeListener = removeListener;
        this.messageReceiver = messageReceiver;
        }

    public void setStatus(String status) {
        this.status = status;
        active = status.equals("ACTIVE");
        System.out.println("Satus zmieniony na: " + active);
        if(active){
            start();
        }
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
        System.out.println("new frequency" + frequency);
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public int getFrequency() {
        return frequency;
    }

    public Message getMessage() {
        return message;
    }

    public String getDeviceNumber() {
        System.out.println(deviceNumber);
        return Integer.toString(deviceNumber);
    }

    @Override
    public void run() {
        while (active) {
            System.out.println("Sending message " + message.getContent());
            messageReceiver.receiveMessage(message, getDeviceNumber());
            try {
                Thread.sleep(1000 / frequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void stop() {
        active = false;
        removeListener.remove((Device)this);
    }
}
