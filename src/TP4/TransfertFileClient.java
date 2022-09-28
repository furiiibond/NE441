package TP4;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TransfertFileClient {

    public static void main(String[] args) {
        new TransfertFileClient().receiveFile("test.txt","rcv.txt");
    }

    public void receiveFile(String askForFile, String fileName) {

        Socket s= null;
        try {
            s = new Socket("127.0.0.1",60);

            InputStream inf = s.getInputStream();
            askForFile += ";";
            s.getOutputStream().write(askForFile.getBytes());
            FileOutputStream out = new FileOutputStream(fileName);
            byte buf[] = new byte[1024];
            int n;
            while((n=inf.read(buf))!=-1){
                out.write(buf,0,n);
            }
            inf.close();
            out.close();
            s.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
