package mastermedia.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mastermedia.frontend.SceneController;
import javafx.scene.shape.SVGPath;

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




    @FXML
    public void initialize() {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M150 0 L75 200 L225 200 Z");


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
