package TP2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ClientUDP {
    private DatagramSocket socket;
    private final InetSocketAddress adrDest;

    public ClientUDP(String addr, int port) {
        // Démarrage
        System.out.println("Demarrage du client...");

        // Le serveur se declare aupres de la couche transport sur le port donné
        socket = null;
        try {
            socket = new DatagramSocket(null);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        adrDest = new InetSocketAddress(addr, port);
    }

    public void sendMessage(String message) {
        // Creation et envoi du message
        byte[] bufE = message.getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        try {
            socket.send(dpE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Message envoyé");
    }

    public String receiveMessage() {
        // Attente de la reponse
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        try {
            socket.receive(dpR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Réponse recue = " + reponse);
        return reponse;
    }

    //close
    public void close() {
        socket.close();
    }
}
