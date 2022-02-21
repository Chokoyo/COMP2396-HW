import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class models a Big Two client.
 *
 * @author Zhuangcheng Gu
 */
public class BigTwoClient implements NetworkGame{
    private BigTwo game; // a BigTwo object for the Big Two card game.
    private BigTwoGUI gui; // a BigTwoGUI object for the Big Two card game.
    private Socket sock; // a socket connection to the game server.
    private ObjectOutputStream oos; // an ObjectOutputStream for sending messages to the server.
    private int playerID; // an integer specifying the playerID (i.e., index) of the local player.
    private String playerName; // a string specifying the name of the local player.
    private String serverIP; // a string specifying the IP address of the game server.
    private int serverPort; // an integer specifying the TCP port of the game server.

    /**
     * Creates and returns an instance of the BigTwoClient class.
     */
    public BigTwoClient(BigTwo game, BigTwoGUI gui) {
        this.game = game;
        this.gui = gui;
        connect();
    }

    /**
     * Returns the playerID (index) of the local player.
     *
     * @return the playerID (index) of the local player
     */
    @Override
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Sets the playerID (index) of the local player.
     *
     * @param playerID the playerID (index) of the local player.
     */
    @Override
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * Returns the name of the local player.
     *
     * @return the name of the local player
     */
    @Override
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the name of the local player.
     *
     * @param playerName the name of the local player
     */
    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Returns the IP address of the server.
     *
     * @return the IP address of the server
     */
    @Override
    public String getServerIP() {
        return serverIP;
    }

    /**
     * Sets the IP address of the server.
     *
     * @param serverIP the IP address of the server
     */
    @Override
    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    /**
     * Returns the TCP port of the server.
     *
     * @return the TCP port of the server
     */
    @Override
    public int getServerPort() {
        return serverPort;
    }

    /**
     * Sets the TCP port of the server
     *
     * @param serverPort the TCP port of the server
     */
    @Override
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * Makes a network connection to the server.
     */
    @Override
    public void connect() {
        playerName = JOptionPane.showInputDialog("Player Name:");
        if (playerName == null) { System.exit(0); }
        while (playerName == null || playerName.equals("")) {
            playerName = JOptionPane.showInputDialog("Please enter a valid name:");
        }
        try {
            serverIP = "127.0.0.1";
            serverPort = 2396;
            sock = new Socket(serverIP, serverPort);
            oos = new ObjectOutputStream(sock.getOutputStream());
        } catch (Exception ex){
            System.out.println("Failed to connect to the socket " + serverIP + ":" + serverPort);
            ex.printStackTrace();

        }
        Runnable job = new ServerHandler();
        Thread thread = new Thread(job);
        thread.start();
    }

    /**
     * Parses the specified message received from the server.
     *
     * @param message the specified message received from the server
     */
    @Override
    public synchronized void parseMessage(GameMessage message) {
        String name;
        switch (message.getType()) {
            case CardGameMessage.PLAYER_LIST:
                playerID = message.getPlayerID();
                String[] nameList = (String[]) message.getData();
                for (int i = 0; i < 4; i++) {
                    if (nameList[i] != null) {
                        game.getPlayerList().get(i).setName(nameList[i]);
                    }
                }
                sendMessage(new CardGameMessage(CardGameMessage.JOIN, -1, playerName));
                break;
            case CardGameMessage.JOIN:
                name = (String) message.getData();
                game.getPlayerList().get(message.getPlayerID()).setName(name);
                if (message.getPlayerID() == playerID) {
                    sendMessage(new CardGameMessage(CardGameMessage.READY, -1 , null));
                }
                break;
            case CardGameMessage.FULL:
                gui.printMsg("Server full");
                break;
            case CardGameMessage.QUIT:
                game.getPlayerList().get(message.getPlayerID()).setName("");
                if (!game.endOfGame()) { // if the game is in progress
                    gui.disable();
                }
                sendMessage(new CardGameMessage(CardGameMessage.READY, -1, null));
                break;
            case CardGameMessage.READY:
                name = game.getPlayerList().get(message.getPlayerID()).getName();
                gui.printMsg(name + " is ready!\n");
                break;
            case CardGameMessage.START:
                BigTwoDeck deck = (BigTwoDeck) message.getData();
                game.start(deck);
                break;
            case CardGameMessage.MOVE:
                game.checkMove(message.getPlayerID(), (int[]) message.getData());
                break;
            case CardGameMessage.MSG:
                gui.printChatMsg(message.getData() + "\n");
                break;
        }

    }

    /**
     * Sends the specified message to the server.
     *
     * @param message the specified message to be sent the server
     */
    @Override
    public synchronized void sendMessage(GameMessage message) {
        try {
            oos.writeObject(message);
        } catch(Exception ex) {
            System.out.println("Fail to write object using Object Output Stream!");
            ex.printStackTrace();
        }
    }

    /**
     * This innerclass models a big two server handler.
     */
    class ServerHandler implements Runnable {

        /**
         * When an object implementing interface {@code Runnable} is used
         * to create a thread, starting the thread causes the object's
         * {@code run} method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method {@code run} is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            CardGameMessage message;
            ObjectInputStream ois;
            try {
                // waits for messages from the server
                ois = new ObjectInputStream(sock.getInputStream());
                while ((message = (CardGameMessage) ois.readObject()) != null) {
                    parseMessage(message);
                    gui.repaint();
                    System.out.println("Message received");
                } // close while
            } catch (Exception ex) {
                System.out.println("Fail to read object using Object input Stream!");
                ex.printStackTrace();
            }
        }
    }
}
