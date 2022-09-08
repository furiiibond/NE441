package TP1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Serveur basique UDP
 */
public class ServeurUDP
{
    public static void main(String[] args) throws Exception
    {
        int port = 3000;
        ServeurUDP serveurUDP = new ServeurUDP();
        serveurUDP.execute(port);
    }

    private void execute(int port) throws IOException
    {
        //
        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 5099
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(port));

        // Attente du premier message
        String message = "";
        while (! message.equals("exit")) {
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            message = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("\nMessage recu = " + message);
            System.out.println("De la part de " + dpR.getAddress() + " sur le port " + dpR.getPort());
        }

        // Emission d'un message en retour
        /*
        byte[] bufE = new String("ok").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length,
                dpR.getAddress(),dpR.getPort());
        socket.send(dpE);
        System.out.println("Message envoye = ok");
        */

        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du serveur.");
    }
}
