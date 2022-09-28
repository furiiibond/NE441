package TP4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Write a client program and a server program that allow the exchange of a single file (always the same) by par socket TCP/IP.
 * The client program connects to the server, the server returns to the client the content of the file file_serveur.txt (you can hardcode this file name in your server program).
 */
public class TransfertFileServer {
    private ServerSocket sv;

    public static void main(String[] args) {
        new TransfertFileServer().sendFile("test.txt");
    }

    public TransfertFileServer() {
    }

    public void sendFile(String fileName) {

        try {
            sv = new ServerSocket(60);
            Socket socketConnexion = sv.accept();
            FileInputStream in = new FileInputStream(fileName);
            OutputStream out = socketConnexion.getOutputStream();
            byte buf[] = new byte[1024];
            int n;
            while((n=in.read(buf))!=-1){
                out.write(buf,0,n);
            }
            out.close();
            socketConnexion.close();
        } catch (Exception ex) {}
    }

}

