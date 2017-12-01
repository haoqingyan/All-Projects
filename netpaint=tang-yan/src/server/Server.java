////Author : Yichao Tang && Haoqing Yan
// the server, receive message from cleent, sent message to all clients.
package server;

/**
 * TO BE CHANGED to allow multiple clients the read and write a Vector of
 * PaintObjects every time a client writes a PaintObject to this server.
 * 
 * THIS CONTAINS SOME OF THE BOILERPLATE CODE PROVIDED HERE
 * 
 * Currently this server waits for one connection, reads the Clients message, which  
 * is printed to a console, writes a message to the Client, and closes the connection.
 *
 * @author Yichao Tang && Haoqing Yan
 */

//Author : Yichao Tang && Haoqing Yan
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import model.PaintObject;
//construct the server.
public class Server implements Serializable{

	private static ServerSocket sock;
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<>());

	public static final int PORT_NUMBER = 1122;

	public static void main(String[] args) throws IOException {

		sock = new ServerSocket(PORT_NUMBER);
		while (true) {
			try {
				Socket client = sock.accept();
				ObjectInputStream fromClient = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream toClient = new ObjectOutputStream(client.getOutputStream());
                clients.add(toClient);
				ClientHandler c = new ClientHandler(fromClient, clients);
				c.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
//add client handler, able to control all cilent. 
class ClientHandler extends Thread {

	private ObjectInputStream fromClient;
	private List<ObjectOutputStream> clients;

	public ClientHandler(ObjectInputStream fromClient, List<ObjectOutputStream> clients) {
		this.fromClient = fromClient;
		this.clients = clients;
	}
// run method, receive and send message. 
	public void run() {
		while (true) {
			Vector<PaintObject> a = null;
			try {
				a = (Vector<PaintObject>) fromClient.readObject();
			} catch (IOException e) {
				e.printStackTrace();
				this.cleanUp();
				return;
			} catch (ClassNotFoundException s) {
				s.printStackTrace();
				this.cleanUp();
				return;
			}
			this.writeToAllClient(a);
		}
	}
// write to all client the message received from one client. 
	private void writeToAllClient(Vector<PaintObject> a) {
		synchronized (clients) {
			for (ObjectOutputStream client : clients) {
				try {
					client.writeObject(a);
				} catch (IOException e) {
					clients.remove(client);
				}
			}
		}
	}

	// Write a method that closes all the
	// resources of a ClientHandler and logs a message, and call it from every
	// place that a fatal error occurs in ClientHandler (the catch blocks that
	// you can't recover from).
	private void cleanUp() {
		// Don't forget to close those sockets. Not an issue here, but you WILL
		// run out eventually if you neglect this.
		try {
			this.fromClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
