package TP1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ClientMult {

    public static void main(String[] args) throws Exception
    {
        // Lancement du jeu de maternelle
        String messToSend = "JOUER";
        ClientMult clientMult = new ClientMult();
        DatagramSocket socket = clientMult.initSocket();
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 11000);
        List<String> reponses = clientMult.execute(socket, adrDest, messToSend, true);
        int num1 = Integer.parseInt(reponses.get(0).split(";")[0]);
        int num2 = Integer.parseInt(reponses.get(1).split(";")[0]);
        int res = num1*num2;
        System.out.println(res);
        reponses = clientMult.execute(socket, adrDest, String.valueOf(res) + ";", false);

        System.out.println(reponses.get(0));
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client.");
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse
     */
    private DatagramSocket initSocket()
    {
        // Demarrage
        System.out.println("Demarrage du client ...");
        try {
            return new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> execute(DatagramSocket socket, InetSocketAddress adrDest, String message, Boolean multiRep) throws IOException
    {


        // Creation et envoi du message
        byte[] bufE = message.getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);
        System.out.println("Message envoyé");

        // Attente du premier paquet
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR); // bloquant

        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Réponse recue = " + reponse);
        List<String> listRep = new ArrayList<>();
        listRep.add(reponse);

        if (multiRep)
        // Attente du second paquet
        {
            byte[] bufR2 = new byte[2048];
            DatagramPacket dpR2 = new DatagramPacket(bufR2, bufR2.length);
            socket.receive(dpR2); // bloquant
            String reponse2 = new String(bufR2, dpR2.getOffset(), dpR2.getLength());
            System.out.println("Réponse 2 recue = " + reponse2);
            listRep.add(reponse2);
        }

        return listRep;
    }
}

