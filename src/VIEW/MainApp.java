package VIEW;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger l'écran de connexion au démarrage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/VIEW/FXML.Login.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/VIEW/style.css").toExternalForm());
        
        primaryStage.setTitle("AirTravel Command Center - Connexion");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}