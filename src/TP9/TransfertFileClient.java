package TP9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TransfertFileClient {

    public static void main(String[] args) {
        new TransfertFileClient().receiveFile("C:\\Users\\jeanc\\OneDrive\\ESISAR\\ESISAR_S3\\NE441\\TP\\TP4\\file1","C:\\Users\\jeanc\\OneDrive\\ESISAR\\ESISAR_S3\\NE441\\TP\\TP4\\file3");
    }

    public void receiveFile(String askForFile, String fileName) {

        Socket s = null;
        try {
            s = new Socket("127.0.0.1",60);

            InputStream inf = s.getInputStream();
            askForFile += ";";
            s.getOutputStream().write(askForFile.getBytes());
            FileOutputStream out = new FileOutputStream(fileName);
            String fileLenString = receiveMessage(s.getInputStream(), ";");
            long fileLen = Long.parseLong(fileLenString);
            byte buf[] = new byte[100000];
            int n;
            while((n=inf.read(buf))!=-1){
                out.write(buf,0,n);
                // display the percentage of the file received
                System.out.println("Percentage : " + (out.getChannel().size() * 100 / fileLen) + "%");
            }
            inf.close();
            out.close();
            s.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public String receiveMessage(InputStream inputStream, String finalChar) throws IOException {

        byte buf[] = new byte[1024];
        int n;
        String str = "";
        while((n=inputStream.read(buf))!=-1){
            str += new String(buf,0,n);
            if (str.contains(";")) {
                break;
            }
        }
        return str.split(";")[0];
    }

}
