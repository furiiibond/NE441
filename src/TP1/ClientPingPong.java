package TP1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ClientPingPong {

    public static void main(String[] args) throws Exception
    {
        String messToSend = "JOUER";
        ClientPingPong clientPingPong = new ClientPingPong();
        DatagramSocket socket = clientPingPong.initSocket();
        String reponse = clientPingPong.execute(socket, messToSend);
        String aGagne;
        if (reponse.equals("PING"))
        {
            aGagne = clientPingPong.execute(socket, "PONG");
        } else {
            aGagne =clientPingPong.execute(socket, "PING");
        }
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client.");
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse
     */
    private DatagramSocket initSocket()
    {
        try {
            return new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private String execute(DatagramSocket socket, String message) throws IOException
    {
        // Demarrage
        System.out.println("Demarrage du client ...");

        // Creation et envoi du message
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 29000);
        byte[] bufE = message.getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);
        System.out.println("Message envoyé");

        // Attente de la reponse
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR); // bloquant
        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Réponse recue = " + reponse);

        return reponse;
    }
}

