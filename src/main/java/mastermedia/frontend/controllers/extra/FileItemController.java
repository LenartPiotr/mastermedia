package mastermedia.frontend.controllers.extra;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileItemController implements Initializable {
    public Pane sectionPane;
    public Label nameSection;
    public ImageView albumPane;
    public Button cancelButton;
    public Button newDivisionButton;

    public Rectangle clip;

    public void setData(File file){
//        nameSection.setText(file.getNameFile());
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
