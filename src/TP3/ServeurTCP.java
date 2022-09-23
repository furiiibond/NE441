package TP3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Serveur basique TCP
 */
public class ServeurTCP
{
	private ServerSocket socketEcoute;
	private Socket socketConnexion;

	public ServeurTCP(int port) {
		System.out.println("Demarrage du serveur ...");
		try {
			this.socketEcoute = new ServerSocket();
			socketEcoute.bind(new InetSocketAddress(port));
			socketConnexion = null;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void send() throws IOException
	{
		// Emission d'un message en retour
		byte[] bufE = new String("ok").getBytes();
		OutputStream os = socketConnexion.getOutputStream();
		os.write(bufE);
		System.out.println("Message envoye = ok");
	}

	// wait and receive a message

	public void waitForClient() throws IOException {
		System.out.println("En attente de connexion ...");
		socketConnexion = socketEcoute.accept();
		System.out.println("Connexion etablie");
		// Affichage du port et de l'ip du client
		System.out.println("Un client est connecté");
		System.out.println("IP:"+socketConnexion.getInetAddress());
		System.out.println("Port:"+socketConnexion.getPort());
	}
	public String receive() throws IOException {

		byte[] bufR = new byte[2048];
		InputStream is = socketConnexion.getInputStream();

		int lenBufR = is.read(bufR);
		String reponse = "";
		if (lenBufR!=-1)
			reponse = new String(bufR, 0, lenBufR);
		return reponse;
	}

	/**
	 * close connection
	 */
	public void close() throws IOException
	{
		// Fermeture de la socket de connexion
		socketConnexion.close();
		// Arret du serveur
		socketEcoute.close();
		System.out.println("Arret du serveur .");
	}
		
}
