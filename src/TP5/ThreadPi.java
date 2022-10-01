package TP5;

import java.security.spec.RSAOtherPrimeInfo;

public class ThreadPi {


    public static void main(String[] args) {
        //start timer
        long start = System.currentTimeMillis();
        double nbIt = 10000000000.0;
        PiThread e1 = new PiThread(0, nbIt/4);
        PiThread e2 = new PiThread(nbIt/4, nbIt/2);
        PiThread e3 = new PiThread(nbIt/2, 3*nbIt/4);
        PiThread e4 = new PiThread(3*nbIt/4, nbIt);
        e1.start();
        e2.start();
        e3.start();
        e4.start();
        try {
            e1.join();
            e2.join();
            e3.join();
            e4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(e1.getResult()+e2.getResult()+e3.getResult()+e4.getResult());
        long stop = System.currentTimeMillis();
        System.out.println("Elapsed Time = "+(stop-start)+" ms");
    }

}
