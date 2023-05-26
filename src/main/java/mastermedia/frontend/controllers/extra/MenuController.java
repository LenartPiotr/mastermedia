package mastermedia.frontend.controllers.extra;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mastermedia.frontend.SceneController;

public class MenuController {


    @FXML
    public Button folder;
    @FXML
    public Button settings;

    @FXML
    public void initialize() {
        folder.setTextFill(Color.WHITE);
        folder.setOnAction(event -> {

            settings.setTextFill(Color.web("#707070"));
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


    public void setUpButton(){
        settings.setTextFill(Color.WHITE);
        folder.setTextFill(Color.web("#707070"));
    }





}
