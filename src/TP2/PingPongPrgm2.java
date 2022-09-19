package TP2;

import java.awt.*;

public class PingPongPrgm2 {

    private static final int PORT = 4002;
    private Television screenDisplay;

    private ClientUDP clientUDP;

    public PingPongPrgm2() {
        screenDisplay = new Television(PORT, Color.GREEN, "PONG");
        clientUDP = new ClientUDP("127.0.0.1", 4001);
    }

    public static void main(String[] args) {
        PingPongPrgm2 prog2 = new PingPongPrgm2();
        prog2.execute();
    }

    public void execute() {
        int i = 0;

        while (i < 100) {
            String message = screenDisplay.receive();
            System.out.println(message.length());
            if (message.equals("red")) {
                screenDisplay.changeColor("red");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            screenDisplay.changeColor("green");
            clientUDP.sendMessage("red");
            i++;
        }

        screenDisplay.close();
        clientUDP.close();
    }
}
