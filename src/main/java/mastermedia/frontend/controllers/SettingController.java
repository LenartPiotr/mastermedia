package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import mastermedia.backend.BackendService;
import mastermedia.backend.settings.SettingChanger;
import mastermedia.backend.settings.properties.FileType;
import mastermedia.frontend.SceneController;
import mastermedia.frontend.controllers.extra.XMLFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SettingController implements Initializable {
    public ComboBox albumComboBox;
    public ComboBox fileComboBox;
    public Button cancelButton;
    public Button acceptButton;
    public Button deleteButton;
    public TextField maxHeight;
    public TextField maxWidth;
    public TextField extra;
    public VBox menuView;
    public TextField browseTextFile;
    public Button browseButton;
    public TextField addNewTypeFile;

    public AnchorPane mainAnchorPane;
    public Button deleteButtonTypeFile;
    public ComboBox extensionsComboBox;
    public TextField addNewExtensions;
    public Button deleteButtonExtensions;

    public Label extraLabel;
    public Label extraLabel1;
    public TextField extra1;

    public Pane compressionPane;
    public Line divisionLine;
    public Button addButtonTypeFile;
    public Button addButtonExtension;
    public Pane extensionPane;
    public Pane browsePane;
    public Button addBrowsePane;
    public Pane savedChanges;
    public Button buttonOK;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            BackendService.getInstance().init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SettingChanger settingChangerStart = BackendService.getInstance().getSettingsChanger();


        compressionPane.setVisible(false);
        divisionLine.setVisible(false);
        addNewExtensions.setVisible(false);
        addNewTypeFile.setVisible(false);
        extensionPane.setVisible(false);
        browsePane.setVisible(false);
        savedChanges.setVisible(false);



        startSetup(settingChangerStart);


        cancelButton.setOnAction(event -> {
            try {

                new SceneController().switchToScene(event, String.valueOf(XMLFile.SETTINGS_VIEW));

            }catch(IOException e) {

                throw new RuntimeException(e);

            }

        });

        acceptButton.setOnAction(event -> {
            if(settingChangerStart.save()) {
                savedChanges.setVisible(true);
                buttonOK.setOnAction(event1 -> savedChanges.setVisible(false));
            }
        });




        deleteButtons();


        browseButton.setOnAction(event -> {
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            Stage stage = (Stage) mainAnchorPane.getScene().getWindow();
            File file = directoryChooser.showDialog(stage);

            if (file != null) {
                browseTextFile.setText(file.getAbsolutePath());
                browseTextFile.clear();
                albumComboBox.getItems().add(browseTextFile.getText());


            }


        });

        addNewTypeFile.setOnAction(event -> {
            String newFileType = addNewTypeFile.getText();
            addNewTypeFile.clear();


            fileComboBox.getItems().add(newFileType);

            FileType newFileTypeObj = new FileType();
            newFileTypeObj.setName(newFileType);
            newFileTypeObj.setExtensions(new ArrayList<>());


            List<FileType> fileTypes = settingChangerStart.getFileTypes();
            fileTypes.add(newFileTypeObj);
            settingChangerStart.setFileTypes(fileTypes);

            extensionsComboBox.getItems().clear();
            for (FileType fileType : settingChangerStart.getFileTypes()) {
                extensionsComboBox.getItems().add(fileType.getName());
            }
            addNewTypeFile.setVisible(false);
            addButtonTypeFile.setVisible(true);
        });

        addButtonTypeFile.setOnAction(event -> {
            addNewTypeFile.setVisible(true);
            addButtonTypeFile.setVisible(false);
        });

        addNewExtensions.setOnAction(event -> {
            String newExtension = addNewExtensions.getText();
            addNewExtensions.clear();


            extensionsComboBox.getItems().add(newExtension);


            int selectedIndex = fileComboBox.getSelectionModel().getSelectedIndex();

            extensionsComboBox.getItems().clear();
            List<String> ex = settingChangerStart.getFileTypes().get(selectedIndex).getExtensions();
            ex.add(newExtension);
            settingChangerStart.getFileTypes().get(selectedIndex).setExtensions(ex);
            extensionsComboBox.getItems().addAll(settingChangerStart.getFileTypes().get(selectedIndex).getExtensions());
            addNewExtensions.setVisible(false);
            addButtonExtension.setVisible(true);
        });

        addButtonExtension.setOnAction(event -> {
            addNewExtensions.setVisible(true);
            addButtonExtension.setVisible(false);

        });

        addBrowsePane.setOnAction(event -> {
            browsePane.setVisible(true);
            addBrowsePane.setVisible(false);
        });

    }

    public void startSetup(SettingChanger settingChangerStart){
        String directoriesCopy = settingChangerStart.getDirectoriesCopy();
        albumComboBox.getItems().add(directoriesCopy);

        String directoriesLowResolution = settingChangerStart.getDirectoriesLowResolution();
        albumComboBox.getItems().add(directoriesLowResolution);

        String directoriesOriginal = settingChangerStart.getDirectoriesOriginal();
        albumComboBox.getItems().add(directoriesOriginal);

        String directoriesSorted = settingChangerStart.getDirectoriesSorted();
        albumComboBox.getItems().add(directoriesSorted);

        albumComboBox.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item);
                    if(item.equals(directoriesCopy))
                        setTooltip(new Tooltip(settingChangerStart.getDirectoriesCopyDescription()));

                    if(item.equals(directoriesLowResolution))
                        setTooltip(new Tooltip(settingChangerStart.getDirectoriesLowResolutionDescription()));

                    if(item.equals(directoriesOriginal))
                        setTooltip(new Tooltip(settingChangerStart.getDirectoriesOriginalDescription()));

                    if(item.equals(directoriesSorted))
                        setTooltip(new Tooltip(settingChangerStart.getDirectoriesSortedDescription()));

                }
            }
        });


        for (FileType f: settingChangerStart.getFileTypes())
            fileComboBox.getItems().add(f.getName());

        fileComboBox.setOnAction(event -> {
            addNewTypeFile.setVisible(false);
            addButtonTypeFile.setVisible(true);
            divisionLine.setVisible(true);
            compressionPane.setVisible(true);

            for(int i = 0; i<fileComboBox.getChildrenUnmodifiable().size();i++) {

                if (fileComboBox.getSelectionModel().getSelectedIndex() == i) {
                    extensionPane.setVisible(true);


                    extensionsComboBox.getItems().clear();
                    if(i == 0){
                        extra1.setVisible(true);
                        extraLabel1.setVisible(true);
                        extraLabel.setText("FPS");
                        extraLabel1.setText("Bitrate");

                        setProperties(
                                maxWidth,
                                String.valueOf(settingChangerStart.getVideoMaxWidth()),
                                settingChangerStart.getVideoMaxWidthDescription()
                        );
                        setProperties(
                                maxHeight,
                                String.valueOf(settingChangerStart.getVideoMaxHeight()),
                                settingChangerStart.getVideoMaxHeightDescription()
                        );
                        setProperties(
                                extra,
                                String.valueOf(settingChangerStart.getVideoFps()),
                                settingChangerStart.getVideoFpsDescription()
                        );

                        setProperties(
                                extra1,
                                String.valueOf(settingChangerStart.getVideoBitrate()),
                                settingChangerStart.getVideoBitrateDescription()
                        );
                        maxWidth.setOnAction(event1 -> {
                            settingChangerStart.setVideoMaxWidth(Integer.parseInt(maxWidth.getText()));
                            maxWidth.setPromptText(maxWidth.getText());
                            maxWidth.clear();
                        });

                        maxHeight.setOnAction(event1 -> {
                            settingChangerStart.setVideoMaxHeight(Integer.parseInt(maxHeight.getText()));
                            maxHeight.setPromptText(maxHeight.getText());
                            maxHeight.clear();
                        });

                        extra.setOnAction(event1 -> {
                            settingChangerStart.setVideoBitrate(Integer.parseInt(extra.getText()));
                            extra.setPromptText(extra.getText());
                            extra.clear();
                        });

                        extra1.setOnAction(event1 -> {
                            settingChangerStart.setVideoFps(extra1.getText());
                            extra1.setPromptText(extra1.getText());
                            extra1.clear();
                        });

                    }

                    if(i == 1){

                        extraLabel.setText("Jakość");
                        extraLabel1.setVisible(false);
                        extra1.setVisible(false);
                        setProperties(
                                maxWidth,
                                String.valueOf(settingChangerStart.getImageMaxWidth()),
                                settingChangerStart.getImageMaxWidthDescription()
                        );
                        setProperties(
                                maxHeight,
                                String.valueOf(settingChangerStart.getImageMaxHeight()),
                                settingChangerStart.getImageMaxHeightDescription()
                        );
                        setProperties(
                                extra,
                                String.valueOf(settingChangerStart.getImageQuality()),
                                settingChangerStart.getImageQualityDescription()
                        );

                        maxWidth.setOnAction(event1 -> {
                            settingChangerStart.setImageMaxWidth(Integer.parseInt(maxWidth.getText()));
                            maxWidth.clear();
                        });

                        maxHeight.setOnAction(event1 -> {
                            settingChangerStart.setImageMaxHeight(Integer.parseInt(maxHeight.getText()));
                            maxHeight.clear();
                        });

                        extra.setOnAction(event1 -> {
                            settingChangerStart.setImageQuality(Integer.parseInt(extra.getText()));
                            extra.clear();
                        });

                    }


                    for (String e : settingChangerStart.getFileTypes().get(i).getExtensions()) {
                        extensionsComboBox.getItems().add(e);


                    }
                }
            }
        });


    }

    public void deleteButtons(){
        deleteButton.setOnAction(event -> {
            deleteItemsInCombobox(albumComboBox);
        });

        deleteButtonTypeFile.setOnAction(event -> {
            deleteItemsInCombobox(fileComboBox);
        });

        deleteButtonExtensions.setOnAction(event -> {
            deleteItemsInCombobox(extensionsComboBox);
        });


    }

    public void deleteItemsInCombobox(ComboBox comboBox){

        int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            comboBox.getItems().remove(selectedIndex);
            comboBox.getSelectionModel().clearSelection();
        }

    }

    public void setProperties(TextField textField,String value,String info){

        textField.setPromptText(value);
        Tooltip tooltip = new Tooltip(info);
        Tooltip.install(textField, tooltip);

    }





}
