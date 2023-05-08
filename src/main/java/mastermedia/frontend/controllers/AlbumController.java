package mastermedia.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import mastermedia.frontend.Main;
import mastermedia.frontend.SceneController;
import mastermedia.frontend.controllers.extra.FileStructure;
import mastermedia.frontend.controllers.extra.XMLFile;

import java.io.IOException;

public class AlbumController {
    public VBox vbox;
    public Button EditButton;
    @FXML
    private ScrollPane scrollPane;



    public Scene createScene() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("album_controller.fxml"));

        return new Scene(fxmlLoader.load());

    }


    public void initialize() throws IOException {

        EditButton.setOnAction(event -> {
            try {
                new SceneController().switchToScene(event, String.valueOf(XMLFile.BROWSE_FILES_IN_ALBUM_VIEW));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        vbox.getChildren().add(FileStructure.addGridPaneDirectory(3,Main.directoryList,200,250));

    }

}