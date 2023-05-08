package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import mastermedia.frontend.Main;
import mastermedia.frontend.controllers.extra.Directory;
import mastermedia.frontend.controllers.extra.FileStructure;

import java.net.URL;
import java.util.ResourceBundle;

import static mastermedia.frontend.controllers.extra.FileStructure.directoryTemporary;

public class ShowFileInAlbum implements Initializable {
    public VBox vbox;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vbox.getChildren().add(FileStructure.addGridPaneFile(3,directoryTemporary));
    }
}
