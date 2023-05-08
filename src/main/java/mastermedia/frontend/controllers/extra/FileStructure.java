package mastermedia.frontend.controllers.extra;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import mastermedia.frontend.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStructure {
    public static void setMainVbox(VBox vbox){

        for(int i = 0; i < 2;i++) {
            ImageView imageView = new ImageView();
            imageView.setFitHeight(13);
            imageView.setFitWidth(752);

            GridPane gridPane = addGridPane(4, loadFiles(),127,186);


            try {
                imageView.setImage(new Image(Main.class.getResource("/mastermedia/frontend/img/line.png").openStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Label albumTitle = new Label("Album nr " + i );
            albumTitle.setFont(new Font("System",20));
            Pane paneLabel = new Pane(albumTitle);
            paneLabel.setPadding(new Insets(0, 0, 10, 0));
            vbox.getChildren().add(paneLabel);

            vbox.getChildren().add(gridPane);

            Pane pane = new Pane(imageView);
            pane.setMaxSize(752,13);

            pane.setPadding(new Insets(0, 0, 10, 0));


            vbox.getChildren().add(pane);

        }

        vbox.boundsInParentProperty().addListener((observable, oldValue, newValue) -> resizePane(newValue,vbox));

        // Inicjalne dostosowanie rozmiaru Pane do zawartości GridPane
        resizePane(vbox.getBoundsInParent(),vbox);



    }

    public static GridPane addGridPane(int column, List<File> files,double heightImage, double widthImage){

        GridPane gridPane = new GridPane();

        int columnCount = 0;
        int rowCount = 0;

        for (File file : files) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("file_container.fxml"));

            try {
                Pane pane = fxmlLoader.load();
                FileItemController fileItemController = fxmlLoader.getController();
                fileItemController.setData(file);


//                #TODO zrobić skalowalny obraz
//                fileItemController.albumPane.setFitWidth(widthImage);
//                fileItemController.albumPane.setFitHeight(heightImage);
//                fileItemController.clip.setWidth(widthImage);
//                fileItemController.clip.setHeight(heightImage);
//                fileItemController.clip.setArcHeight(20);
//                fileItemController.clip.setArcWidth(20);

                gridPane.add(pane, columnCount, rowCount);


                columnCount++;

                if (columnCount == column) { // Number of columns per row
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


    public static List<File> loadFiles(){
        List<File> ls = new ArrayList<>();

        for(int i= 0; i<20;i++){
            File file = new File();
            file.setNameFile(" ");
            ls.add(file);
        }


        return ls;
    }


    private static void resizePane(Bounds bounds, VBox mainVbox) {
        double newWidth = bounds.getMaxX();
        double newHeight = bounds.getMaxY();

        mainVbox.setPrefWidth(newWidth);
        mainVbox.setPrefHeight(newHeight);
    }

}
