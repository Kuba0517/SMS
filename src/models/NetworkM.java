package models;

import controller.NetworkC;

import java.util.ArrayList;

public class NetworkM {
    private ArrayList<BSCM> bscmList;
    private BTSM startBtsm;
    private BTSM endBtsm;

    public NetworkM(){
        bscmList = new ArrayList<>();
        startBtsm = new BTSM();
        endBtsm = new BTSM();
    }

    public void addBscm(BSCM bscm){
        bscmList.add(bscm);
    }

    public void removeBscm(BSCM bscm){
        bscmList.remove(bscm);
    }

    public BTSM getStartBtsm() {
        return startBtsm;
    }

    public void setStartBtsm(BTSM startBtsm) {
        this.startBtsm = startBtsm;
    }

    public BTSM getEndBtsm() {
        return endBtsm;
    }

    public void setEndBtsm(BTSM endBtsm) {
        this.endBtsm = endBtsm;
    }

    public ArrayList<BSCM> getBscmList() {
        return bscmList;
    }
}
