package TP2;

import java.awt.*;

public class ChenillardUDP {
    private static final int DEFAULTPORT = 4000;
    private Television screenDisplay;
    private ClientUDP clientUDP;

    private int serveurPort;

    // server port given in parameter
    public ChenillardUDP(int port) {
        serveurPort = port;
        System.out.println("Serveur port : " + serveurPort);
        // Démarrage
        System.out.println("Demarrage du chenillard...");

        // Le serveur se declare aupres de la couche transport sur le port donné
        screenDisplay = new Television(serveurPort, Color.GREEN, "PLAYER");
        int clientPort = (serveurPort == DEFAULTPORT+3) ? DEFAULTPORT : serveurPort + 1; // calcul du prochain port (voisin)
        clientUDP = new ClientUDP("127.0.0.1", clientPort);

    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("main: Not enough parameters. Give an id greater than 0.");
            return;
        }
        int progID = Integer.parseInt(args[0]);
        if (progID < 0) {
            System.out.println("main: You must give an id greater than 0.");
            return;
        }
        ChenillardUDP chenillard = new ChenillardUDP(DEFAULTPORT + progID);
        chenillard.execute();
    }

    private void execute()  {
        // Initialisation du jeu par le client numéro 0
        //envoi d'un message au suivant
        if (serveurPort == DEFAULTPORT) {
            clientUDP.sendMessage("red");
        }
        int i = 0;
        while (i < 15) {
            String message = screenDisplay.receive();
            if (message.equals("red")) {
                screenDisplay.changeColor("red");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            screenDisplay.changeColor("green");
            clientUDP.sendMessage("red");

            i++;
        }
        screenDisplay.close();
        clientUDP.close();
    }
}
