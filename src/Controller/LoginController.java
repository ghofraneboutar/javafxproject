/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author ghofrane
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private CheckBox chkRemember;
    @FXML private Button btnLogin;
    @FXML private Hyperlink linkRegister;

    @FXML
    private void initialize() {
        // Initialisation du contrôleur
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        
        // Validation basique
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur de connexion", 
                    "Veuillez remplir tous les champs", 
                    "Le nom d'utilisateur et le mot de passe sont requis.");
            return;
        }
        
        // Ici, vous implémenteriez la logique d'authentification réelle
        // Pour cet exemple, nous utilisons des identifiants codés en dur
        if (username.equals("admin") && password.equals("admin123")) {
            try {
                // Charger le dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/Dashboard.fxml"));
                Parent root = loader.load();
                
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("AirTravel Command Center");
                stage.setMaximized(true);
                stage.show();
                
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Erreur", 
                        "Impossible de charger le dashboard", 
                        e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur de connexion", 
                    "Identifiants invalides", 
                    "Le nom d'utilisateur ou le mot de passe est incorrect.");
        }
    }

    @FXML
    private void goToRegister(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/VIEW/register.fxml"));
            Stage stage = (Stage) linkRegister.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", 
                    "Impossible de charger l'écran d'inscription", 
                    e.getMessage());
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}