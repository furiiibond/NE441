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
        new TransfertFileServer().sendFile();
    }

    public TransfertFileServer() {
    }

    public void sendFile() {

        try {
            sv = new ServerSocket(60);
            Socket socketConnexion = sv.accept();
            // read the file name from the client
            InputStream inf = socketConnexion.getInputStream();
            OutputStream out = socketConnexion.getOutputStream();
            // while buffer do not contain the end string ";"
            String fileName = receiveFileName(inf);
            // calculate the file size
            long fileSize = getFileSize(fileName);
            // send the file size to the client
            sendMessage(out, String.valueOf(fileSize));
            // send the file
            FileInputStream in = new FileInputStream(fileName);

            byte buf[] = new byte[100000];
            int n;
            while((n=in.read(buf))!=-1){
                out.write(buf,0,n);
            }
            out.close();
            socketConnexion.close();
        } catch (Exception ex) {}
    }

    public String receiveFileName(InputStream inf) throws IOException {
        byte buf[] = new byte[1024];
        int n;
        String str = "";
        while((n=inf.read(buf))!=-1){
            str += new String(buf,0,n);
            if (str.contains(";")) {
                break;
            }
        }
        return str.split(";")[0];
    }

    public long getFileSize(String fileName) {
        File file = new File(fileName);
        return file.length();
    }

    public void sendMessage(OutputStream os, String message) {
        // Emission d'un message en retour
        message += ";";
        byte[] bufE = message.getBytes();
        try {
            os.write(bufE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Message envoye = " + message);
    }

}

