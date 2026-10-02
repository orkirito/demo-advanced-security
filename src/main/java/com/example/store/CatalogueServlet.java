package com.example.store;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Point d'entree HTTP du catalogue produits.
 */
public class CatalogueServlet extends HttpServlet {

    private final DatabaseService databaseService = new DatabaseService();
    private final InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String term = request.getParameter("q");
        PrintWriter writer = response.getWriter();

        if ("search".equals(action)) {
            try {
                // La saisie utilisateur descend jusqu'a la requete SQL.
                String products = databaseService.findProductByName(term);
                // Et elle est aussi renvoyee telle quelle dans la reponse HTML.
                writer.println("<h1>Resultats pour " + term + "</h1>");
                writer.println("<pre>" + products + "</pre>");
            } catch (Exception e) {
                writer.println("Erreur : " + e.getMessage());
            }
        } else if ("invoice".equals(action)) {
            String name = request.getParameter("name");
            writer.println("<pre>" + invoiceService.readInvoice(name) + "</pre>");
        } else {
            writer.println("<p>Action inconnue : " + action + "</p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id");
        try {
            databaseService.deleteProduct(productId);
            response.getWriter().println("Produit " + productId + " supprime");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
