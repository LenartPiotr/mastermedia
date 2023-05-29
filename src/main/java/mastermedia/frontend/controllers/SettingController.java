package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
import java.util.concurrent.atomic.AtomicReference;


public class SettingController implements Initializable {
    public ComboBox albumComboBox;
    public ComboBox fileComboBox;
    public Button cancelButton;
    public Button acceptButton;

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



    public Pane compressionPane;
    public Line divisionLine;
    public Button addButtonTypeFile;
    public Button addButtonExtension;
    public Pane extensionPane;
    public Pane browsePane;

    public Pane savedChanges;
    public Button buttonOK;
    public Button settings;
    public Button folder;
    public Label warningLabel;
    public Pane warningPane;
    public TextField maxHeight1;
    public TextField maxWidth1;
    public TextField Quality;
    public Pane pane;
    public TextField bitrate;
    public TextField fps;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            BackendService.getInstance().init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SettingChanger settingChangerStart = BackendService.getInstance().getSettingsChanger();
        SettingChanger settingChangerCancel = BackendService.getInstance().getSettingsChanger();

        settingsMenu();

        compressionPane.setVisible(false);
        divisionLine.setVisible(false);
        addNewExtensions.setVisible(false);
        addNewTypeFile.setVisible(false);
        extensionPane.setVisible(false);
        browsePane.setVisible(false);
        savedChanges.setVisible(false);
        warningPane.setVisible(false);


        browseTextFile.setOnAction(event -> {});

        startSetup(settingChangerStart);

        cancelButton.setOnAction(event -> {

            try {

                new SceneController().switchToScene(event, String.valueOf(XMLFile.BROWSE_FILES_ALBUM_VIEW));

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



    }

    public void startSetup(SettingChanger settingChangerStart){
        String directoriesCopy = settingChangerStart.getDirectoriesCopy();
        albumComboBox.getItems().add("directoriesCopy");

        String directoriesLowResolution = settingChangerStart.getDirectoriesLowResolution();
        albumComboBox.getItems().add("directoriesLowResolution");

        String directoriesOriginal = settingChangerStart.getDirectoriesOriginal();
        albumComboBox.getItems().add("directoriesOriginal");

        String directoriesSorted = settingChangerStart.getDirectoriesSorted();
        albumComboBox.getItems().add("directoriesSorted");

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

        albumComboBox.setOnAction(event -> {
            browsePane.setVisible(true);

            int selectedIndex = albumComboBox.getSelectionModel().getSelectedIndex();

            switch (selectedIndex) {
                case 0 -> {
                    browseTextFile.setPromptText(settingChangerStart.getDirectoriesCopy());
                }
                case 1 -> {
                    browseTextFile.setPromptText(settingChangerStart.getDirectoriesLowResolution());
                }
                case 2 -> {

                    browseTextFile.setPromptText(settingChangerStart.getDirectoriesOriginal());
                }
                case 3 -> {
                    browseTextFile.setPromptText(settingChangerStart.getDirectoriesSorted());

                }

                default -> browseTextFile.clear();
            }

            browseAlbum(selectedIndex,settingChangerStart);

        });


        for (FileType f: settingChangerStart.getFileTypes())
            fileComboBox.getItems().add(f.getName());

        fileComboBox.setOnAction(event -> {
            addNewTypeFile.setVisible(false);
            addButtonTypeFile.setVisible(true);
            divisionLine.setVisible(true);
            compressionPane.setVisible(true);

            for(int i = 0; i<fileComboBox.getChildrenUnmodifiable().size();i++) {
                extensionPane.setVisible(true);
                extensionsComboBox.getItems().clear();

                if (fileComboBox.getSelectionModel().getSelectedIndex() == i) {

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
                            fps,
                            String.valueOf(settingChangerStart.getVideoFps()),
                            settingChangerStart.getVideoFpsDescription()
                    );

                    setProperties(
                            bitrate,
                            String.valueOf(settingChangerStart.getVideoBitrate()),
                            settingChangerStart.getVideoBitrateDescription()
                    );
                    maxWidth.setOnAction(event1 -> {
                        try {
                            settingChangerStart.setVideoMaxWidth(Integer.parseInt(maxWidth.getText()));
                            maxWidth.setPromptText(maxWidth.getText());
                            maxWidth.clear();
                            maxWidth.setStyle("-fx-text-fill: white;");
                        } catch (NumberFormatException e) {
                            maxHeight.setStyle("-fx-text-fill: red;");

                        }
                    });

                    maxHeight.setOnAction(event1 -> {
                        try {
                            settingChangerStart.setVideoMaxHeight(Integer.parseInt(maxHeight.getText()));
                            maxHeight.setPromptText(maxHeight.getText());
                            maxHeight.clear();
                            maxHeight.setStyle("-fx-text-fill: white;");
                        } catch (NumberFormatException e) {
                            maxHeight.setStyle("-fx-text-fill: red;");

                        }
                    });

                    fps.setOnAction(event1 -> {
                        try {
                            settingChangerStart.setVideoBitrate(Integer.parseInt(fps.getText()));
                            fps.setPromptText(fps.getText());
                            fps.clear();
                            fps.setStyle("-fx-text-fill: white;");
                        } catch (NumberFormatException e) {
                            maxHeight.setStyle("-fx-text-fill: red;");

                        }

                    });

                    bitrate.setOnAction(event1 -> {
                        try {
                            settingChangerStart.setVideoFps(bitrate.getText());
                            bitrate.setPromptText(bitrate.getText());
                            bitrate.clear();
                            bitrate.setStyle("-fx-text-fill: white;");
                        } catch (NumberFormatException e) {
                            maxHeight.setStyle("-fx-text-fill: red;");

                        }
                    });


                    setProperties(
                            maxWidth1,
                            String.valueOf(settingChangerStart.getImageMaxWidth()),
                            settingChangerStart.getImageMaxWidthDescription()
                    );
                    setProperties(
                            maxHeight1,
                            String.valueOf(settingChangerStart.getImageMaxHeight()),
                            settingChangerStart.getImageMaxHeightDescription()
                    );
                    setProperties(
                            Quality,
                            String.valueOf(settingChangerStart.getImageQuality()),
                            settingChangerStart.getImageQualityDescription()
                    );

                    maxWidth1.setOnAction(event1 -> {
                        try {
                            settingChangerStart.setImageMaxWidth(Integer.parseInt(maxWidth.getText()));
                            maxWidth.setPromptText(maxWidth.getText());
                            maxWidth.clear();
                            maxWidth.setStyle("-fx-text-fill: white;");
                        } catch (NumberFormatException e) {
                            maxHeight.setStyle("-fx-text-fill: red;");

                        }
                    });

                    maxHeight1.setOnAction(event1 -> {
                        maxHeight.setStyle("-fx-text-fill: white;");

                        try {
                            int value = Integer.parseInt(maxHeight.getText());
                            settingChangerStart.setImageMaxHeight(value);
                            maxHeight.setPromptText(maxHeight.getText());
                            maxHeight.clear();
                        } catch (NumberFormatException e) {
                            maxHeight.setStyle("-fx-text-fill: red;");

                        }
                    });

                    Quality.setOnAction(event1 -> {

                        try {
                            int quality = Integer.parseInt(extra.getText());
                            if (quality >= 0 && quality <= 10) {
                                extra.setStyle("-fx-text-fill: white;");
                                settingChangerStart.setImageQuality(quality);
                                extra.setPromptText(extra.getText());
                                extra.clear();
                            } else {
                                extra.setStyle("-fx-text-fill: red;");

                            }
                        } catch (NumberFormatException e) {
                            maxHeight.setStyle("-fx-text-fill: red;");

                        }
                    });


                    for (String e : settingChangerStart.getFileTypes().get(i).getExtensions()) {
                        extensionsComboBox.getItems().add(e);

                    }
                }
            }

        });
    }

    public void deleteButtons(){

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

    public void settingsMenu(){
        settings.setTextFill(Color.WHITE);
        folder.setOnAction(event -> {

            settings.setTextFill(Color.web("#707070"));
            try {

                new SceneController().switchToScene(event, String.valueOf(XMLFile.AlBUM_CONTROLLER));

            }catch(IOException e) {

                throw new RuntimeException(e);

            }

        });

        settings.setOnAction(event -> {

            try {

                new SceneController().switchToScene(event, String.valueOf(XMLFile.SETTINGS_VIEW));

            }catch(IOException e) {

                throw new RuntimeException(e);

            }

        });
    }


    public void browseAlbum(int i,SettingChanger settingChangerStart) {
        browseButton.setOnAction(event -> {
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            Stage stage = (Stage) mainAnchorPane.getScene().getWindow();
            File file = directoryChooser.showDialog(stage);

            if (file != null) {
                browseTextFile.setText(file.getAbsolutePath());
                browseTextFile.setPromptText(file.getAbsolutePath());
            }

            switch (i) {
                case 0 -> {
                    settingChangerStart.setDirectoriesCopy(browseTextFile.getText());
                }
                case 1 -> {
                    settingChangerStart.setDirectoriesLowResolution(browseTextFile.getText());
                }
                case 2 -> {

                    settingChangerStart.setDirectoriesOriginal(browseTextFile.getText());
                }
                case 3 -> {
                    browseTextFile.setPromptText(settingChangerStart.getDirectoriesSorted());
                    settingChangerStart.setDirectoriesSorted(browseTextFile.getText());
                }

                default -> browseTextFile.clear();
            }
        });
    }

}
