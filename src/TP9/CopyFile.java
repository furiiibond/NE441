package TP9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CopyFile {

    public static void main(String[] args) throws IOException {
        String fromFile = "esisar.bmp";
        String toFile = "esisar2.bmp";

        System.out.println("Debut de la copie de " + fromFile + " vers "+ toFile);
        FileInputStream fis = new FileInputStream(fromFile);

        byte[] bs = new byte[64*1024];
        int len = 0;
        String str = "";

        while (len != -1) {
            str = str + new String(bs, 0, len, StandardCharsets.UTF_8); // ENCODING
            len = fis.read(bs);
        }
        fis.close();

        FileOutputStream fos = new FileOutputStream(toFile);
        fos.write(str.getBytes(StandardCharsets.UTF_8));
        fos.close();
        System.out.println("Fin de la copie");
    }

    // on peut trouver des combinaisons de caractères qui ne sont pas dans l'encodage UTF-8 (ex: é) et qui sont dans l'encodage ISO-8859-1
    // l'image est donc corrompue en UTF-8 mais pas en ISO-8859-1 (caractères spéciaux non reconnus)
}