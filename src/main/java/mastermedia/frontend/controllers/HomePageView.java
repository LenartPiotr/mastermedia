package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import mastermedia.frontend.SceneController;
import mastermedia.frontend.controllers.extra.XMLFile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageView implements Initializable {
    public Button startButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startButton.setOnAction(event -> {
            try {

                new SceneController().switchToScene(event, String.valueOf(XMLFile.AlBUM_CONTROLLER));

            }catch(IOException e) {

                throw new RuntimeException(e);

            }
        });

    }
}
