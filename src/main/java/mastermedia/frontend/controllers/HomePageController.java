package mastermedia.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import mastermedia.frontend.Main;
import mastermedia.frontend.controllers.extra.MenuController;
import mastermedia.frontend.controllers.extra.SceneController;
import mastermedia.frontend.controllers.extra.XMLFile;

import java.io.IOException;

public class HomePageController {

    public Scene createScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home_page_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        return scene;
    }





}