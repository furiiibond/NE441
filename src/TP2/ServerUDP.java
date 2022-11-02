package TP2;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServerUDP {

    private DatagramSocket socket;
    private List<Client> clients;

    public ServerUDP(int port) {
        // Démarrage
        System.out.println("Demarrage du serveur...");
        clients = new ArrayList<>();

        // Le serveur se declare aupres de la couche transport sur le port donné
        socket = null;
        try {
            socket = new DatagramSocket(null);
            socket.bind(new InetSocketAddress(port));
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    }

    // Attente de messages
    public String receive() {
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        try {
            socket.receive(dpR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String message = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Message recu = " + message);

        // register
        if (message.startsWith("register") || message.startsWith("last")) {
            clients.add(new Client(dpR.getAddress(), dpR.getPort()));
        }
        // remove /n at the end
        return message.split("\n")[0];
    }

    // get number of clients
    public int getNumberOfClients() {
        return clients.size();
    }

    // Emission d'un message en retour
    public void send(String message, int numClient) {
        byte[] bufE = message.getBytes();
        InetAddress address = clients.get(numClient).getAddr();
        int port = clients.get(numClient).getPort();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, address, port);
        try {
            socket.send(dpE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Message envoye = " + message);
    }

    public void sendAll(String message) {
        for (Client client : clients) {
            send(message, clients.indexOf(client));
        }
    }

    // close the socket
    public void close() {
        socket.close();
    }

}
