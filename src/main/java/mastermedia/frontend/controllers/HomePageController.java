package mastermedia.frontend.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import mastermedia.frontend.Main;

import java.io.IOException;

public class HomePageController {

    public Scene createScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home_page_view.fxml"));

        return new Scene(fxmlLoader.load());
    }


}