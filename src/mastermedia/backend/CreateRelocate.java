package mastermedia.backend;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateRelocate {



        public static void main(String[] args) {
            String inputFolder = "sciezka/bezwgledna/do/katalogu/zbiorczego/ze/zdjeciami_i_filmami/do/posortowania";
            String outputFolder = "sciezka/bezwgledna/do/katalogu/gdzie/maja/byc/posortowane/zdjecia_i_filmy";
            File inputDirectory = new File(inputFolder);
            File[] files = inputDirectory.listFiles();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

            for (File file : files) {
                try {
                    BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    Date creationDate = new Date(attr.creationTime().toMillis());

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(creationDate);
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;

                    String folderType;
                    if (isImage(file.getName())) {
                        folderType = "zdjęcia";
                    } else if (isVideo(file.getName())) {
                        folderType = "filmy";
                    } else {
                        continue;
                    }

                    String folderName = sdf.format(creationDate);
                    File outputDirectory = new File(outputFolder + "/" + folderType + "/" + folderName);
                    if (!outputDirectory.exists()) {
                        outputDirectory.mkdirs();
                    }
                    Files.move(file.toPath(), outputDirectory.toPath().resolve(file.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private static boolean isImage(String fileName) {
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            return extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png");
        }

        private static boolean isVideo(String fileName) {
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            return extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("avi") || extension.equalsIgnoreCase("mov");
        }


}
