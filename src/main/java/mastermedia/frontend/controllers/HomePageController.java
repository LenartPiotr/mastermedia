package mastermedia.frontend.controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import mastermedia.frontend.Main;

public class HomePageController {

    public Scene createScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home_page_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        return scene;
    }





}