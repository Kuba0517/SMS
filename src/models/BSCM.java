package models;

import events.ViewUpdateListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BSCM {
    private static int number;
    private int thisNumber;
    private int smsTransfered;
    private List<ViewUpdateListener<BSCM>> listeners;
    private int smsPending;

    public BSCM() {
        thisNumber = ++number;
        this.smsTransfered = 0;
        this.smsPending = 0;
        this.listeners = new ArrayList<>();
    }


    public void addViewUpdateListener(ViewUpdateListener<BSCM> listener){
        listeners.add(listener);
    }
    public String getNumber() {
        return Integer.toString(thisNumber);
    }

    public String getSmsTransfered() {
        return Integer.toString(smsTransfered);
    }

    public void setSmsTransfered(int smsTransfered) {
        this.smsTransfered = smsTransfered;
    }

    public String getPendingMessage() {
        return Integer.toString(smsPending);
    }

    public void setSmsPending(int smsPending) {
        this.smsPending = smsPending;
    }

    private void fireViewUpdate(){
        for(ViewUpdateListener<BSCM> listener : listeners){
            listener.updateView(this);
        }
    }
}
