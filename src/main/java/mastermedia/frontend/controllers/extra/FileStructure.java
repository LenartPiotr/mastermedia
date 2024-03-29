package mastermedia.frontend.controllers.extra;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.layout.*;
import mastermedia.frontend.Main;
import mastermedia.frontend.SceneController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class FileStructure {

    public static Directory directoryTemporary = new Directory();
    public static List<File> fileList = new ArrayList<>();
    public static int filePosition;

    public static File[] selectedFiles = new File[100];

    public static GridPane addGridPaneFile(int column, Directory directory, Boolean edit, AnchorPane anchorPane){

        fileList = directory.getFileList();
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(20);


        int columnCount = 0;
        int rowCount = 0;

        for (int i = 0; i < fileList.size();i++) {

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("file_container.fxml"));


            try {
                Pane pane = fxmlLoader.load();
                if(fileList.get(i).isFile()) {

                    FileItemController fileItemController = fxmlLoader.getController();
                    fileItemController.showImageInAlbum(directory, i, edit);


                    gridPane.add(pane, columnCount, rowCount);


                    columnCount++;

                    if (columnCount == column) {
                        columnCount = 0;
                        rowCount++;
                    }


                    int positionOfFile = i;

                    if (!edit) {
                        pane.setOnMouseClicked(mouseEvent -> {
                            filePosition = positionOfFile;
                            FXMLLoader fxmlLoaderFocusOnImage = new FXMLLoader(Main.class.getResource("focus_on_file.fxml"));
                            try {
                                Pane pane1 = fxmlLoaderFocusOnImage.load();
                                anchorPane.getChildren().add(pane1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });
                    } else {
                        fileItemController.checkBox.setVisible(true);

                        AtomicBoolean isClicked = new AtomicBoolean(true);
                        fileItemController.checkBox.setOnAction(event -> {

                            if (isClicked.get()) {
                                FileStructure.selectedFiles[positionOfFile] = fileList.get(positionOfFile);
                                System.out.println(fileList.get(positionOfFile));
                                fileItemController.selected_pane.setVisible(true);
                                isClicked.set(false);

                            } else {
                                FileStructure.selectedFiles[positionOfFile] = null;
                                fileItemController.selected_pane.setVisible(false);
                                isClicked.set(true);
                            }

                        });
                    }
                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
                }


        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();
        ColumnConstraints column4 = new ColumnConstraints();

        // Wyśrodkowanie kolumn
        column1.setHalignment(HPos.CENTER);
        column2.setHalignment(HPos.CENTER);
        column3.setHalignment(HPos.CENTER);
        column4.setHalignment(HPos.CENTER);

        // Dodanie ColumnConstraints do GridPane
        gridPane.getColumnConstraints().addAll(column1, column2, column3,column4);


        return gridPane;
    }

    public static GridPane addGridPaneDirectory(int column, List<Directory> directoryList, double heightImage, double widthImage){

        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(20);

        int columnCount = 0;
        int rowCount = 0;

        for (Directory directory : directoryList) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("file_container.fxml"));

            try {
                Pane pane = fxmlLoader.load();

                pane.setOnMouseClicked(mouseEvent -> {
                    try {
                        directoryTemporary = directory;
                        new SceneController().switchToScene(mouseEvent, String.valueOf(XMLFile.SHOW_FILE_IN_ALBUM));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                FileItemController fileItemController = fxmlLoader.getController();
                fileItemController.setData(directory);




//                #TODO zrobić skalowalny obraz
                fileItemController.albumPane.setFitWidth(widthImage);
                fileItemController.albumPane.setFitHeight(heightImage);
                fileItemController.clip.setWidth(widthImage);
                fileItemController.clip.setHeight(heightImage);
                fileItemController.clip.setArcHeight(20);
                fileItemController.clip.setArcWidth(20);

                gridPane.add(pane, columnCount, rowCount);


                columnCount++;

                if (columnCount == column) {
                    columnCount = 0;
                    rowCount++;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        return gridPane;
    }


}
