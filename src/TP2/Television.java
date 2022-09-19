package TP2;

import javax.swing.*;
import java.awt.*;

/*
*   This Television class displays a green window
*   and waits for a command sent from netcat for example
*   if it receives "red", the window turns red
*   if it receives "green", the window turns green
*
*   It works in local or in remote
*/
public class Television
{
    private JFrame screenDisplay;
    private ServerUDP serverUDP;

    /*  Constructor of the television
    *   Opens a green window and starts an UDP server on a given port
    */

    private static final int PORT = 7050;

    public Television(int port, Color color, String title) {
        screenDisplay = openWindow(color, title);
        if (port != 0) {
            serverUDP = new ServerUDP(port);
        }
    }

    public static void main(String[] args)
    {
        Television c = new Television(PORT, Color.GREEN, "Watching TV");
        c.execute();
    }

    public JFrame openWindow(Color color, String title)
    {
        JFrame frame = new JFrame(title);
        frame.setSize(187,332);
        frame.getContentPane().setBackground(color);
        frame.setVisible(true);

        return frame;
    }

    // Change color of the screen
    public void changeColor(String color)
    {
        if (color.equals("red")) {
            screenDisplay.getContentPane().setBackground(Color.RED);
            screenDisplay.setVisible(true);
        } else if (color.equals("green")) {
            screenDisplay.getContentPane().setBackground(Color.GREEN);
            screenDisplay.setVisible(true);
        }
    }

    public String receive()
    {
        return serverUDP.receive();
    }

    private void execute()
    {
        String message = "";
        while (!message.equals("exit")) {
            message = this.receive();
            changeColor(message);
        }
        close();
    }

    public void close() {
        serverUDP.close();
        screenDisplay.dispose();
    }
}