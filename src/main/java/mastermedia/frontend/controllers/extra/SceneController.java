package mastermedia.frontend.controllers.extra;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class SceneController {

    protected Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToScene(ActionEvent event, String nameFileXML) throws IOException {

        System.out.println(nameFileXML.toLowerCase() + ".fxml");
        System.out.println(getClass().getResource(
                nameFileXML.toLowerCase() + ".fxml"));
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mastermedia/frontend"+
                nameFileXML.toLowerCase() + ".fxml")));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSceneWithMenuItems(MenuItem menuItem, String nameFileXML) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                nameFileXML.toLowerCase() + ".fxml")));
        stage = (Stage)menuItem.getParentPopup().getOwnerWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
