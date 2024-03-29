package mastermedia.frontend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mastermedia.backend.BackendService;
import mastermedia.frontend.controllers.AlbumController;
import mastermedia.frontend.controllers.extra.Directory;
import mastermedia.frontend.controllers.extra.TreeOfDirectories;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        Scene loginScene = new Scene(new FXMLLoader(Main.class.getResource("home_page_view.fxml")).load());

        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image(Main.class.getResource("/mastermedia/frontend/img/logo.png").openStream()));
        stage.setTitle("MasterMedia");
        stage.setScene(loginScene);

        stage.show();
    }

    public static void main(String[] args) {

        try {
            BackendService.getInstance().init();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        launch();

    }

}
