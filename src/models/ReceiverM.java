package models;

import java.util.ArrayList;
import events.ViewUpdateListener;

public class ReceiverM {
    private ArrayList<VRDM> vrdmList;
    private ArrayList<ViewUpdateListener<ReceiverM>> listeners;

    public ReceiverM() {
        vrdmList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void addVRD(VRDM vrdm) {
        vrdmList.add(vrdm);
        fireUpdateView();
    }


    public void removeVRD(VRDM vrdm) {
        vrdmList.remove(vrdm);
        fireUpdateView();
    }

    public ArrayList<VRDM> getVRDList() {
        return vrdmList;
    }

    public void addViewUpdateListener(ViewUpdateListener<ReceiverM> listener) {
        listeners.add(listener);
    }

    private void fireUpdateView(){
        for(ViewUpdateListener<ReceiverM> reciver : listeners){
            reciver.updateView(this);
        }
    }
}
