package TP6;

import java.util.ArrayList;
import java.util.List;

/*
N philosophers (N is for example 5) are gathered around a table in a Chinese restaurant. Each philosopher loops on two tasks:

    discuss a random time between 0 and 10 seconds
    eat a random time between 0 and 10 seconds
    */
public class PhilosopheSimple extends Thread  {

    private final int id;

    public PhilosopheSimple(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        List<PhilosopheSimple> philosopheList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            philosopheList.add(new PhilosopheSimple(i));
        }
        philosopheList.forEach(PhilosopheSimple::start);
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
        The philosopher is eating for a random time between 0 and 10 seconds
     */
    public void manger() {
        System.out.println("Le philosophe num " + id + " mange");
        try {
            Thread.sleep((int) (Math.random() * 10000)); // the program will stop for 0 to 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
        The philosopher is talking for a random time between 0 and 10 seconds
    */
    public void discuter() {
        System.out.println("Le philosophe num " + id + " discute");
        try {
            Thread.sleep((int) (Math.random() * 10000)); // the program will stop for 0 to 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
