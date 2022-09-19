package TP2;

/*
The server program will work as follows:

it manages the registration of clients one after the other
the last client connects (for this, a parameter on the command line allows to indicate to the client program that it is the last, the last client registers with a specific message)
the server program then sends orders to the clients to manage the display of the colors correctly
 */

public class ChenillardAutoServeur {

    private static final int DEFAULTPORT = 4000;

    private ServerUDP serverUDP;

    public ChenillardAutoServeur(int port) {
        serverUDP = new ServerUDP(port);

    }

    public static void main(String[] args) {

        ChenillardAutoServeur chenillard = new ChenillardAutoServeur(DEFAULTPORT);
        chenillard.execute();

    }

    private void execute() {
        String message = "";
        // wait for the last client to register
        while (!message.equals("last")) {
            message = serverUDP.receive();
        }
        // starting the game
        while (true) {
            for (int i = 0; i < serverUDP.getNumberOfClients(); i++) {
                serverUDP.send("red", i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                serverUDP.send("green", i);
            }
        }
    }

}
