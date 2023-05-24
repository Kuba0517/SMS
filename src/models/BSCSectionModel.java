package models;

import events.ViewUpdateListener;

import java.util.ArrayList;

public class BSCSectionModel {
    private ViewUpdateListener<BSCSectionModel> listener;
    private ArrayList<BSCM> bscms;

    public BSCSectionModel() {
        bscms = new ArrayList<>();
        bscms.add(new BSCM());
    }

    public void addBSCM() {
        bscms.add(new BSCM());
        fireViewUpdate();
    }

    public void setViewUpdateListener(ViewUpdateListener<BSCSectionModel> listener) {
        this.listener = listener;
    }

    public ArrayList<BSCM> getBSCMS(){
        return bscms;
    }

    private void fireViewUpdate(){
        listener.updateView(this);
    }
}
