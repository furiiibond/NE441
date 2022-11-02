package TP9;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Write a client program and a server program that allow the exchange of a single file (always the same) by par socket TCP/IP.
 * The client program connects to the server, the server returns to the client the content of the file file_serveur.txt (you can hardcode this file name in your server program).
 */
public class TransfertFileServer {
    private ServerSocket sv;
    private int numClient = 0;

    public static void main(String[] args)
    {
        new TransfertFileServer().sendFile();
    }

    public TransfertFileServer()
    {
        try {
            numClient = 0;
            sv = new ServerSocket(60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendFile() {
        try {
            while (true) {
                Socket socketConnexion = sv.accept();
                // we give to the new thread the socket stream of the socket
                new SendThread(socketConnexion, numClient).start();  // we start the thread
                numClient++;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }



}

