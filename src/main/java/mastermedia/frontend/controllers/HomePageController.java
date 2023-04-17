package mastermedia.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import mastermedia.frontend.Main;

import java.io.IOException;

public class HomePageController {



    @FXML
    Button reloadButton;

    public Scene createScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home_page_view.fxml"));
        return new Scene(fxmlLoader.load());
    }



    @FXML
    public void initialize() {



        reloadButton.setOnAction(event -> {
            // #TODO add action

        });


    }


}