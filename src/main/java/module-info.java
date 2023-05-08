module mastermedia {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.desktop;
    requires org.yaml.snakeyaml;

    opens mastermedia.frontend to javafx.fxml;
    exports mastermedia.frontend;
    exports mastermedia.frontend.controllers;
    opens mastermedia.frontend.controllers to javafx.fxml;

    opens mastermedia.backend.settings to org.yaml.snakeyaml;
    exports mastermedia.backend;
    exports mastermedia.backend.settings;
    exports mastermedia.frontend.controllers.extra;
    opens mastermedia.frontend.controllers.extra to javafx.fxml;


}

