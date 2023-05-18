import controller.SenderC;
import graphics.MainWindow;
import graphics.SenderG;
import models.SenderM;

public class Main {

    public static void main(String[] args) {
        // Create the model
        SenderM senderModel = new SenderM();

        // Create the controller, giving it the model
        SenderC senderController = new SenderC(senderModel);

        // Create the view, giving it the controller
        SenderG senderView = new SenderG(senderController);

        // Set the view in the controller
        senderController.setView(senderView);

        // Finally, create the main window and give it the view
        MainWindow mainWindow = new MainWindow(senderView);
    }
}
