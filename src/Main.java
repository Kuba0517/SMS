import controller.*;
import graphics.MainWindowG;
import graphics.NetworkG;
import graphics.ReceiverG;
import graphics.SenderG;
import models.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        SenderM senderModel = new SenderM();
        ReceiverM receiverModel = new ReceiverM();
        NetworkM networkModel = new NetworkM();


        NetworkC networkController = new NetworkC(networkModel, receiverModel, senderModel);
        SenderC senderController = new SenderC(senderModel, networkController);
        ReceiverC receiverController = new ReceiverC(receiverModel);



        SenderG senderView = new SenderG(senderController);
        ReceiverG reciverView = new ReceiverG(receiverController);
        NetworkG networkView = new NetworkG(networkController, networkController);


        senderController.setView(senderView);
        receiverController.setView(reciverView);
        networkController.setView(networkView);


        MainWindowG mainWindow = new MainWindowG();
        mainWindow.setClosing(networkController);
        mainWindow.setNetwork(networkView);
        mainWindow.setReceiver(reciverView);
        mainWindow.setSender(senderView);
    }
}


