package mastermedia.backend.settings;

import mastermedia.backend.settings.properties.FileType;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SettingChanger {
    private Settings settings;
    private File pathToSettings;

    private String message;

    public SettingChanger(Settings settings, File pathToSettings){
        this.settings = new Settings(settings);
        this.pathToSettings = pathToSettings;
        message = " ";
    }

    // Directories

    public String getDirectoriesOriginal(){ return settings.getDirectories().getOriginal(); }
    public String getDirectoriesLowResolution(){ return settings.getDirectories().getLowResolution(); }
    public String getDirectoriesSorted(){ return settings.getDirectories().getSorted(); }
    public String getDirectoriesCopy(){ return settings.getDirectories().getCopy(); }

    public boolean setDirectoriesOriginal(String value){ settings.getDirectories().setOriginal(value); return true; }
    public boolean setDirectoriesLowResolution(String value){ settings.getDirectories().setLowResolution(value); return true; }
    public boolean setDirectoriesSorted(String value){ settings.getDirectories().setSorted(value); return true; }
    public boolean setDirectoriesCopy(String value){ settings.getDirectories().setCopy(value); return true; }

    public String getDirectoriesOriginalDescription(){ return "Ścieżka do katalogu, gdzie przechowywane są oryginały obrazów"; }
    public String getDirectoriesLowResolutionDescription(){ return "Ścieżka do katalogu, gdzie tymczasowo są przechowywane pomniejszone multimedia"; }
    public String getDirectoriesSortedDescription(){ return "Ścieżka do katalogu, gdzie przechowywane są posortowane i pomniejszone multimedia"; }
    public String getDirectoriesCopyDescription(){ return "Ścieżka do katalogu, gdzie przechowywane są posortowane kopie oryginałów"; }

    // Filetypes

    public List<FileType> getFileTypes() { return settings.getFiletypes().getFileTypeList(); }
    public boolean setFileTypes(List<FileType> list) { settings.getFiletypes().setFileTypeList(list); return true; }

    // Thumbnails

    public int getImageMaxWidth() { return settings.getThumbnails().getMaxWidth(); }
    public int getImageMaxHeight() { return settings.getThumbnails().getMaxHeight(); }
    public int getImageQuality() { return settings.getThumbnails().getQuality(); }

    public boolean setImageMaxWidth(int value) { settings.getThumbnails().setMaxWidth(value); return true; }
    public boolean setImageMaxHeight(int value) { settings.getThumbnails().setMaxHeight(value); return true; }
    public boolean setImageQuality(int value) { settings.getThumbnails().setQuality(value); return true; }

    public String getImageMaxWidthDescription() { return "Maksymalny rozmiar szerokości zdjęcia po pomniejszeniu (To nie jest wyjściowa wielkość. Zdjęcia będą skalowane proporcjonalnie)"; }
    public String getImageMaxHeightDescription() { return "Maksymalny rozmiar wysokości zdjęcia po pomniejszeniu (To nie jest wyjściowa wielkość. Zdjęcia będą skalowane proporcjonalnie)"; }
    public String getImageQualityDescription() { return "Skala kompresji zdjęcia od 1 do 10"; }

    // Toenails

    public int getVideoMaxWidth() { return settings.getToenails().getMaxWidth(); }
    public int getVideoMaxHeight() { return settings.getToenails().getMaxHeight(); }
    public int getVideoBitrate() { return settings.getToenails().getBitrate(); }
    public String getVideoFps() { return settings.getToenails().getFps(); }

    public boolean setVideoMaxWidth(int value) { settings.getToenails().setMaxWidth(value); return true; }
    public boolean setVideoMaxHeight(int value) { settings.getToenails().setMaxHeight(value); return true; }
    public boolean setVideoBitrate(int value) { settings.getToenails().setBitrate(value); return true; }
    public boolean setVideoFps(String value) { settings.getToenails().setFps(value); return true; }

    public String getVideoMaxWidthDescription() { return "Maksymalny rozmiar szerokości wideo po pomniejszeniu (To nie jest wyjściowa wielkość. Zdjęcia będą skalowane proporcjonalnie)"; }
    public String getVideoMaxHeightDescription() { return "Maksymalny rozmiar wysokości wideo po pomniejszeniu (To nie jest wyjściowa wielkość. Zdjęcia będą skalowane proporcjonalnie)"; }
    public String getVideoBitrateDescription() { return "Wyjściowy bitrate wideo"; }
    public String getVideoFpsDescription() { return "Wyjściowa ilość FPS (liczba lub \"original\")"; }

    public boolean save(){
        try {
            PrintWriter writer = new PrintWriter(pathToSettings);

            DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

            Representer representer = new Representer(options);
            representer.addClassTag(Settings.class, Tag.MAP);

            Yaml yaml = new Yaml(representer, options);

            yaml.dump(settings, writer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getMessage(){
        return this.message;
    }
}
