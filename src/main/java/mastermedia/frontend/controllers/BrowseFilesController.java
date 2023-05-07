package mastermedia.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import mastermedia.frontend.Main;
import mastermedia.frontend.controllers.extra.File;
import mastermedia.frontend.controllers.extra.FileItemController;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BrowseFilesController implements Initializable {
    public ScrollPane scrollPane;
    public Pane mainPane;
    public GridPane GridPaneForFiles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<File> files = loadFiles();

        int columnCount = 0;
        int rowCount = 0;

        for (File file : files) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("file_container.fxml"));

            try {
                Pane pane = fxmlLoader.load();
                FileItemController fileItemController = fxmlLoader.getController();
                fileItemController.setData(file);

                GridPaneForFiles.add(pane, columnCount, rowCount);

                columnCount++;

                if (columnCount == 3) { // Number of columns per row
                    columnCount = 0;
                    rowCount++;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();

        // Wyśrodkowanie kolumn
        column1.setHalignment(HPos.CENTER);
        column2.setHalignment(HPos.CENTER);
        column3.setHalignment(HPos.CENTER);

        // Dodanie ColumnConstraints do GridPane
        GridPaneForFiles.getColumnConstraints().addAll(column1, column2, column3);


        mainPane.boundsInParentProperty().addListener((observable, oldValue, newValue) -> resizePane(newValue));

        // Inicjalne dostosowanie rozmiaru Pane do zawartości GridPane
        resizePane(mainPane.getBoundsInParent());

    }


    public List<File> loadFiles(){
        List<File> ls = new ArrayList<>();

        for(int i= 0; i<20;i++){
            File file = new File();
            file.setNameFile("Album nr" + i);
            ls.add(file);
        }


        return ls;
    }


    private void resizePane(Bounds bounds) {
        double newWidth = bounds.getMaxX();
        double newHeight = bounds.getMaxY();

        mainPane.setPrefWidth(newWidth);
        mainPane.setPrefHeight(newHeight);
    }

}

