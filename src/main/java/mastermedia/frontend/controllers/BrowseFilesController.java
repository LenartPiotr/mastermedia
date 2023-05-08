package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import mastermedia.frontend.Main;
import mastermedia.frontend.controllers.extra.FileStructure;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowseFilesController implements Initializable {
    public ScrollPane scrollPane;
//    public Pane mainPane;
    public GridPane GridPaneForFiles;
    public VBox mainVbox;
    public ImageView line;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileStructure.setMainVbox(mainVbox, Main.directoryList);

    }


}

