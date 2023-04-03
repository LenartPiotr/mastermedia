package mastermedia.backend;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.Map;

public class CreateRelocate {

    private static Map<Integer, String> month_map = Map
        .ofEntries(Map.entry(1, "Styczeń"), Map.entry(2, "Luty"), Map.entry(3, "Marzec"), Map.entry(4, "Kwiecień"),
                   Map.entry(5, "Maj"), Map.entry(6, "Czerwiec"), Map.entry(7, "Lipiec"), Map.entry(8, "Sierpień"),
                   Map.entry(9, "Wrzesień"), Map.entry(10, "Październik"), Map.entry(11, "Listopad"),
                   Map.entry(12, "Grudzień"));

    public void relocateFiles(String inputPath, String outputPath) {

        File inputDirectory = new File(inputPath);

        if(!inputDirectory.isDirectory()) return;

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        for(File file : inputDirectory.listFiles()) {

            try {

                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                // Date creationDate = new Date(attr.creationTime().toMillis());
                LocalDate creationDate = LocalDate.from(attr.creationTime().toInstant());

                // Calendar calendar = Calendar.getInstance();
                // calendar.setTime(creationDate);
                // int year = calendar.get(Calendar.YEAR);
                // int month = calendar.get(Calendar.MONTH) + 1;

                String folderType;

                if(isImage(file.getName())) {

                    folderType = "Zdjęcia";

                }else if(isVideo(file.getName())) {

                    folderType = "Filmy";

                }else {

                    continue;

                }

                File outputDirectory = new File(outputPath + "/" + month_map.get(creationDate.getMonthValue()) + " " +
                                                creationDate.getYear() + " " + folderType);

                if(!outputDirectory.exists()) { outputDirectory.mkdirs(); }

                Files.move(file.toPath(), outputDirectory.toPath().resolve(file.getName()));

                i++;

            }catch(Exception e) {

                e.printStackTrace();

            }

        }

    }

    private static boolean isImage(String fileName) {

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") ||
               extension.equalsIgnoreCase("png");

    }

    private static boolean isVideo(String fileName) {

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("avi") ||
               extension.equalsIgnoreCase("mov");

    }

}
