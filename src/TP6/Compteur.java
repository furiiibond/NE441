package TP6;

public class Compteur {

    private int compteur;

    public Compteur() {
        compteur = 0;
    }

    public int getCompteur() {
        synchronized (this) {
            compteur++;
            return compteur;
        }
    }
}
