package mastermedia.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mastermedia.backend.FileManager;
import mastermedia.backend.FolderStructure;
import mastermedia.backend.settings.Settings;
import mastermedia.frontend.controllers.HomePageController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        HomePageController homePageController = new HomePageController();
        Scene loginScene = homePageController.createScene();

        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("MasterMedia");
        stage.setScene(loginScene);

        stage.show();

    }

    public static void main(String[] args) {

        FileManager fm = new FileManager();
        fm.checkFiles();
        Settings s = fm.getSettings();
        FolderStructure fs = new FolderStructure();
        fs.createFolderStructure(s.getDirectories());
        launch();

    }

}
