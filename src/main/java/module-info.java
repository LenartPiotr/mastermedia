module frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens mastermedia.frontend to javafx.fxml;
    exports mastermedia.frontend;

//    opens main.mastermedia.main.frontend to javafx.graphics;
//    exports main.mastermedia.main.frontend;

}