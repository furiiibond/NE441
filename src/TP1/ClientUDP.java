package TP1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Client basique UDP
 */
public class ClientUDP
{
    public static void main(String[] args) throws Exception
    {
        String messToSend = "Test UDP";
        ClientUDP clientUDP = new ClientUDP();
        clientUDP.execute(messToSend);
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse
     */
    private void execute(String message) throws IOException
    {
        // Demarrage
        System.out.println("Demarrage du client ...");

        // Creation de la socket
        DatagramSocket socket = new DatagramSocket();

        // Creation et envoi du message
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 3000);
        byte[] bufE = message.getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);
        System.out.println("Message envoyé");

        // Attente de la reponse
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR);
        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Réponse recue = " + reponse);

        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client.");
    }
}

