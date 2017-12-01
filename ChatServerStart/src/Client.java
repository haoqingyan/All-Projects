

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Client extends JFrame {

  private static final String ADDRESS = "localhost";

  public static void main(String[] args) throws UnknownHostException, IOException {
    String username = JOptionPane.showInputDialog("Username?");
    new Client(username).setVisible(true);
  }

  private DefaultListModel<String> model;
  private JTextField field;
  private Socket socket;
  private String username;
  private ObjectOutputStream oos;
  private ObjectInputStream ois;

   public Client(String username) throws UnknownHostException, IOException {
    this.username = username;
    this.setupModelAndLayout();

    try {
      // TODO 8: Connect to the Server (construct a new Socket object)
      socket = new Socket(ADDRESS, Server.SERVER_PORT);

      // TODO 9:  Get the server's input and output streams for reads and writes
      oos = new ObjectOutputStream(socket.getOutputStream());
      ois = new ObjectInputStream(socket.getInputStream());
      this.model.addElement("Connected to server at " + ADDRESS + ":" + Server.SERVER_PORT);
    } catch (IOException e) {
      e.printStackTrace();
      this.cleanUpAndQuit("Couldn't connect to the server");
    }

    // Listen for clients to send messages
    this.field.addActionListener(new FieldListener());

    // TODO 10: Start a new ServerListener thread
    ServerListener serverListener = new ServerListener();
    serverListener.start();
  }
   
   private class ServerListener extends Thread {

     @Override
     public void run() {
       // TODO 11: Repeatedly accept String objects from the server and add
       // them to our model.
       try {
         /* The server sent us a String? Stick it in the JList! */
         while (true) {
           Client.this.model.addElement((String) ois.readObject());
         }
       } catch (IOException e) {
         e.printStackTrace();
       } catch (ClassNotFoundException e) {
         e.printStackTrace();
       }
     }
   }

  private void setupModelAndLayout() {
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(500, 600);
    this.setLayout(new FlowLayout());

    this.model = new DefaultListModel<String>();

    JList<String> messages = new JList<>(model);
    JScrollPane scroll = new JScrollPane(messages);
    scroll.setPreferredSize(new Dimension(500, 500));
    this.add(scroll);

    this.field = new JTextField();
    this.field.setToolTipText("Send a message here!");
    this.field.setPreferredSize(new Dimension(500, 30));
    this.add(field);
  }

  private void cleanUpAndQuit(String message) {
    JOptionPane.showMessageDialog(Client.this, message);
    try {
      if (socket != null)
        socket.close();
    } catch (IOException e) {
      // Couldn't close the socket, we are in deep trouble. Abandon ship.
      e.printStackTrace();
    }
    Client.this.dispatchEvent(new WindowEvent(Client.this, WindowEvent.WINDOW_CLOSING));
  }

  private class FieldListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        // TODO 12: When the enter button is pressed, send the contents of
        // the JTextField to the server (add username to identify sender)
        oos.writeObject(username + ": " + field.getText());
        Client.this.field.setText("");

      } catch (IOException ioe) {
        ioe.printStackTrace();

        Client.this.cleanUpAndQuit("Couldn't send a message to the server");
      }
    }
  }

 }