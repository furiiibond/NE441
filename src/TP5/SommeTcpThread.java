package TP5;

import TP3.ClientTCP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SommeTcpThread extends Thread {

    private ClientTCP clientTCP;

    private static final int MIN = 21000;
    private static final int MAX = 23000;

    private int montant;

    public SommeTcpThread(int port) {
        this.clientTCP = new ClientTCP("127.0.0.1", port);
    }

    public void run(){
        try {
            clientTCP.send("COMBIEN");
            String montantString = clientTCP.receive("E").split("E")[0];
            montant = Integer.parseInt(montantString);
            //System.out.println(montant);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<SommeTcpThread> sommeTcpThreads = new ArrayList<>();
        int nbThread = 0;
        for (int i = MIN; i <= MAX; i++) {
            sommeTcpThreads.add(new SommeTcpThread(i));
            sommeTcpThreads.get(nbThread).start();
            nbThread++;
        }
        int joined = 0;
        for (int i = 0; i < nbThread; i++) {
            try {
                sommeTcpThreads.get(i).join();
                joined++;
                System.out.println(joined);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int max = sommeTcpThreads.get(0).getMontant();
        for (int i = 1; i < nbThread; i++) {
            int m = sommeTcpThreads.get(i).getMontant();
            if (m>max)
                max = m;
        }
        System.out.println("MAX = " + max);
    }

    public int getMontant() {
        return montant;
    }

}
