package TP5;

public class PiThread extends Thread {
    private double min;
    private double max;
    private double result;

    public PiThread(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double calculePi(){
        double res = 0.0;
        double one = -1;
        for (double i = min; i < max-1; i++) {
            one = -one;
            res += one/(2*i+1.0);
        }

        return res*4;

    }

    public void run() {
        this.result = this.calculePi();
    }

    public double getResult() {
        return result;
    }
}
