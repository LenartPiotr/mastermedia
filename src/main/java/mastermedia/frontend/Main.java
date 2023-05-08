package mastermedia.frontend;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mastermedia.backend.FileManager;
import mastermedia.backend.FolderStructure;
import mastermedia.backend.settings.Settings;
import mastermedia.frontend.controllers.AlbumController;
import mastermedia.frontend.controllers.extra.Directory;

public class Main extends Application {

    public static FolderStructure folderStructure;
    public static List<Directory> directoryList = new ArrayList<>();
    public static File[] images;
    public static String[] albumList;

    @Override
    public void start(Stage stage) throws IOException {

        AlbumController albumController = new AlbumController();

        Scene loginScene =  new Scene(new FXMLLoader(Main.class.getResource("home_page_view.fxml")).load());

        stage.initStyle(StageStyle.DECORATED);
//        stage.getIcons().add(new Image());
        stage.setTitle("MasterMedia");
        stage.setScene(loginScene);

        stage.show();

        // Pobranie listy albumów
        albumList = folderStructure.getSorted().list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) { return new File(dir, name).isDirectory(); }

        });

        for(int i = 0; i < Objects.requireNonNull(albumList).length; i++) {

            Directory directory = new Directory();
            directory.setName(albumList[i]);
            directoryList.add(directory);

        }

        for(Directory d : directoryList) System.out.println(d.getName());

        // Pobranie zdjęć w albumach

        for(int i = 0; i < directoryList.size(); i++) {

            String album = albumList[i];
            File albumFolder = new File(folderStructure.getSorted().getPath(), album);
            images = albumFolder.listFiles();

            directoryList.get(i).setFileList(Arrays.stream(images).toList());

        }

        // assert images != null;
        for(Directory d : directoryList) System.out.println(String.valueOf(d.getFileList().get(0)));

    }

    public static void main(String[] args) {

        FileManager fm = new FileManager();
        fm.checkFiles();
        Settings s = fm.getSettings();
        folderStructure = new FolderStructure();
        folderStructure.createFolderStructure(s.getDirectories());

        // new WindowsFFMPEGDownloader().download(folderStructure.getOriginal());
        launch();

    }

}
