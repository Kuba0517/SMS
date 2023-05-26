package models;

import events.Device;
import events.RemoveListener;
import events.MessageSender;
import java.util.Random;

public class VBDM implements Runnable, Device {
    private static final Random RANDOM = new Random();
    private String status;
    private int frequency;
    private Message message;
    private boolean active;
    private int deviceNumber;
    private RemoveListener removeListener;
    private MessageSender messageReceiver;
    private final Object lock = new Object();
    private boolean suspended = false;


    public VBDM(Message message, RemoveListener removeListener, MessageSender messageReceiver){
        this.status = "WAITING";
        this.frequency = RANDOM.nextInt(10) + 1;
        this.message = message;
        this.deviceNumber = (int) (Math.random() * 11);
        this.active = true;
        this.removeListener = removeListener;
        this.messageReceiver = messageReceiver;
        new Thread(this).start();
        }

    public void setStatus(String status) {
        this.status = status;
        active = status.equals("ACTIVE");
        System.out.println("Satus zmieniony na: " + active);
        if(active){
            this.setSuspended();
        }
        else {
            this.cont();
        }
    }

    public void setSuspended(){
        suspended = true;
    }

    public void cont(){
        synchronized (lock){
            suspended = false;
            lock.notify();
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
            synchronized(lock) {
                while (suspended) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            try {
                Thread.sleep(2000 / frequency);
                messageReceiver.sendMessage(message, getDeviceNumber());
                System.out.println("WATEK 1");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void stop() {
        active = false;
        removeListener.remove(this);
    }
}
