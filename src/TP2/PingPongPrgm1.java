package TP2;

import java.awt.*;

public class PingPongPrgm1 {

    private static final int PORT = 4001;
    private Television screenDisplay;

    private ClientUDP clientUDP;

    public PingPongPrgm1() {
        screenDisplay = new Television(PORT, Color.RED, "PING");
        clientUDP = new ClientUDP("127.0.0.1", 4002);
    }

    public static void main(String[] args) {
        PingPongPrgm1 prog1 = new PingPongPrgm1();
        prog1.execute();
    }
    public void execute() {
        int i = 0;

        while (i < 100) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            screenDisplay.changeColor("green");
            clientUDP.sendMessage("red");
            String rep = screenDisplay.receive();
            System.out.println(rep.length());

            if (rep.equals("red")) {
                screenDisplay.changeColor("red");
            }
            i++;
        }

        screenDisplay.close();
        clientUDP.close();

    }
}
