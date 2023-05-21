package models;

import events.MessageReceiverBSC;
import events.ViewUpdateListener;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public abstract class NetworkDevice implements Runnable {
    private static final Random RANDOM = new Random();
    private final Object listenersLock = new Object();
    private static int number;
    private Thread thread;
    private int thisNumber;
    private int smsTransfered;
    private List<ViewUpdateListener<BSCM>> listeners;
    private Queue<Message> messages;
    private int smsPending;
    private MessageReceiverBSC messageReceiver;

}
