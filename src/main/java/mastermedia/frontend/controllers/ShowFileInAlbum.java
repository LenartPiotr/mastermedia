package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import mastermedia.frontend.controllers.extra.FileStructure;

import java.net.URL;
import java.util.ResourceBundle;

import static mastermedia.frontend.controllers.extra.FileStructure.directoryTemporary;

public class ShowFileInAlbum implements Initializable {
    public VBox vbox;
    public ScrollPane scrollPane;
    public Label title;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setText(directoryTemporary.getName());
        vbox.getChildren().add(FileStructure.addGridPaneFile(4,directoryTemporary));

        scrollPane.setVvalue(0.001);
        scrollPane.setHvalue(2);



    }
}
