package mastermedia.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import mastermedia.frontend.controllers.extra.FileInAlbumController;

public class BrowseFilesAlbumController  {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView lineBetweenAlbums;

    // metoda inicjalizująca kontroller po załadowaniu pliku FXML
    public void initialize() {
        // dodanie elementów do panelu z albumami
        for(int i = 0; i < 10; i++) { // dodajemy 10 przykładowych elementów
            // utworzenie i dodanie elementu
//            Pane fileInAlbumPane = new FileInAlbumController().getFileInAlbumPane();
            Pane fileInAlbumPane = new Pane();

            mainPane.getChildren().add(fileInAlbumPane);
//            fileInAlbumPane.setStyle("-fx-background-color: #FF0000;");
//            fileInAlbumPane.setLayoutX(50); // ustawienie położenia względem osi X
//            fileInAlbumPane.setLayoutY(50); // ustawienie położenia względem osi Y
        }

        // ustawienie marginesów dla linii między albumami
        lineBetweenAlbums.setFitWidth(mainPane.getPrefWidth() - 14); // 14 to marginesy dla panelu z elementami
        lineBetweenAlbums.setLayoutX(7); // 7 to margines od lewej strony panelu z elementami
    }
}
