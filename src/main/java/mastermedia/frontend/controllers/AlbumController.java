package mastermedia.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import mastermedia.frontend.SceneController;
import mastermedia.frontend.controllers.extra.FileStructure;
import mastermedia.frontend.controllers.extra.TreeOfDirectories;
import mastermedia.frontend.controllers.extra.XMLFile;

import java.io.IOException;

public class AlbumController {
    public VBox vbox;
    public Button refreshButton;
    @FXML
    private ScrollPane scrollPane;


    public void initialize() throws IOException {

        refreshButton.setOnAction(event -> {
            try {
                new SceneController().switchToScene(event, String.valueOf(XMLFile.AlBUM_CONTROLLER));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });



        TreeOfDirectories treeOfDirectories = new TreeOfDirectories();


        vbox.getChildren().add(FileStructure.addGridPaneDirectory(3,treeOfDirectories.getDirectoryList(),150,220));

    }

}