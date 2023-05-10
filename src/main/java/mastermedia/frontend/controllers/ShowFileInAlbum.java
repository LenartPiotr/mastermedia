package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mastermedia.frontend.SceneController;
import mastermedia.frontend.controllers.extra.FileItemController;
import mastermedia.frontend.controllers.extra.FileStructure;
import mastermedia.frontend.controllers.extra.XMLFile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static mastermedia.frontend.controllers.extra.FileStructure.directoryTemporary;

public class ShowFileInAlbum implements Initializable {
    public VBox vbox;
    public ScrollPane scrollPane;
    public Label title;
    public Button editButton;
    public Button backButton;
    public AnchorPane mainPane;
    public Button rotateLeftButton;
    public Button flipHorizontallyButton;
    public Button deleteButton;
    public Button flipVerticallyButton;
    public Button rotateRightButton;
    public Pane editPanel;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        title.setText(directoryTemporary.getName());
        vbox.getChildren().add(FileStructure.addGridPaneFile(4,directoryTemporary,false,mainPane));

        scrollPane.setVvalue(0.001);
        scrollPane.setHvalue(2);
        editPanel.setVisible(false);

        AtomicBoolean isClicked = new AtomicBoolean(false);
        editButton.setOnAction(event -> {

//            FileItemController.setUpCheckBox();
            if (isClicked.get()) {
                editButton.setText("Edytuj");
                editPanel.setVisible(false);
                isClicked.set(false);

            } else {
                editButton.setText("Zapisz");
                editPanel.setVisible(true);
                isClicked.set(true);
                vbox.getChildren().removeAll();
                try {
                    new SceneController().switchToScene(event, String.valueOf(XMLFile.SHOW_FILE_IN_ALBUM));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

//                vbox.getChildren().add(FileStructure.addGridPaneFile(4,directoryTemporary,true,mainPane));
            }
        });

        rotateLeftButton.setOnAction(event -> {});
        rotateRightButton.setOnAction(event -> {});
        flipHorizontallyButton.setOnAction(event -> {});
        flipVerticallyButton.setOnAction(event -> {});
        deleteButton.setOnAction(event -> {});


        backButton.setOnAction(event -> {
            try {

                new SceneController().switchToScene(event, String.valueOf(XMLFile.AlBUM_CONTROLLER));

            }catch(IOException e) {

                throw new RuntimeException(e);

            }
        });





    }
}
