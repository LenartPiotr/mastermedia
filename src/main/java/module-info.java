module frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
//    requires de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

    opens mastermedia.frontend to javafx.fxml;
    exports mastermedia.frontend;
    exports mastermedia.frontend.controllers;
    opens mastermedia.frontend.controllers to javafx.fxml;
    exports mastermedia.frontend.controllers.extra;
    opens mastermedia.frontend.controllers.extra to javafx.fxml;


}