package models;

import java.util.HashMap;
import java.util.Map;

public class BSCM implements Runnable {
    private Map<String, BTSM> btsMap;

    public BSCM() {
        btsMap = new HashMap<>();
    }

    public void addBTS(BTSM bts) {
        btsMap.put(bts.getId(), bts);
    }

    @Override
    public void run() {
        while (true) {
            for (BTSM bts : btsMap.values()) {
                Message message = bts.retrieveMessage();
                if (message != null) {
                    processMessage(message);
                }
            }
        }
    }

    private void processMessage(Message message) {
        // Logika przetwarzania wiadomości
    }
}
