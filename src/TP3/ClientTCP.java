package TP3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Client basique TCP
 * 
 */
public class ClientTCP
{

	private Socket socket;
	private InetSocketAddress inetSocketAddress;

	public ClientTCP(String host, int port){
		initConnection(host, port);
	}

	private void initConnection(String host, int port) {
		System.out.println("Demarrage du client ...");
		//Creation de la socket
		socket = new Socket();

		// Connexion au serveur
		inetSocketAddress = new InetSocketAddress(host, port);
		try {
			socket.connect(inetSocketAddress);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Le client cree une socket, envoie un message au serveur
	 * 
	 */
	public void send(String message) throws IOException
	{
		// Envoi de la requete
		byte[] bufE = new String(message).getBytes();
		OutputStream os = socket.getOutputStream();
		os.write(bufE);
		System.out.println("Message envoye : " + message);
	}

	/**
	 * Le client attend la reponse du serveur
	 *
	 */
	public String receive() throws IOException {

		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		int lenBufR = is.read(bufR);
		String reponse = "";
		if (lenBufR!=-1)
		{
			reponse = new String(bufR, 0 , lenBufR );
			System.out.println("Reponse recue = "+reponse);
		}
		else
		{
			System.out.println("Aucune reponse recue");
		}
		return reponse;
	}

	/**
	 * Fermeture de la socket
	 *
	 */
	public void close() {
		// Fermeture de la socket
		try {
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Arret du client .");
	}
}
