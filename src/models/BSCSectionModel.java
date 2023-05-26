package models;

import events.ViewUpdateListener;

import java.util.ArrayList;

public class BSCSectionModel {
    private ViewUpdateListener<BSCSectionModel> listener;
    private ArrayList<BSCM> bscms;
    private static int numberInOrder;
    private int thisNumberInOrder;

    public BSCSectionModel() {
        bscms = new ArrayList<>();
        this.thisNumberInOrder = numberInOrder++;
        bscms.add(new BSCM());
        bscms.get(0).setNumberInOrder(thisNumberInOrder);
    }

    public void addBSCM() {
        bscms.add(new BSCM());
        bscms.get(bscms.size() - 1).setNumberInOrder(thisNumberInOrder);
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
