package TP6;

import java.util.List;

public class Arbitre {

    private int[] bagettes;

    public Arbitre(int nbBagettes) {
        bagettes = new int[nbBagettes];
        for (int i = 0; i < nbBagettes; i++) {
            bagettes[i] = 0;
        }
    }

    /*
        Donne ou non l'autorisation de parole au philosophe :
            False : l'une ou les deux baguettes du philosophe sont utilisées
            True : les deux baguettes sont libres
    */
    public boolean autorisation(int numPhilo) {
        synchronized (this) {
            if (numPhilo == 0) { // cas spécial car le philosophe num 0 est entre la baguette 0 et la dernière
                if (bagettes[0] == 0 && bagettes[bagettes.length - 1] == 0) {
                    bagettes[0] = 1;
                    bagettes[bagettes.length - 1] = 1;
                    return true;
                }
            } else {
                if (bagettes[numPhilo] == 0 && bagettes[numPhilo - 1] == 0) {
                    bagettes[numPhilo] = 1;
                    bagettes[numPhilo - 1] = 1;
                    return true;
                }
            }
            return false;
        }
    }

    /*
        Libère les deux baguettes de parole du philosophe désigné
     */
    public void liberation(int numPhilo) {
        if (numPhilo == 0) {
            bagettes[0] = 0;
            bagettes[bagettes.length - 1] = 0;
        } else {
            bagettes[numPhilo] = 0;
            bagettes[numPhilo - 1] = 0;
        }
    }
}
