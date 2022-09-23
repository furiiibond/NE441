package TP3;

import java.io.IOException;

public class ReceptionSimpleTCP {

    public static void main(String[] args) {
        ServeurTCP serveur = new ServeurTCP(1234);
        try {
            String message= "";
            serveur.waitForClient();
            while(!message.equals("fin")) {
                message = serveur.receive();
                System.out.println("Message recu : " + message);
            }
            serveur.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
