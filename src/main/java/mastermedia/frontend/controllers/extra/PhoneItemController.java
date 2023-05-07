package mastermedia.frontend.controllers.extra;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PhoneItemController implements Initializable {
    @FXML
    public Button connectButton;
    public Label namePhone;
    public ImageView imgPhone;

    public void setData(Phone phone){
//        Image image = new Image(getClass().getResourceAsStream(phone.getImgPath()));
//        imgPhone.setImage(image);

        namePhone.setText(phone.getName());


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectButton.setOnAction(event -> connectButton.setVisible(true));

    }
}
