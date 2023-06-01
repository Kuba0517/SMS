package models;

import java.util.ArrayList;

import events.ViewUpdateListener;

public class ReceiverM {
    private static ArrayList<VRDM> vrdmList;
    private ViewUpdateListener<ReceiverM> listener;

    public ReceiverM() {
        vrdmList = new ArrayList<>();
    }

    public void addVRD(VRDM vrdm) {
        vrdmList.add(vrdm);
        fireViewUpdate();
    }


    public void removeVRD(VRDM vrdm) {
        vrdmList.remove(vrdm);
        fireViewUpdate();
    }

    public static ArrayList<VRDM> getVRDList() {
        return vrdmList;
    }

    public void setViewUpdateListener(ViewUpdateListener<ReceiverM> listener) { // Zmienione na set zamiast add
        this.listener = listener;
    }

    private void fireViewUpdate() {
        if (listener != null) { // Sprawdzenie, czy listener nie jest nullem
            listener.updateView(this);
        }
    }


}
