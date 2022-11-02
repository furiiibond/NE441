package TP9;

import TP3.ServeurTCP;

public class ServerHTTP {


    private final ServeurTCP serverTCP;

    public static void main(String[] args) {
        new ServerHTTP().execute();
    }

    public ServerHTTP() {
        this.serverTCP = new ServeurTCP(80);
    }

    public void execute() {
        //String message = this.serverTCP.receive();
        // check if the message is a GET request
        //if (message.startsWith("GET")) {
        //}


    }
}

