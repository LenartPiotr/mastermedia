package mastermedia.frontend;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mastermedia.backend.FileManager;
import mastermedia.backend.FolderStructure;
import mastermedia.backend.ffmpeg.WindowsFFMPEGDownloader;
import mastermedia.backend.settings.Settings;
import mastermedia.frontend.controllers.HomePageController;

public class Main extends Application {

    static FolderStructure folderStructure;

    @Override
    public void start(Stage stage) throws IOException {

        HomePageController homePageController = new HomePageController();
        Scene loginScene = homePageController.createScene();

        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("MasterMedia");
        stage.setScene(loginScene);

        stage.show();

        // Pobranie listy albumów
        String[] lista = folderStructure.getSorted().list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory();
            }
        });
        for (String s: lista) System.out.println(s);

        // Pobranie zdjęć w albumie
        String album = lista[0];
        File albumFolder = new File(folderStructure.getSorted().getPath(), album);
        File[] images = albumFolder.listFiles();

        for (File f: images) System.out.println(f.getName());

    }

    public static void main(String[] args) {

        FileManager fm = new FileManager();
        fm.checkFiles();
        Settings s = fm.getSettings();
        folderStructure = new FolderStructure();
        folderStructure.createFolderStructure(s.getDirectories());
        // new WindowsFFMPEGDownloader().download(fs.getOriginal());
        launch();

    }

}
