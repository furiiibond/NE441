package TP3;

import java.io.IOException;

/**
 * Exercice 2
 *
 */
public class MassageSimpleTCP {

    public static void main(String[] args) {
        ClientTCP client = new ClientTCP("127.0.0.1", 1234);
        try {
            client.send("Bonjour");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        client.close();
    }
}
