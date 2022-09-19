package TP2;

/*
The client program will be launched N times and corresponds to a flashing terminal, the server program will be launched only once. The operation of the client program will be as follows:

start the program
it registers with the server
it waits for the server's orders: an order to turn red, an order to turn green
 */


import java.awt.*;

public class ChenillardAutoClient {

    private static final int DEFAULTPORT = 4000;
    private final Television screenDisplay;
    private ClientUDP clientUDP;

    public ChenillardAutoClient(boolean isLastOne) {
        screenDisplay = new Television(0, Color.GREEN, "PLAYER"); // 0 = no serveur started
        clientUDP = new ClientUDP("127.0.0.1", DEFAULTPORT);
        if (isLastOne) {
            clientUDP.sendMessage("last");
        } else {
            clientUDP.sendMessage("register");
        }
    }

    public static void main(String[] args) {
        boolean isLastOne = args[0].equals("last");

        ChenillardAutoClient chenillard = new ChenillardAutoClient(isLastOne);

        chenillard.execute();

    }

    private void execute() {
        while (true) {
            String message = clientUDP.receiveMessage();
            if (message.equals("red")) {
                screenDisplay.changeColor("red");
            } else {
                screenDisplay.changeColor("green");
            }
        }
    }


}
