package events;

import models.VBDM;
import java.util.ArrayList;

public interface SenderInterface {
    void addVBD(VBDM vbdm);
    ArrayList<VBDM> getVBDList();
}
