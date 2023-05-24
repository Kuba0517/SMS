package models;

import events.ViewUpdateListener;

import java.util.ArrayList;

public class BTSSectionModel {
    private ViewUpdateListener<BTSSectionModel> listener;

    private ArrayList<BTSM> btsms;

    public BTSSectionModel() {
        btsms = new ArrayList<>();
        btsms.add(new BTSM());
    }

    public void addBTSM() {
        btsms.add(new BTSM());
        fireViewUpdate();
    }


    public ArrayList<BTSM> getBTSMS(){
        return btsms;
    }

    public void setViewUpdateListener(ViewUpdateListener<BTSSectionModel> listener) {
        this.listener = listener;
    }
    private void fireViewUpdate(){
        listener.updateView(this);
    }
}
