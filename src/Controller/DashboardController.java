package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;

public class DashboardController {

    @FXML private BorderPane mainBorderPane;
    @FXML private Button btnDashboard, btnAvions, btnVols, btnEquipage, btnPilotes;
    @FXML private Label lblDate;
    @FXML private HBox statsContainer;
    
    private Button currentActiveButton;

    @FXML
    private void initialize() {
        // Afficher la date
        lblDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));

        // Configurer les actions des boutons
        btnAvions.setOnAction(e -> loadView("/VIEW/Avion.fxml"));
    }

    // Charger une vue externe
    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node view = loader.load();
            mainBorderPane.setCenter(view); // Remplace le centre du BorderPane
        } catch (IOException e) {
            System.err.println("Erreur de chargement: " + fxmlPath);
            e.printStackTrace();
        }
    }
}