package TP9;

import java.io.*;
import java.net.Socket;

public class SendThread extends Thread {

    private final Socket socketConnexion;

    public SendThread(Socket socketConnexion, int numClient)
    {
        this.socketConnexion = socketConnexion;
        System.out.println("Client " + numClient + " connected");
    }

    @Override
    public void run() {
        try {
            InputStream inf = socketConnexion.getInputStream();
            OutputStream out = socketConnexion.getOutputStream();

            // read the file name from the client
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
