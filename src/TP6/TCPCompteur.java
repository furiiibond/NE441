package TP6;

import TP3.ServeurTCP;

import java.io.IOException;

/*
You will have to develop a TCP server. This server listens in TCP on all ports between 21 000 and 23 000 (included).
 */
public class TCPCompteur extends Thread {

    private ServeurTCP server;

    private Compteur compteur;
    private final int port;

    public static void main(String[] args) {
        Compteur compteur = new Compteur();
        for (int i = 21000; i <= 23000; i++) {
            new TCPCompteur(i, compteur).start();
        }
    }

    public TCPCompteur(int port, Compteur compteur) {
        this.port = port;
        this.server = new ServeurTCP(port);
        this.compteur = compteur;
    }

    public void run() {
        while (true) {
            try {
                server.waitForClient();
                String message = server.receive("?");
                System.out.println(message);
                if (!message.equals("NUMERO?")){
                    server.send("VOUS AVEZ FAIT UNE ERREUR.");
                } else {
                    server.send("NUMERO=" + compteur.getCompteur());
                }
                server.closeSocket();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //server.close();
    }
}
