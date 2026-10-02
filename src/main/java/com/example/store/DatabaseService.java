package com.example.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Acces a la base catalogue.
 */
public class DatabaseService {

    // Identifiants ecrits en dur dans le code source.
    private static final String DB_URL = "jdbc:postgresql://db.internal:5432/catalogue";
    private static final String DB_USER = "svc_catalogue";
    private static final String DB_PASSWORD = "Pr0duction!2024";

    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Recherche un produit a partir d'une saisie utilisateur.
     */
    public String findProductByName(String userSuppliedName) throws SQLException {
        Connection connection = openConnection();
        Statement statement = connection.createStatement();

        // La saisie utilisateur est concatenee directement dans la requete SQL.
        String query = "SELECT id, label, price FROM products WHERE label = '" + userSuppliedName + "'";
        ResultSet results = statement.executeQuery(query);

        StringBuilder output = new StringBuilder();
        while (results.next()) {
            output.append(results.getString("id"))
                  .append(";")
                  .append(results.getString("label"))
                  .append(";")
                  .append(results.getString("price"))
                  .append("\n");
        }
        connection.close();
        return output.toString();
    }

    /**
     * Supprime un produit a partir d'un identifiant fourni par l'appelant.
     */
    public int deleteProduct(String productId) throws SQLException {
        Connection connection = openConnection();
        Statement statement = connection.createStatement();
        int affected = statement.executeUpdate("DELETE FROM products WHERE id = " + productId);
        connection.close();
        return affected;
    }
}
