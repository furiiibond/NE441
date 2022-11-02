package TP8JeuxNIM;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class NimServer
{
    public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException
    {
        // Adresse IP du serveur
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");

        // Crée un objet réel capable de faire des calculs
        NimImpl nimImpl = new NimImpl();

        // Crée un serveur écoutant sur le port 7070, permettant d'accéder à cet objet réel
        Nim skeleton = (Nim) UnicastRemoteObject.exportObject(nimImpl,7070);

        //  Il y a un registry sur votre machine sur le port 5050
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",5050);

        // On indique au registry qu'un service est présent sur notre machine, avec son nom
        registry.bind("Nim", skeleton);

        // TODO check 2 clients in order to start

        System.out.println("Le serveur est prêt ...");
    }
}