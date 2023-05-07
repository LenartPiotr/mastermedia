package mastermedia.frontend.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import mastermedia.frontend.Main;
import mastermedia.frontend.controllers.extra.Phone;
import mastermedia.frontend.controllers.extra.PhoneItemController;

public class HomePageController implements Initializable {

    public AnchorPane paneForPhones;
    public GridPane gridPaneForPhones;
    @FXML
    Button reloadButton;



    public Scene createScene() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home_page_view.fxml"));
        return new Scene(fxmlLoader.load());

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Phone> phones = loadPhones();

        int columnCount = 0;
        int rowCount = 0;

        for (Phone phone : phones) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("phone_container.fxml"));

            try {
                Pane pane = fxmlLoader.load();
                PhoneItemController phoneItemController = fxmlLoader.getController();
                phoneItemController.setData(phone);

                Insets paneInsets = new Insets(10); // Ustawienie marginesu na 10 pikseli
                GridPane.setMargin(pane, paneInsets);
                gridPaneForPhones.add(pane, columnCount, rowCount);

                columnCount++;

                if (columnCount == 3) { // Number of columns per row
                    columnCount = 0;
                    rowCount++;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }




        reloadButton.setOnAction(event -> {
            // #TODO add action

        });

    }


    public List<Phone> loadPhones(){
        List<Phone> ls = new ArrayList<>();

        Phone phone = new Phone();
        phone.setName("Samsung s20");
//        phone.setImgPath("mastermedia/frontend/img/phone.png");
        ls.add(phone);

        Phone phone1 = new Phone();
        phone1.setName("Iphone");
//        phone1.setImgPath("mastermedia/frontend/img/phone.png");
        ls.add(phone1);

        return ls;
    }


}
