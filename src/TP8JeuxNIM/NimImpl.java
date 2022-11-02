package TP8JeuxNIM;

import java.rmi.RemoteException;


public class NimImpl implements Nim {

        private boolean someonePlaying;
        private int nbAllumettes;

        private boolean gameRunning;

        public NimImpl() throws RemoteException {
            // random between 4 and 20
            nbAllumettes = (int) (Math.random() * 17) + 4;
            someonePlaying = false;
            gameRunning = true;
        }

        @Override
        public int jouer(int nbAllumettesPrises) throws RemoteException {
            System.out.println("Nombre d'allumettes restantes : " + nbAllumettesPrises);
            nbAllumettes = nbAllumettes - nbAllumettesPrises;
            someonePlaying = false;
            if (nbAllumettes == 0) {
                gameRunning = false;
            }
            return nbAllumettes;
        }

        @Override
        public boolean isSomeonePlaying() {
            return someonePlaying;
        }

        @Override
        public int iAmPlaying() {
            someonePlaying = true;
            return nbAllumettes;
        }

    @Override
    public boolean isGameRunning() {
        return gameRunning;
    }
}