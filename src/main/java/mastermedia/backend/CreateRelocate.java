package mastermedia.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import mastermedia.backend.settings.FileTypes;

public class CreateRelocate {

    private File inputPath;
    private File outputPath;
    private Map<String, String> fileTypeMap;
    private static Map<Integer, String> monthMap = Map.ofEntries(Map.entry(1, "Styczeń"), Map.entry(2, "Luty"), Map.entry(3, "Marzec"),
                                                                 Map.entry(4, "Kwiecień"), Map.entry(5, "Maj"), Map.entry(6, "Czerwiec"),
                                                                 Map.entry(7, "Lipiec"), Map.entry(8, "Sierpień"), Map.entry(9, "Wrzesień"),
                                                                 Map.entry(10, "Październik"), Map.entry(11, "Listopad"), Map.entry(12, "Grudzień"));

    public CreateRelocate(File inputPath, File outputPath, FileTypes config) {

        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.fileTypeMap = parseFileTypes(config);

    }

    public void relocateFiles() {

        if(!inputPath.isDirectory()) { return; }

        for(File file : inputPath.listFiles()) {

            try {

                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                LocalDate creationDate = LocalDate.ofInstant(attr.creationTime().toInstant(), ZoneId.systemDefault());
                String folderType = fileTypeMap.get(getFileExtension(file.getName()));

                if(folderType == null) { folderType = "Unknown"; }

                File outputDirectory = new File(outputPath, monthMap.get(creationDate.getMonthValue()) + " " + creationDate.getYear() + " " + folderType);

                if(!outputDirectory.exists()) { outputDirectory.mkdirs(); }

                Files.move(file.toPath(), outputDirectory.toPath().resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);

            }catch(Exception e) {

                e.printStackTrace();

            }

        }

    }

    private Map<String, String> parseFileTypes(FileTypes config) {

        Map<String, String> map = new HashMap<>();

        for(String folder : config.getVideo()) map.put(folder, "Video");

        for(String folder : config.getImage()) map.put(folder, "Image");

        return map;

    }

    private Map<String, String> readConfigFile(String configFilePath) {

        Map<String, String> map = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {

            String line;

            while((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if(parts.length == 2) { map.put(parts[0], parts[1]); }

            }

        }catch(Exception e) {

            e.printStackTrace();

        }

        return map;

    }

    private String getFileExtension(String fileName) {

        int lastDotIndex = fileName.lastIndexOf(".");

        if(lastDotIndex == -1) { return ""; }

        return fileName.substring(lastDotIndex + 1);

    }

}
