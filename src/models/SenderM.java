package models;

import events.ViewUpdateListener;

import java.util.ArrayList;
import java.util.List;

public class SenderM {
    private List<VBDM> items;
    private List<ViewUpdateListener<VBDM>> listeners;

    public SenderM() {
        items = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void addVBD(VBDM vbdm) {
        items.add(vbdm);
        fireViewUpdate(vbdm);
    }

    public void addViewUpdateListener(ViewUpdateListener<VBDM> listener) {
        listeners.add(listener);
    }

    private void fireViewUpdate(VBDM vbdm) {
        for (ViewUpdateListener<VBDM> listener : listeners) {
            listener.updateView(vbdm);
        }
    }
}