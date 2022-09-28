package TP4;

import java.io.*;

public class Transfert {

    public Transfert() {
    }

    public void transfert(FileInputStream in, ObjectOutputStream out, boolean closeOnExit) throws IOException
    {
        byte buf[] = new byte[1024];

        int n;
        while((n=in.read(buf))==1024){
            out.write(buf,0,n);
        }
        //et la on ajoute les bytes plus petits que 1024 ) la fin
        out.write(buf,0,n);
        if (closeOnExit) {
            in.close();
            out.close();
        }
    }
}
