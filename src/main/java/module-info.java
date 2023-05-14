module mastermedia {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.desktop;
    requires org.yaml.snakeyaml;
    requires transitive ffmpeg;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
//    requires lombok;

    opens mastermedia.frontend to javafx.fxml;
    exports mastermedia.frontend;
    exports mastermedia.frontend.controllers;
    opens mastermedia.frontend.controllers to javafx.fxml;

    opens mastermedia.backend.settings to org.yaml.snakeyaml;
    exports mastermedia.backend;
    exports mastermedia.backend.settings;
    exports mastermedia.backend.squisher;
    exports mastermedia.frontend.controllers.extra;
    opens mastermedia.frontend.controllers.extra to javafx.fxml;

}

