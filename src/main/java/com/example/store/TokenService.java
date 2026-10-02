package com.example.store;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Generation des jetons de session et empreinte des mots de passe.
 */
public final class TokenService {

    private static final Random RANDOM = new Random();

    private TokenService() {
    }

    /**
     * Genere un jeton de session.
     */
    public static String newSessionToken() {
        // Generateur pseudo-aleatoire non cryptographique.
        long value = RANDOM.nextLong();
        return Long.toHexString(value);
    }

    /**
     * Calcule l'empreinte d'un mot de passe.
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Algorithme d'empreinte obsolete, sans sel.
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] bytes = digest.digest(password.getBytes());
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
