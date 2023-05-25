package mastermedia.frontend.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mastermedia.frontend.controllers.extra.Container;

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
    public Button deleteButton1;
    public TextField maxHeight;
    public TextField maxWidth;
    public TextField extra;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Container> containerList = new ArrayList<>(4) ;

        Container.FileType video = new Container.FileType(
                "video", new String[]{"mp4","mkv","avi"},
                new Container.FileType.Properties(1024,1024,5));
        Container.FileType image = new Container.FileType(
                "image", new String[]{"png","jpg","jpeg"},
                new Container.FileType.Properties(640,480,450));
        Container.FileType[] fileTypes = new Container.FileType[]{video,image};

        containerList.add(new Container("Original",fileTypes));
        containerList.add(new Container("lowResolution",fileTypes));
        containerList.add(new Container("sorted",fileTypes));

        for(Container c:containerList){
            albumComboBox.getItems().add(c.getDirectoryName());

        }
        for(Container.FileType f: containerList.get(1).getFiletype()){
            fileComboBox.getItems().add(f.getName());
            maxWidth.setText(String.valueOf(f.getProperties().maxWidth));
            maxHeight.setText(String.valueOf(f.getProperties().maxHeight));
            extra.setText(String.valueOf(f.getProperties().extra));

        }




        albumComboBox.setOnAction(event -> {
            for(int i = 0;i < containerList.size();i++) {

                for (int j = 0;j <containerList.get(i).getFiletype().length; j++) {
                    fileComboBox.setOnAction(event1 -> {


                    });


                }
            }

        });

        fileComboBox.setOnAction(event -> {




        });

        cancelButton.setOnAction(event -> {

        });

        acceptButton.setOnAction(event -> {

        });

        deleteButton.setOnAction(event -> {

        });

        deleteButton1.setOnAction(event -> {

        });

        maxHeight.setOnAction(event -> {

        });

        maxWidth.setOnAction(event -> {

        });

        extra.setOnAction(event -> {

        });


    }
}
