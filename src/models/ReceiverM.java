package models;

import java.util.ArrayList;

public class ReceiverM {
    private ArrayList<VRDM> vrdmList;

    public ReceiverM() {
        vrdmList = new ArrayList<>();
    }

    // You might want to add methods to manipulate the list, e.g., add, remove, get receivers
    public void addVRD(VRDM vrdm) {
        vrdmList.add(vrdm);
    }

    public void removeVRD(VRDM vrdm) {
        vrdmList.remove(vrdm);
    }

    public ArrayList<VRDM> getVRDList() {
        return vrdmList;
    }
}
