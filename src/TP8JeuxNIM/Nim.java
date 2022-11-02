package TP8JeuxNIM;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Nim extends Remote {

    int jouer(int nbAllumettes) throws RemoteException;

    boolean isSomeonePlaying() throws RemoteException;

    int iAmPlaying() throws RemoteException;

    boolean isGameRunning() throws RemoteException;
}
