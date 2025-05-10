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

public class RegisterController {

    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;
    @FXML private ComboBox<String> cmbRole;
    @FXML private ComboBox<String> cmbDepartment;
    @FXML private CheckBox chkTerms;
    @FXML private Button btnRegister;
    @FXML private Hyperlink linkLogin;

    @FXML
    private void initialize() {
        // Initialiser les ComboBox avec des valeurs
        cmbRole.getItems().addAll(
            "Administrateur",
            "Gestionnaire de flotte",
            "Planificateur de vols",
            "Gestionnaire d'équipage",
            "Analyste",
            "Support technique"
        );
        
        cmbDepartment.getItems().addAll(
            "Opérations aériennes",
            "Maintenance",
            "Ressources humaines",
            "Planification",
            "Administration",
            "Support technique",
            "Finance"
        );
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        // Validation des champs
        if (!validateFields()) {
            return;
        }
        
        // Ici, vous implémenteriez la logique d'enregistrement réelle
        // Pour cet exemple, nous affichons simplement un message de succès
        showAlert(Alert.AlertType.INFORMATION, "Inscription réussie", 
                "Compte créé avec succès", 
                "Vous pouvez maintenant vous connecter avec vos identifiants.");
        
        // Rediriger vers l'écran de connexion
        goToLogin(event);
    }

    private boolean validateFields() {
        // Vérifier que tous les champs obligatoires sont remplis
        if (txtFirstName.getText().trim().isEmpty() ||
            txtLastName.getText().trim().isEmpty() ||
            txtEmail.getText().trim().isEmpty() ||
            txtUsername.getText().trim().isEmpty() ||
            txtPassword.getText().isEmpty() ||
            txtConfirmPassword.getText().isEmpty() ||
            cmbRole.getValue() == null ||
            cmbDepartment.getValue() == null) {
            
            showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", 
                    "Champs incomplets", 
                    "Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        
        // Vérifier que l'email est valide
        if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", 
                    "Email invalide", 
                    "Veuillez entrer une adresse email valide.");
            return false;
        }
        
        // Vérifier que le mot de passe est assez long
        if (txtPassword.getText().length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", 
                    "Mot de passe trop court", 
                    "Le mot de passe doit contenir au moins 8 caractères.");
            return false;
        }
        
        // Vérifier que les mots de passe correspondent
        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", 
                    "Les mots de passe ne correspondent pas", 
                    "Veuillez vous assurer que les deux mots de passe sont identiques.");
            return false;
        }
        
        // Vérifier que les termes et conditions sont acceptés
        if (!chkTerms.isSelected()) {
            showAlert(Alert.AlertType.ERROR, "Erreur d'inscription", 
                    "Termes et conditions", 
                    "Vous devez accepter les termes et conditions pour vous inscrire.");
            return false;
        }
        
        return true;
    }

    @FXML
    private void goToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/VIEW/FXML.Login.fxml"));
            Stage stage = (Stage) linkLogin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", 
                    "Impossible de charger l'écran de connexion", 
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
