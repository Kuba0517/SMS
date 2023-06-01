package models;

import events.Device;
import events.RemoveListener;
import events.MessageSender;
import java.util.Random;

import static models.VRDM.getCounter;

public class VBDM implements Runnable, Device {
    private static final Random RANDOM = new Random();
    private String status;
    private int frequency;
    private Message message;
    private boolean active;
    private static int counter = 1;
    private int deviceNumber;
    private RemoveListener removeListener;
    private MessageSender messageReceiver;
    private final Object lock = new Object();
    private boolean suspended = false;
    private int receipent;
    private Random random = new Random();
    private int possibleRecipentPool;
    private Thread thread;
    private int smsTransfered;

    public VBDM(Message message, RemoveListener removeListener, MessageSender messageReceiver){
        this.status = "ACTIVE";
        this.frequency = RANDOM.nextInt(10) + 1;
        this.message = message;
        this.deviceNumber = counter++;
        this.active = true;
        this.removeListener = removeListener;
        this.messageReceiver = messageReceiver;
        this.possibleRecipentPool = getCounter();
        this.receipent = random.nextInt(possibleRecipentPool) + 1;
        this.thread = new Thread(this);
        this.smsTransfered = 0;
        message.setRecipient(Integer.toString(receipent));
        System.out.println("Odbiorca: " + receipent);
        thread.start();
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

    public Thread getThread() {
        return thread;
    }
    public void incrementTransferedSMS(){
        smsTransfered++;
    }
    public int getTransferedSMS(){
        return smsTransfered;
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

    public int getDeviceNumber() {
        return deviceNumber;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && active) {
                synchronized (lock) {
                    while (suspended) {
                        lock.wait();
                    }
                }
                Thread.sleep(2000 / frequency);
                messageReceiver.sendMessage(message, Integer.toString(getDeviceNumber()));
                this.incrementTransferedSMS();
            }
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


    @Override
    public void stop() {
        active = false;
        removeListener.remove(this);
    }
}
