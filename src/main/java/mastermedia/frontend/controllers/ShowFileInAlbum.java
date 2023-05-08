package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import mastermedia.frontend.Main;
import mastermedia.frontend.controllers.extra.FileStructure;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowFileInAlbum implements Initializable {
    public VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vbox.getChildren().add(FileStructure.addGridPane(3, Main.directoryList,200,200));
    }
}
