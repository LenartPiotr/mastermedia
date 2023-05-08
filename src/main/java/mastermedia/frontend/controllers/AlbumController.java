package mastermedia.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mastermedia.frontend.controllers.extra.File;
import mastermedia.frontend.controllers.extra.FileStructure;

import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    public VBox vbox;
    @FXML
    private ScrollPane scrollPane;


    public void initialize() {

        List<File> ls = new ArrayList<>();

        for(int i= 0; i<4;i++){
            File file = new File();
            file.setNameFile(" ");
            ls.add(file);
        }
        vbox.getChildren().add(FileStructure.addGridPane(3,ls,200,400));

    }
}
