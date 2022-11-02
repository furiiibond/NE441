package TP7;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Decoder extends Thread {
    public Decoder() {
    }

    public void run(String hash, Object start, Object end) {
        brutforce(hash,start,end);
    }

    public String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String brutforce(String hash, Object start, Object end) {
        // we know that password in md5 start by p2024esisar and end by nine digits
        // so we can brutforce the hash with a for loop
        String mdp = "";
        for (int i = (int) start; i < (int) end; i++) {
            mdp = "p2024esisar" + i;
            if (hash.equals(getMd5(mdp))) {
                return mdp;
            }
        }
        return mdp;
    }
}
