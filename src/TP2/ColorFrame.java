package TP2;

import java.awt.Color;

import javax.swing.JFrame;

    /*
    * Fenetre avec un fond de couleur
    * * Ouverture d'une fenetre avec un fond de couleur vert
    * * Ouverture d'une fenetre avec un fond de couleur rouge
    *  Attente entre les changements de couleur
    */
public class ColorFrame
{

    public static void main(String[] args) throws Exception
    {
        ColorFrame c = new ColorFrame();
        c.execute();
    }

    private void execute() throws Exception
    {
        JFrame frame = new JFrame("Chenillard");
        frame.setSize(300,300);

        //
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(2000);


        //
        frame.getContentPane().setBackground(Color.RED);
        frame.setVisible(true);
        Thread.sleep(2000);


        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(2000);

        frame.dispose();

    }
}