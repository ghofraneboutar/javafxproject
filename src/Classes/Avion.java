/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;



public class Avion {

    public enum Tetat {

        disponible,
        maintenance,
        en_vol
    }
    private final IntegerProperty id;
    private final StringProperty matricule;
    private final StringProperty modele;
    private final IntegerProperty capacite;
    private final ObjectProperty<Tetat> etat;
    private List<Vol> vols = new ArrayList<>();

    public Avion(int id, String matricule, String modele, int capacite, Tetat etat) {
        this.id = new SimpleIntegerProperty(id);
        this.matricule = new SimpleStringProperty(matricule);
        this.modele = new SimpleStringProperty(modele);
        this.capacite = new SimpleIntegerProperty(capacite);
        this.etat = new SimpleObjectProperty<>(etat);
    }

    public String getMatricule() {
        return matricule.get();
    }

    public int getId() {
        return this.id.get();
    }

    public List<Vol> getVols() {
        return vols;
    }

    public String getModele() {
        return modele.get();
    }

    public int getCapacite() {
        return capacite.get();
    }

    public Tetat getEtat() {
        return etat.get();
    }

    public StringProperty matriculeProperty() {
        return matricule;
    }

    public StringProperty modeleProperty() {
        return modele;
    }

    public IntegerProperty capaciteProperty() {
        return capacite;
    }

    public ObjectProperty<Tetat> etatProperty() {
        return etat;
    }

    public void setId(int value) {
        id.set(value);
    }

    public void setMatricule(String value) {
        matricule.set(value);
    }

    public void setModele(String value) {
        modele.set(value);
    }

    public void setCapacite(int value) {
        capacite.set(value);
    }

    public void setEtat(Tetat value) {
        etat.set(value);
    }
}
