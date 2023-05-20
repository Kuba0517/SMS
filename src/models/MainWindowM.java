package models;

import java.util.ArrayList;

public class MainWindowM {
    private ArrayList<NetworkM> networks;
    private ArrayList<BTSM> startingBTS;
    private ArrayList<BTSM> endingBTS;


    public MainWindowM(){
        networks = new ArrayList<>();
        startingBTS = new ArrayList<>();
        endingBTS = new ArrayList<>();
    }

    public void addNetwork(NetworkM networkM){
        networks.add(networkM);
        startingBTS.add(networkM.getStartBtsm());
        endingBTS.add(networkM.getEndBtsm());
    }

    public ArrayList<BTSM> getStartingBTS() {
        return startingBTS;
    }
}
