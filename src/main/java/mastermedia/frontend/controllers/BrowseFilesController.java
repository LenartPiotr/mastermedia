package mastermedia.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import mastermedia.frontend.Main;
import mastermedia.frontend.controllers.extra.File;
import mastermedia.frontend.controllers.extra.FileItemController;
import mastermedia.frontend.controllers.extra.FileStructure;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BrowseFilesController implements Initializable {
    public ScrollPane scrollPane;
//    public Pane mainPane;
    public GridPane GridPaneForFiles;
    public VBox mainVbox;
    public ImageView line;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileStructure.setMainVbox(mainVbox);

    }


}

