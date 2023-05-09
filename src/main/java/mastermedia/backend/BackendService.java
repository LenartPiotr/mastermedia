package mastermedia.backend;

import mastermedia.backend.settings.Settings;
import mastermedia.backend.squisher.ImageSquisher;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackendService {
    private static BackendService instance;
    private Settings settings;
    private FolderStructure folderStructure;
    private BackendService(){
        //
    }
    public static BackendService getInstance(){
        if (instance == null) instance = new BackendService();
        return instance;
    }

    public void init() throws IOException {
        FileManager fm = new FileManager();
        fm.checkFiles();
        settings = fm.getSettings();
        folderStructure = new FolderStructure();
        folderStructure.createFolderStructure(settings.getDirectories());

        if(folderStructure.getBinaries().toPath().resolve("bin/ffmpeg.exe").toFile().exists() &&
                folderStructure.getBinaries().toPath().resolve("bin/ffprobe.exe").toFile().exists()) {

            ImageSquisher is = new ImageSquisher(new FFmpeg(folderStructure.getBinaries().toPath().resolve("bin/ffmpeg.exe").toString()),
                    new FFprobe(folderStructure.getBinaries().toPath().resolve("bin/ffprobe.exe").toString()),
                    settings.getThumbnails());

            is.squishFolder(folderStructure.getOriginal(), folderStructure.getLowResolution());

            new FileRelocator(folderStructure.getLowResolution(), folderStructure.getSorted(), settings.getFiletypes()).relocateFiles();

        }
    }

    public String[] getAlbumList(){
        return folderStructure.getSorted().list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory();
            }
        });
    }

    public File[] getImagesInAlbum(String albumName){
        File folder = new File(folderStructure.getSorted().getPath(), albumName);
        return folder.listFiles();
    }

    public Settings getSettings() { return settings; }
    public FolderStructure getFolderStructure() { return folderStructure; }
}
