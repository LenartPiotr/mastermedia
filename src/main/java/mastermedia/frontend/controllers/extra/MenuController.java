package mastermedia.frontend.controllers.extra;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {

    @FXML
    Button home;
    @FXML
    Button search;
    @FXML
    Button folder;

    @FXML
    Button settings;

    String a = String.valueOf(XMLFile.HOME_PAGE_VIEW);

    @FXML
    public void initialize() {
        home.setOnAction(event -> {
            try {
                new SceneController().switchToScene(event, String.valueOf(XMLFile.HOME_PAGE_VIEW));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        search.setOnAction(event -> {
            try {
                new SceneController().switchToScene(event, String.valueOf(XMLFile.BROWSE_FILES_VIEW));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        folder.setOnAction(event -> {
            try {
                new SceneController().switchToScene(event, String.valueOf(XMLFile.BROWSE_FILES_ALBUM_VIEW));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        settings.setOnAction(event -> {
            try {
                new SceneController().switchToScene(event, String.valueOf(XMLFile.SETTINGS_VIEW));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }
}
