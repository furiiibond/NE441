package TP6;

import tdm.tdm07.exo3.checker.CodeChecker;

import java.util.ArrayList;
import java.util.List;

/*
    N philosophers (N is for example 5) are gathered around a table in a Chinese restaurant. Each philosopher loops on two tasks:

    discuss a random time between 0 and 1 milisecond
    eat a random time between 0 and 1 milisecond
*/
public class Philosophe extends Thread  {

    private final int id;
    private Arbitre arbitre;

    public Philosophe(Arbitre arbitre,int id) {
        this.id = id;
        this.arbitre = arbitre;
    }

    public static void main(String[] args) {
        List<Philosophe> philosopheList = new ArrayList<>();
        final int nbPhilosophe = 50;
        Arbitre arbitre = new Arbitre(nbPhilosophe);

        for (int i = 0; i < nbPhilosophe; i++) {
            philosopheList.add(new Philosophe(arbitre, i));
        }
        philosopheList.forEach(Philosophe::start);
    }

    /*
        Thread function for running the behaviour
     */
    public void run() {
        while (true) {
            this.discuter();
            this.manger();
        }
    }

    /*
        The philosopher is eating for a random time between 0 and 1 milisecond
     */
    public void manger() {
        if (arbitre.autorisation(id)) {
            CodeChecker.startEating(id);
            //System.out.println("Le philosophe num " + id + " mange");
            try {
                Thread.sleep(1); // the program will stop for 0 to 1 milisecond
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CodeChecker.stopEating(id);
            arbitre.liberation(id);
        } else {
            //System.out.println("Le philosophe num " + id + " attend 1 milisec");
            // attente de 1 miliseconde
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.manger();
        }
    }

    /*
        The philosopher is talking for a random time between 0 and 1 milisecond
    */
    public void discuter() {
        //System.out.println("Le philosophe num " + id + " discute");
        try {
            Thread.sleep(1); // the program will stop for 0 to 1 milisecond
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
