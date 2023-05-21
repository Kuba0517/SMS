package models;

import events.ViewUpdateListener;

import java.util.ArrayList;
import java.util.List;

public class SenderM {
    private List<VBDM> vbdmList;
    private ViewUpdateListener<SenderM> listener;

    public SenderM() {
        vbdmList = new ArrayList<>();
    }

    public void addVBD(VBDM vbdm) {
        vbdmList.add(vbdm);
        fireViewUpdate();
    }

    public List<VBDM> getVBDList() {
        return vbdmList;
    }

    public void removeVBD(VBDM vbdm){
        vbdmList.remove(vbdm);
        fireViewUpdate();
    }

    public void setViewUpdateListener(ViewUpdateListener<SenderM> listener) { // Zmienione na set zamiast add
        this.listener = listener;
    }

    private void fireViewUpdate() {
        if (listener != null) { // Sprawdzenie, czy listener nie jest nullem
            listener.updateView(this);
        }
    }
}