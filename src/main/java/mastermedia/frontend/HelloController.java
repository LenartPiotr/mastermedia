package mastermedia.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mastermedia.backend.FolderStructure;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void createDirs() {
        FolderStructure.createFolderStructure();
    }

    @FXML
    protected void testFFMPEG() {
    }
}
