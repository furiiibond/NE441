package TP4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionFichier {

    public static void main(String[] args) {
        List<Integer> buffLenList = new ArrayList<>();
        buffLenList.add(10);
        buffLenList.add(100);
        buffLenList.add(1000);
        buffLenList.add(10000);
        buffLenList.add(100000);
        // for each bufferlen copyfile
        for (Integer buffLen:buffLenList) {
            System.out.println("BufferLen : " + buffLen);
            copyFile(buffLen);
        }

        
    }

    private static void copyFile(int tailleBuf) {
        long start = System.currentTimeMillis();
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\jeanc\\OneDrive\\ESISAR\\ESISAR_S3\\NE441\\TP\\TP4\\file1");
            FileOutputStream fos = new FileOutputStream("C:\\Users\\jeanc\\OneDrive\\ESISAR\\ESISAR_S3\\NE441\\TP\\TP4\\file2");
            byte[] buffer = new byte[tailleBuf];
            int nbLus = fis.read(buffer);
            while (nbLus != -1) {
                fos.write(buffer, 0, nbLus);
                nbLus = fis.read(buffer);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long stop = System.currentTimeMillis();
        System.out.println("Elapsed Time = "+(stop-start)+" ms");
    }

}
