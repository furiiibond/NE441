package TP1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Random;

public class ServeurPingPong
{
    public static void main(String[] args) throws Exception
    {
        int port = 29000;
        ServeurPingPong serveurPingPong = new ServeurPingPong();
        serveurPingPong.execute(port);
    }

    private void execute(int port) throws IOException
    {
        // Démarrage
        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport sur le port donné
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(port));

        // Attente du premier message
        String message;
        
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        message = receiveMessage(dpR ,socket, bufR);

        if (message.equals("JOUER"))
        {
            // Emission d'un message en retour
            byte[] bufE;
            Random rand = new Random();
            int randomInt = rand.nextInt(2); // between 0 and 1
            String RepAttendu = "";

            if (randomInt == 0)
            {
                RepAttendu = "PONG";
                bufE = new String("PING").getBytes();
            } else {
                RepAttendu = "PING";
                bufE = new String("PONG").getBytes();
            }
            DatagramPacket dpE = new DatagramPacket(bufE, bufE.length,
                    dpR.getAddress(),dpR.getPort());
            socket.send(dpE);
            System.out.println("Message envoyé = " + new String(bufE));
            bufR = new byte[2048]; // vide le tampon
            dpR = new DatagramPacket(bufR, bufR.length);
            String reponse = receiveMessage(dpR, socket, bufR);

            if (reponse.equals(RepAttendu)){
                bufE = new String("GAGNE ").getBytes();
            } else {
                bufE = new String("PERDU ").getBytes();
             }
            dpE = new DatagramPacket(bufE, bufE.length,
                    dpR.getAddress(),dpR.getPort());
            socket.send(dpE);
        }

        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du serveur.");
    }

    private static String receiveMessage (DatagramPacket dpR, DatagramSocket socket, byte[] bufR) throws IOException
    {
        String message;
        socket.receive(dpR);
        message = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("\nMessage recu = " + message);
        System.out.println("De la part de " + dpR.getAddress() + " sur le port " + dpR.getPort());
        return message;
    }
}
