/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classes.Avion;
import Classes.Avion.Tetat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ghofrane
 */
public class DAOAvion {
  
    

    public static boolean ajouter(Avion avion) {
        Connection cn = LaConnexion.seConnecter();
         String requete = "INSERT INTO avion (modele,capacite,etat) VALUES (?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS); // Récupérer l'ID généré
            ps.setString(1, avion.getModele());
            ps.setInt(2, avion.getCapacite());
            ps.setString(3, avion.getEtat().name());
           int n = ps.executeUpdate();
            if (n >= 1) {
                 ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                avion.setId(rs.getInt(1));
                System.out.println("ajout réussi"); // Mettre à jour l'objet Avion avec le vrai ID
            }
              return true; 
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     public static ArrayList<Avion> chercher(String modele) {
        ArrayList<Avion> avions = new ArrayList<>();
        Connection cn = LaConnexion.seConnecter();
        String requete = "SELECT * FROM avion WHERE modele LIKE ?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, "%" + modele + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String mod = rs.getString("modele");
                int capacite = rs.getInt("capacite");
                Tetat etat = Tetat.valueOf(rs.getString("etat"));

                Avion a = new Avion(id,mod, capacite, etat);
                avions.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }

        return avions;
    }
    public static boolean archiver(Avion a) {
        Connection cn = LaConnexion.seConnecter();
        String requete = "delete from avion  where  avion.id=?;";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1, a.getId());
            int n = pst.executeUpdate();
            if (n >= 1) {
                System.out.println("suppression réussie");
            }
            return true;

        } catch (SQLException ex) {
            System.out.println("problème de requête de suppression" + ex.getMessage());
        }
        return false;
    }
     public static boolean changerEtat(Avion a,Tetat etat) {
        Connection cn = LaConnexion.seConnecter();
        String requete = "update `avion` set `etat`=? where `avion`.`id`=?;";
        try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, etat.name());
            pst.setInt(2,a.getId() );
            int n = pst.executeUpdate();
            if (n >= 1) {
                System.out.println("Modif réussi");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println("probleme de requete Modif" + ex.getMessage());
        }
        return false;

    }
    
}

