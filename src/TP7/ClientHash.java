package TP7;

import TP2.ClientUDP;


// brutforce hash recieved from server udp
public class ClientHash {

    private static final Object START = 0;
    private static final Object END = 999999999/4;
    private ClientUDP client;
    public ClientHash() {
        this.client = new ClientUDP("192.168.109.200", 3000);
        client.sendMessage("register");
    }

    public static void main(String[] args) {
        ClientHash client = new ClientHash();
        client.execute();
    }

    public void execute() {
        // wait for hash
        String hash = client.receiveMessage();
        // display hash
        System.out.println("Hash recu = " + hash);
        // create a thread of Decoder for each part of the brutforce
        for (int i = 0; i < 4; i++) {
            //new Decoder(hash, i).start();
        }

        // send mdp to server
        //client.sendMessage(mdp);
    }

}
