package mastermedia.frontend.controllers.extra;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mastermedia.frontend.SceneController;

public class MenuController {


    @FXML
    Button folder;
    @FXML
    Button settings;

    @FXML
    public void initialize() {

        folder.setOnAction(event -> {

            try {

                new SceneController().switchToScene(event, String.valueOf(XMLFile.AlBUM_CONTROLLER));

            }catch(IOException e) {

                throw new RuntimeException(e);

            }

        });

        settings.setOnAction(event -> {

            try {

                new SceneController().switchToScene(event, String.valueOf(XMLFile.SETTINGS_VIEW));

            }catch(IOException e) {

                throw new RuntimeException(e);

            }

        });

    }





}
