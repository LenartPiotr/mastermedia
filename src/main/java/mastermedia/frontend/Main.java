package mastermedia.frontend;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mastermedia.backend.CreateRelocate;
import mastermedia.backend.FileManager;
import mastermedia.backend.FolderStructure;
import mastermedia.backend.settings.Settings;
import mastermedia.backend.squisher.ImageSquisher;
import mastermedia.frontend.controllers.HomePageController;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

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

        try {

            FFmpeg ff = new FFmpeg(new File(fs.getBinaries(), "bin/ffmpeg.exe").getAbsolutePath());
            FFprobe fp = new FFprobe(new File(fs.getBinaries(), "bin/ffprobe.exe").getAbsolutePath());
            ImageSquisher is = new ImageSquisher(ff, fp, s.getThumbnails());
            for(File f : fs.getOriginal().listFiles()) is.squishFile(f, new File(fs.getLowResolution(), f.getName()));

        }catch(IOException e) {

            e.printStackTrace();

        }

        new CreateRelocate(fs.getLowResolution(), fs.getSorted(), s.getFiletypes()).relocateFiles();
        // if(!new File(fs.getBinaries(), "ffmpeg-git-essentials.7z").exists()) new Thread(() -> {

        // System.out.println("start download");
        // new WindowsFFMPEGDownloader().download(fs.getBinaries());
        // System.out.println("done download");

        // }).start();
        launch();

    }

}
