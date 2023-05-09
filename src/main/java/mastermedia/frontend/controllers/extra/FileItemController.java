package mastermedia.frontend.controllers.extra;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class FileItemController implements Initializable {
    public Pane sectionPane;
    public Label nameSection;
    public ImageView albumPane;
    public Button cancelButton;
    public Button newDivisionButton;

    public Rectangle clip;
    public Label title;
    public enum type {DIRECTORY,FILE}

    public void setData(Directory directory) throws IOException {
        title.setText(directory.getName());

        albumPane.setImage(new Image(directory.fileList.get(0).toURI().toString()));
    }

    public void showImageInAlbum(Directory directory, int i){
        title.setVisible(false);
        sectionPane.setPrefWidth(albumPane.getFitWidth());
        sectionPane.setPrefHeight(albumPane.getFitHeight());
        albumPane.setImage(new Image(directory.fileList.get(i).toURI().toString()));
    }




    private String changePath(String path){
        return path.replace(".\\Public\\", "").replace("\\","/");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clip = new Rectangle();
        clip.setWidth(183);
        clip.setHeight(127);

        clip.setArcHeight(20);
        clip.setArcWidth(20);

        albumPane.setClip(clip);



        cancelButton.setVisible(false);
        newDivisionButton.setVisible(false);

        AtomicBoolean isClicked = new AtomicBoolean(false);

        albumPane.setOnMouseClicked(event -> {
            if (isClicked.get()) {
                cancelButton.setVisible(false);
                newDivisionButton.setVisible(false);
                isClicked.set(false);
            } else {
                cancelButton.setVisible(true);
                newDivisionButton.setVisible(true);
                isClicked.set(true);
            }
        });
    }
}
