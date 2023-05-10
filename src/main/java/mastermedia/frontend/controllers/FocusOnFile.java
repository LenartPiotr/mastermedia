package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import mastermedia.frontend.SceneController;
import mastermedia.frontend.controllers.extra.Directory;
import mastermedia.frontend.controllers.extra.FileStructure;
import mastermedia.frontend.controllers.extra.XMLFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class FocusOnFile implements Initializable {
    public Pane foggyPane;
    public ImageView focusedImg;
    public Pane rightArrow;
    public Pane leftArrow;

    public  Directory directory = FileStructure.directoryTemporary;
    List<File> fileList = directory.getFileList();


    public void showImage(int i){

        focusedImg.setImage(new Image(fileList.get(i).toURI().toString()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AtomicInteger i = new AtomicInteger(FileStructure.filePosition);
        showImage(i.get());


        foggyPane.setOnMouseClicked(mouseEvent -> {
            try {
                new SceneController().switchToScene(mouseEvent, String.valueOf(XMLFile.SHOW_FILE_IN_ALBUM));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        rightArrow.setOnMouseClicked(mouseEvent -> {
            showImage(i.incrementAndGet());
        });

        leftArrow.setOnMouseClicked(mouseEvent -> {

            showImage(i.decrementAndGet());

        });

    }
}
