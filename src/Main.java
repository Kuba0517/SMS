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

        // Create the controller, giving it the model

        NetworkC networkController = new NetworkC(networkModel);
        SenderC senderController = new SenderC(senderModel, networkController);
        ReceiverC receiverController = new ReceiverC(receiverModel);



        // Create the view, giving it the controller
        SenderG senderView = new SenderG(senderController);
        ReceiverG reciverView = new ReceiverG(receiverController);
        NetworkG networkView = new NetworkG(networkController, networkController);

        // Set the view in the controller
        senderController.setView(senderView);
        receiverController.setView(reciverView);
        networkController.setView(networkView);

        // Finally, create the main window and give it the view
        MainWindowG mainWindow = new MainWindowG(senderView, reciverView, networkView);
    }
}
