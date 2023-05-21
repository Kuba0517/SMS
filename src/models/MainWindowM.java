package models;

import events.ViewUpdateListener;

import java.util.ArrayList;
import java.util.List;

public class MainWindowM {
    private final Object listenersLock = new Object();
    private ArrayList<ViewUpdateListener<MainWindowM>> listeners;
    private ArrayList<NetworkM> networks;
    private ArrayList<BTSM> startingBTS;
    private ArrayList<BTSM> endingBTS;


    public MainWindowM(){
        this.listeners = new ArrayList<>();
        networks = new ArrayList<>();
        startingBTS = new ArrayList<>();
        endingBTS = new ArrayList<>();
    }

    public void addNetwork(NetworkM networkM){
        networks.add(networkM);
        startingBTS.add(networkM.getStartBtsm());
        endingBTS.add(networkM.getEndBtsm());
        fireViewUpdate();
    }

    public ArrayList<NetworkM> getNetworks(){
        return networks;
    }

    public void addViewUpdateListener(ViewUpdateListener<MainWindowM> listener) {
        synchronized (listenersLock) {
            listeners.add(listener);
        }
    }

    public ArrayList<BTSM> getStartingBTS() {
        return startingBTS;
    }

    private void fireViewUpdate() {
        for(ViewUpdateListener<MainWindowM> listener : listeners){
            listener.updateView(this);
        }
    }
}
