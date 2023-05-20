package models;

import events.ViewUpdateListener;

import java.util.ArrayList;
import java.util.List;

public class SenderM {
    private List<VBDM> vbdmList;
    private List<ViewUpdateListener<SenderM>> listeners;

    public SenderM() {
        vbdmList = new ArrayList<>();
        listeners = new ArrayList<>();
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

    public void addViewUpdateListener(ViewUpdateListener<SenderM> listener) {
        listeners.add(listener);
    }

    private void fireViewUpdate() {
        for (ViewUpdateListener<SenderM> listener : listeners) {
            listener.updateView(this);
        }
    }
}