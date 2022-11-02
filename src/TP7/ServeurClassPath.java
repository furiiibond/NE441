package TP7;

import TP2.ServerUDP;

import java.util.Scanner;

public class ServeurClassPath {

    private static final int NBCLIENTS = 1;
    private ServerUDP server;

    public static void main(String[] args) {
        ServeurClassPath serv = new ServeurClassPath(3000);
        serv.execute();
    }
    public ServeurClassPath(int port) {
        this.server = new ServerUDP(port);
    }

    public void execute() {
        // start timer


        waitForAllClients();
        System.out.println("All clients connected");
        System.out.println("Type the hash please");
        // wait for user input text
        String hash= "9aae4ed4950369d9b007e10b310377b0";
        // send hash to all clients
        long startTime = System.currentTimeMillis();
        server.sendAll(hash);
        // wait for a client to send a message
        String message = server.receive();
        System.out.println("MDP recu = " + message);
        // stop timer
        long endTime = System.currentTimeMillis();
        System.out.println("Temps d'execution = " + (endTime - startTime) + " ms");
    }

    private void waitForAllClients() {
        while (server.getNumberOfClients() < NBCLIENTS) {
            server.receive();
        }
    }


}
