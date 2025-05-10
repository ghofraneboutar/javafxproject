package Controller;

import Classes.Avion;
import Classes.Avion.Tetat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AvionController {

    @FXML private TextField txtSearch;
    @FXML private Button btnAddNew, btnSearch, btnFilter;
    @FXML private TableView<Avion> tableAvions;
    @FXML private TableColumn<Avion, Integer> colId;
    @FXML private TableColumn<Avion, String> colMatricule, colModele;
    @FXML private TableColumn<Avion, Integer> colCapacite;
    @FXML private TableColumn<Avion, Tetat> colEtat;
    @FXML private VBox detailsPane;
    @FXML private Label lblNoSelection;
    
    private ObservableList<Avion> avionsList = FXCollections.observableArrayList(
        new Avion(1, "A320-001", "Airbus A320", 180, Tetat.disponible),
        new Avion(2, "B737-002", "Boeing 737-800", 189, Tetat.disponible),
        new Avion(3, "A330-003", "Airbus A330-300", 295, Tetat.maintenance),
        new Avion(4, "B777-004", "Boeing 777-300ER", 396, Tetat.disponible),
        new Avion(5, "A350-005", "Airbus A350-900", 325, Tetat.disponible),
        new Avion(6, "B787-006", "Boeing 787-9", 290, Tetat.disponible),
        new Avion(7, "A321-007", "Airbus A321neo", 220, Tetat.disponible),
        new Avion(8, "E190-008", "Embraer E190", 114, Tetat.maintenance)
    );
    
    @FXML
    private void initialize() {
        // Configure table columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colModele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        colCapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        // Style the etat column
        colEtat.setCellFactory(column -> new TableCell<Avion, Tetat>() {
            @Override
            protected void updateItem(Tetat item, boolean empty) {
                super.updateItem(item, empty);
                
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.toString());
                    if (item == Tetat.disponible) {
                        setStyle("-fx-background-color: #e6f7e9; -fx-text-fill: #2a9d8f; -fx-padding: 5; -fx-background-radius: 3;");
                    } else if (item == Tetat.maintenance) {
                        setStyle("-fx-background-color: #fff4e5; -fx-text-fill: #f9c74f; -fx-padding: 5; -fx-background-radius: 3;");
                    } else if (item == Tetat.en_vol) {
                        setStyle("-fx-background-color: #e3f2fd; -fx-text-fill: #1976d2; -fx-padding: 5; -fx-background-radius: 3;");
                    }
                }
            }
        });
        
        // Load data
        tableAvions.setItems(avionsList);
        
        // Configure selection listener
        tableAvions.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showDetails(newSelection);
            } else {
                hideDetails();
            }
        });
        
        // Configure buttons
        btnAddNew.setOnAction(e -> showAddDialog());
        btnSearch.setOnAction(e -> searchAvions());
        btnFilter.setOnAction(e -> showFilterDialog());
        
        // Initial state
        hideDetails();
    }
    
    private void showDetails(Avion avion) {
        detailsPane.setVisible(true);
        lblNoSelection.setVisible(false);
        
        // In a real application, you would populate the details pane with the avion's details
    }
    
    private void hideDetails() {
        detailsPane.setVisible(false);
        lblNoSelection.setVisible(true);
    }
    
    private void showAddDialog() {
        Dialog<Avion> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un nouvel avion");
        dialog.setHeaderText("Entrez les détails du nouvel avion");
        
        // Set the button types
        ButtonType addButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        
        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));
        
        TextField id = new TextField();
        id.setPromptText("ID");
        TextField matricule = new TextField();
        matricule.setPromptText("Matricule");
        TextField modele = new TextField();
        modele.setPromptText("Modèle");
        TextField capacite = new TextField();
        capacite.setPromptText("Capacité");
        ComboBox<Tetat> etat = new ComboBox<>();
        etat.getItems().addAll(Tetat.values());
        etat.setValue(Tetat.disponible);
        
        grid.add(new Label("ID:"), 0, 0);
        grid.add(id, 1, 0);
        grid.add(new Label("Matricule:"), 0, 1);
        grid.add(matricule, 1, 1);
        grid.add(new Label("Modèle:"), 0, 2);
        grid.add(modele, 1, 2);
        grid.add(new Label("Capacité:"), 0, 3);
        grid.add(capacite, 1, 3);
        grid.add(new Label("État:"), 0, 4);
        grid.add(etat, 1, 4);
        
        dialog.getDialogPane().setContent(grid);
        
        // Request focus on the matricule field by default
        matricule.requestFocus();
        
        // Convert the result to an Avion object when the add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    int idValue = Integer.parseInt(id.getText());
                    int capaciteValue = Integer.parseInt(capacite.getText());
                    return new Avion(idValue, matricule.getText(), modele.getText(), capaciteValue, etat.getValue());
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setHeaderText("Valeurs numériques invalides");
                    alert.setContentText("Veuillez entrer des nombres valides pour l'ID et la capacité.");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });
        
        dialog.showAndWait().ifPresent(avion -> {
            if (avion != null) {
                avionsList.add(avion);
                tableAvions.getSelectionModel().select(avion);
            }
        });
    }
    
    private void searchAvions() {
        String searchText = txtSearch.getText().toLowerCase();
        if (searchText.isEmpty()) {
            tableAvions.setItems(avionsList);
            return;
        }
        
        ObservableList<Avion> filteredList = FXCollections.observableArrayList();
        for (Avion avion : avionsList) {
            if (avion.getMatricule().toLowerCase().contains(searchText) ||
                avion.getModele().toLowerCase().contains(searchText) ||
                avion.getEtat().toString().toLowerCase().contains(searchText)) {
                filteredList.add(avion);
            }
        }
        
        tableAvions.setItems(filteredList);
    }
    
    private void showFilterDialog() {
        // In a real application, you would show a dialog with filter options
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Filtrer les avions");
        alert.setHeaderText("Fonctionnalité à venir");
        alert.setContentText("La fonctionnalité de filtrage sera disponible dans une prochaine version.");
        alert.showAndWait();
    }
}