import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

  public static final int SERVER_PORT = 4001;

  private static ServerSocket sock;
  private static List<ObjectOutputStream> clients = Collections
      .synchronizedList(new ArrayList<>());

  public static void main(String[] args) throws IOException {
    sock = new ServerSocket(SERVER_PORT);
    System.out.println("Server started on port " + SERVER_PORT);

    while (true) {
      // TODO 1: Accept a connection from the ServerSocket.
      Socket s = sock.accept();

      ObjectInputStream is = new ObjectInputStream(s.getInputStream());
      ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
      // TODO 2: Add the output stream to our list of clients
      // so we can write to all clients output streams later.  
      clients.add(os);

      // TODO 3: Start a new ClientHandler thread for this client.
      ClientHandler c = new ClientHandler(is, clients);
      c.start();

      System.out.println("Accepted a new connection from " + s.getInetAddress());
    }
  }
}

class ClientHandler extends Thread {

  private ObjectInputStream input;
  private List<ObjectOutputStream> clients;

  public ClientHandler(ObjectInputStream input, List<ObjectOutputStream> clients) {
    this.input = input;
    this.clients = clients;
  }

  @Override
  public void run() {
    while (true) {

      String message = null;

      try {
        // TODO 4: Read a String from the client 
        message = (String) input.readObject();

      } catch (IOException e) {
        /* Client left -- clean up and let the thread die */
        this.cleanUp();
        return;
      } catch (ClassNotFoundException e) {
        /* This one is probably a bug though */
        e.printStackTrace();
        this.cleanUp();
        return;
      }

      this.writeStringToClients(message);
    }
  }

  private void writeStringToClients(String message) {
    synchronized (clients) {
      // TODO 5: Use an enhanced for loop to Send a string to all clients 
      // the client list by iterating over all ObjectOutputStreams in clients
      for (ObjectOutputStream client : clients) {
        try {
          client.writeObject(message);

          // TODO 6: After writeObject, do NOT forget reset(). This would be 
          // a silent error that results in the server not working.
          client.reset();

          // TODO 7: In the catch block, remove the ObjectOutputStream
          // from the list (ArrayList has remove(E element))
        } catch (IOException e) {
          // If we can't write to the client, their socket was
          // closed. Therefore, remove it from the list.
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
      this.input.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}