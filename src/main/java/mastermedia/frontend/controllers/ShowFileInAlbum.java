package mastermedia.frontend.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mastermedia.backend.BackendService;
import mastermedia.frontend.Main;
import mastermedia.frontend.SceneController;

import mastermedia.frontend.controllers.extra.FileStructure;
import mastermedia.frontend.controllers.extra.XMLFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;


import static mastermedia.frontend.controllers.extra.FileStructure.directoryTemporary;

public class ShowFileInAlbum implements Initializable {
    public VBox vbox;
    public ScrollPane scrollPane;
    public Label title;
    public Button editButton;
    public Button backButton;
    public AnchorPane mainPane;
    public Button rotateLeftButton;
    public Button flipHorizontallyButton;
    public Button deleteButton;
    public Button flipVerticallyButton;
    public Button rotateRightButton;
    public Pane editPanel;
    public static AtomicBoolean isClicked;

    public Pane warningPane;

    public Button acceptButtonWarning;
    public Button cancelButtonWarning;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isClicked = new AtomicBoolean(false);
        title.setText(directoryTemporary.getName());
        GridPane gridPane = FileStructure.addGridPaneFile(4,directoryTemporary,false,mainPane);
        vbox.getChildren().add(gridPane);


        scrollPane.setVvalue(0.001);
        scrollPane.setHvalue(2);
        editPanel.setVisible(false);
        warningPane.setVisible(false);



        editButton.setOnAction(event -> {
            editButton.setVisible(false);

            backButton.setOnAction(event1 -> {
                if(backButton.getText().equals("Powrót")){
                    try {
                        new SceneController().switchToScene(event, String.valueOf(XMLFile.AlBUM_CONTROLLER));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    editButton.setVisible(true);
                    editButtonAction(gridPane, false, "Edytuj", "Powrót");
                }
            });

            if (isClicked.get()) {

                editButtonAction(gridPane,false,"Edytuj","Powrót");

            } else {

                editButtonAction(gridPane,true,"Zapisz","Zakończ");

            }
        });

        rotateLeftButton.setOnAction(event -> {

            BackendService.getInstance().rotateImages(270,eliminateNullElement(FileStructure.selectedFiles));
            clearAfterOperation(gridPane,true);



            backButton.setOnAction(event1 -> {
                BackendService.getInstance().rotateImages(90,eliminateNullElement(FileStructure.selectedFiles));
                editButton.setVisible(true);
                editButtonAction(gridPane,false,"Edytuj","Powrót");

            });

        });
        rotateRightButton.setOnAction(event -> {

            BackendService.getInstance().rotateImages(90,eliminateNullElement(FileStructure.selectedFiles));
            clearAfterOperation(gridPane,true);


            backButton.setOnAction(event1 -> {
                BackendService.getInstance().rotateImages(270,eliminateNullElement(FileStructure.selectedFiles));
                editButton.setVisible(true);
                editButtonAction(gridPane,true,"Zapisz","Anuluj");


            });

        });
        flipHorizontallyButton.setOnAction(event -> {

            BackendService.getInstance().flipHorizontal(eliminateNullElement(FileStructure.selectedFiles));
            clearAfterOperation(gridPane,true);


            backButton.setOnAction(event1 -> {
                BackendService.getInstance().flipHorizontal(eliminateNullElement(FileStructure.selectedFiles));
                editButton.setVisible(true);
                editButtonAction(gridPane,true,"Zapisz","Anuluj");


            });


        });
        flipVerticallyButton.setOnAction(event -> {

            BackendService.getInstance().flipVertical(eliminateNullElement(FileStructure.selectedFiles));
            clearAfterOperation(gridPane,true);

            backButton.setOnAction(event1 -> {
                BackendService.getInstance().flipVertical(eliminateNullElement(FileStructure.selectedFiles));
                editButton.setVisible(true);
                editButtonAction(gridPane,false,"Edytuj","Powrót");


            });

        });
        deleteButton.setOnAction(event -> {
            warningPane.setVisible(true);
            acceptButtonWarning.setOnAction(eventWarning->{
                    try {
                        BackendService.getInstance().deleteImages(eliminateNullElement(FileStructure.selectedFiles));
                        new SceneController().switchToScene(eventWarning, String.valueOf(XMLFile.SHOW_FILE_IN_ALBUM));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
            });
            cancelButtonWarning.setOnAction(eventWarning->{
                editButtonAction(gridPane,false,"Edytuj","Powrót");
                warningPane.setVisible(false);
            });


        });


        backButton.setOnAction(event -> {
            try {

                new SceneController().switchToScene(event, String.valueOf(XMLFile.AlBUM_CONTROLLER));

            }catch(IOException e) {

                throw new RuntimeException(e);

            }
        });


    }



    public void editButtonAction(GridPane gridPane,Boolean edit,String editButtonLabel,String backButtonLabel){
        editButton.setText(editButtonLabel);
        backButton.setText(backButtonLabel);
        editPanel.setVisible(edit);

        isClicked.set(edit);
        clearAfterOperation(gridPane,edit);

    }

    public File [] eliminateNullElement(File [] files){
        return Arrays.stream(files)
                .filter(file -> file != null)
                .toArray(File[]::new);
    }

    public void clearAfterOperation(GridPane gridPane,Boolean edit){
        gridPane.getChildren().clear();
        gridPane.getChildren()
                .addAll(FileStructure.addGridPaneFile(4,directoryTemporary,edit,mainPane).getChildren());
        FileStructure.selectedFiles = new File[100];
    }
}
