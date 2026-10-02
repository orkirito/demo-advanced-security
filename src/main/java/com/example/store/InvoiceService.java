package com.example.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Service de telechargement des factures clients.
 */
public class InvoiceService {

    private static final String INVOICE_ROOT = "/var/data/invoices/";

    /**
     * Retourne le contenu d'une facture a partir du nom transmis par le client HTTP.
     */
    public String readInvoice(String invoiceName) throws IOException {
        // Le nom est concatene au repertoire racine sans verification.
        File invoice = new File(INVOICE_ROOT + invoiceName);
        try (InputStream in = new FileInputStream(invoice)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    /**
     * Archive une facture en appelant l'utilitaire systeme de compression.
     */
    public void archiveInvoice(String invoiceName) throws IOException {
        // La saisie est injectee dans une ligne de commande passee au shell.
        String command = "sh -c \"gzip " + INVOICE_ROOT + invoiceName + "\"";
        Runtime.getRuntime().exec(command);
    }
}
