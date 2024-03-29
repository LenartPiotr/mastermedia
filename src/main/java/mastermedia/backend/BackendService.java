package mastermedia.backend;

import mastermedia.backend.metadata.FileMetadata;
import mastermedia.backend.metadata.MetadataManager;
import mastermedia.backend.settings.SettingChanger;
import mastermedia.backend.settings.Settings;
import mastermedia.backend.squisher.ImageSquisher;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class BackendService {
    private static BackendService instance;
    private Settings settings;
    private FolderStructure folderStructure;
    private MetadataManager metadataManager;
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

        metadataManager = new MetadataManager("metadata.yml");
        metadataManager.read();

        if(folderStructure.getBinaries().toPath().resolve("bin/ffmpeg.exe").toFile().exists() &&
                folderStructure.getBinaries().toPath().resolve("bin/ffprobe.exe").toFile().exists()) {

            ImageSquisher is = new ImageSquisher(new FFmpeg(folderStructure.getBinaries().toPath().resolve("bin/ffmpeg.exe").toString()),
                    new FFprobe(folderStructure.getBinaries().toPath().resolve("bin/ffprobe.exe").toString()),
                    settings.getThumbnails());

            // Create new data
            for (File f : Objects.requireNonNull(folderStructure.getOriginal().listFiles())){
                if (metadataManager.getFilesData().stream().anyMatch(m -> m.getFileName().equals(f.getName())))
                    continue;
                metadataManager.getFilesData().add(new FileMetadata(f.getName(), FileMetadata.ProcessingStage.SOURCE, ""));
            }

            // Squish images
            // is.squishFolder(folderStructure.getOriginal(), folderStructure.getLowResolution());
            for (FileMetadata data : metadataManager.getFilesData()){
                if (!data.getProcessingStage().equals(FileMetadata.ProcessingStage.SOURCE)) continue;
                is.squishFile(
                        folderStructure.getOriginal().toPath().resolve(data.getFileName()).toFile(),
                        folderStructure.getLowResolution().toPath().resolve(data.getFileName()).toFile()
                );
                data.setProcessingStage(FileMetadata.ProcessingStage.MINI);
            }

            // Reallocate low resolution
            new FileRelocator(folderStructure.getLowResolution(), folderStructure.getSorted(), settings.getFiletypes(), false).relocateFiles();
            for (FileMetadata data : metadataManager.getFilesData()){
                if (data.getProcessingStage().equals(FileMetadata.ProcessingStage.MINI))
                    data.setProcessingStage(FileMetadata.ProcessingStage.SORTED);
            }

            // Reallocate originals
            new FileRelocator(folderStructure.getOriginal(), folderStructure.getCopy(), settings.getFiletypes(), true).relocateFiles();

            metadataManager.save();
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

    public void deleteImages(File[] files){
        for(File file : files){
            // low-res
            if(file.delete()){
                metadataManager.getFilesData().stream().filter(m -> m.getFileName().equals(file.getName())).forEach(m -> m.setProcessingStage(FileMetadata.ProcessingStage.DELETED));
            }
            // high-res
            getOriginalCopy(file).delete();
        }
        metadataManager.save();
    }

    public void rotateImages(int degrees, File[] files){
        for(File file : files){
            try {
                // low-res
                Thumbnails.of(file)
                        .scale(1) // keep original size
                        .rotate(degrees)
                        .toFile(file);
                // high-res
                Thumbnails.of(getOriginalCopy(file))
                        .scale(1)
                        .rotate(degrees)
                        .toFile(getOriginalCopy(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void flipVertical(File[] files){
        for(File file : files){
            try {
                // low-res
                BufferedImage img = ImageIO.read(file);
                BufferedImage flippedImg = flip(img, true);
                ImageIO.write(flippedImg, "jpg", file);
                // high-res
                BufferedImage img2 = ImageIO.read(getOriginalCopy(file));
                BufferedImage flippedImg2 = flip(img2, true);
                ImageIO.write(flippedImg2, "jpg", getOriginalCopy(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void flipHorizontal(File[] files){
        for(File file : files){
            try {
                // low-res
                BufferedImage img = ImageIO.read(file);
                BufferedImage flippedImg = flip(img, false);
                ImageIO.write(flippedImg, "jpg", file);
                // high-res
                BufferedImage img2 = ImageIO.read(getOriginalCopy(file));
                BufferedImage flippedImg2 = flip(img2, false);
                ImageIO.write(flippedImg2, "jpg", getOriginalCopy(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File getOriginalCopy(File thumbnail){
        String name = thumbnail.getName();
        String parent = thumbnail.getParentFile().getName();
        return new File(new File(folderStructure.getCopy(), parent), name);
    }

    private BufferedImage flip(BufferedImage img, boolean vertical) {
        AffineTransform at = new AffineTransform();
        if (vertical) {
            at.concatenate(AffineTransform.getScaleInstance(1, -1));
            at.concatenate(AffineTransform.getTranslateInstance(0, -img.getHeight()));
        } else {
            at.concatenate(AffineTransform.getScaleInstance(-1, 1));
            at.concatenate(AffineTransform.getTranslateInstance(-img.getWidth(), 0));
        }

        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(img, null);
    }

    public Settings getSettings() { return settings; }
    public FolderStructure getFolderStructure() { return folderStructure; }

    public SettingChanger getSettingsChanger(){
        return new SettingChanger(settings, new FileManager().getConfigFile());
    }
}
