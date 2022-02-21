import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is used for modeling a graphical user interface for the Big Two card game.
 *
 * @author Zhuangcheng Gu
 */
public class BigTwoGUI implements CardGameUI{
    private BigTwo game; // a BigTwo Object
    private BigTwoClient client; // a BigTwoClient Object
    private boolean[] selected; // selected cards
    private int activePlayer; // the No. of player (from 0 to 3)
    private JFrame frame; // the window frame
    private JPanel bigTwoPanel; // the panel showing players and cards
    private JButton playButton; // the play button Object
    private JButton passButton; // the pass button Object
    private JTextArea msgArea; // the message area Object
    private JTextArea chatArea; // the chat area Object
    private JTextField chatInput; // the chat input box Object
    private JMenu gameMenu; // the game menu Object
    private JMenu messageMenu; // the menu message Object
    private JLabel messageLabel; // the message label Object

    private static final String DIR = System.getProperty("user.dir") + "\\ResourcePack\\"; // Directory for resource
    private static final ArrayList<Image> CARD_IMAGES = new ArrayList<Image>(52); // Resource for cards
    private static final ArrayList<Image> AVATAR_IMAGES = new ArrayList<Image>(4); // Resource for avatars
    private static final Image CARD_BACK = new ImageIcon(DIR + "b.gif").getImage(); // Resource for card Back

    /**
     * Creates and returns an instance of the BigTwoUI class.
     * Params:
     *
     * @param game a BigTwo object associated with this UI
     */
    public BigTwoGUI(BigTwo game) {
        this.game = game;
        setActivePlayer(game.getCurrentPlayerIdx());
        selected = new boolean[13];
        resourceInit();
        frameInit();
//        client = new BigTwoClient(game, this);
    }

    /*
    Initialize the frame
     */
    private void frameInit() {
        // Initialize Frame
        frame = new JFrame("Big Two");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(900, 600));
        frame.pack();

        bigTwoPanel = new BigTwoPanel();
        bigTwoPanel.setBorder(new EmptyBorder(5,5,5,5));
        bigTwoPanel.setBackground(new Color(0,163,94));

        gameMenu = new javax.swing.JMenu("Game");
        messageMenu = new javax.swing.JMenu("Message");
        MenuBar menuBar = new MenuBar();

        playButton = new JButton("Play");
        playButton.addActionListener(new PlayButtonListener());
        passButton = new JButton("Pass");
        passButton.addActionListener(new PassButtonListener());
        chatInput = new JTextField();
        messageLabel = new JLabel("Message:");
        BottomPanel bottomPanel = new BottomPanel();

        msgArea = new JTextArea();
        msgArea.setEditable(false);
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        RightPanel rightPanel = new RightPanel();

        client = new BigTwoClient(game, this);

        frame.add(bigTwoPanel, BorderLayout.CENTER);
        frame.add(menuBar, BorderLayout.NORTH);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        disable();
        frame.repaint();
    }

    /*
    Initialize resources
     */
    private void resourceInit() {
        String rankCov = "a23456789tjqk";
        String suitCov = "dchs";
        for (int i = 0; i < 52; i++) {
            String cardFileName = DIR + rankCov.charAt(i % 13) + suitCov.charAt(i / 13) + ".gif";
            CARD_IMAGES.add(new ImageIcon(cardFileName).getImage());
        }

//        ArrayList<Integer> avatarSerial = new ArrayList<Integer>();
//        for (int i = 0; i <= 11; i++) {
//            avatarSerial.add(i);
//        }
//        Collections.shuffle(avatarSerial);
        ArrayList<Integer> avatarSerial = new ArrayList<Integer>();
        avatarSerial.add(4);
        avatarSerial.add(2);
        avatarSerial.add(7);
        avatarSerial.add(10);

        for (int i = 0; i < 4; i++) {
            String avatarFileName = DIR + "player" + avatarSerial.get(i) + ".png";
            AVATAR_IMAGES.add(new ImageIcon(avatarFileName).getImage());
        }
    }

    /**
     * Sets the index of the active player (i.e., the current player).
     *
     * @param activePlayer an int value representing the index of the active player
     */
    @Override
    public void setActivePlayer(int activePlayer) {
        if (activePlayer < 0 || activePlayer >= 4) {
            this.activePlayer = -1;
        } else {
            this.activePlayer = activePlayer;
        }
    }

    /**
     * Return the client of the big two game.
     *
     * @return big two client
     */
    public BigTwoClient getClient() {
        return client;
    }

    /**
     * Repaints the user interface.
     */
    @Override
    public void repaint() {
        resetSelected();
        frame.repaint();
    }

    /**
     * Prints the specified string to the message area of the card game user
     * interface.
     *
     * @param msg the string to be printed to the message area of the card game user
     *            interface
     */
    @Override
    public void printMsg(String msg) {
        msgArea.append(msg);
        msgArea.setCaretPosition(msgArea.getDocument().getLength());
    }

    /**
     * Prints the specified string to the chat area of the card game user
     * interface.
     *
     * @param msg the string to be printed to the chat area of the card game user
     *            interface
     */
    public void printChatMsg(String msg) {
        chatArea.append(msg);
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    /**
     * Clears the message area of the card game user interface.
     */
    @Override
    public void clearMsgArea() {
        msgArea.setText("");
    }

    /**
     * Resets the card game user interface.
     */
    @Override
    public void reset() {
        resetSelected();
        clearMsgArea();
        enable();
    }

    /**
     * Enables user interactions.
     */
    @Override
    public void enable() {
        if (activePlayer == client.getPlayerID()) {
            playButton.setEnabled(true);
            passButton.setEnabled(true);
//            bigTwoPanel.setEnabled(true);
        }
    }

    /**
     * Disables user interactions.
     */
    @Override
    public void disable() {
        playButton.setEnabled(false);
        passButton.setEnabled(false);
//        bigTwoPanel.setEnabled(false);
    }

    /**
     * This method convert the selected array to cardIdx required by BigTwo Object.
     *
     * @return cardIdx - an array to store whether the card on index i is selected
     */
    public int[] getSelected() {
        int[] cardIdx = null;
        int count = 0;
        for (boolean b : selected) {
            if (b) {
                count++;
            }
        }

        if (count != 0) {
            cardIdx = new int[count];
            count = 0;
            for (int j = 0; j < selected.length; j++) {
                if (selected[j]) {
                    cardIdx[count] = j;
                    count++;
                }
            }
        }
        return cardIdx;
    }

    /*
    This method reset the selected array.
     */
    private void resetSelected() {
        Arrays.fill(selected, false);
    }

    /**
     * Prompts active player to select cards and make his/her move.
     */
    @Override
    public void promptActivePlayer() {
        printMsg(game.getPlayerList().get(activePlayer).getName() + "'s turn: \n");
        enable();
        repaint();
    }

    /*
    This inner class describe the state and behaviour of a BigTwoPanel Object and implements MouseListener
     */
    private class BigTwoPanel extends JPanel implements MouseListener {
        private int height; // the height of a panel
        private double cardHeight; // the height of card
        private double cardWidth; // the width of card
        private double cardSeparate; // the separation between cards

        /*
        The constructor for BigTwoPenal Object
         */
        private BigTwoPanel() {
            addMouseListener(this);
        }

        /**
         * The method paint all the components in the BigTwoPanel Object
         *
         * @param g - a Graphics Object
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(new Color(0,163,94));
            Graphics2D g2 = (Graphics2D)g;

            height = Math.max((frame.getHeight() - 90) / 5, 102);
            cardHeight = (double) height / 102 * 73;
            cardWidth = cardHeight / 73 * 56;
            cardSeparate = cardWidth / 56 * 20;

            // print separator
            for (int i = 0; i < 4; i++) {
                g2.drawLine(0, height * (i + 1), this.getWidth(), height * (i + 1));
            }

            // print user name and avatar
            for (int i = 0; i < 4; i++) {
                if (game.getPlayerList().get(i).getName().equals("")) continue;
                g2.setColor(Color.BLACK);
                if (i == activePlayer && !game.endOfGame()) g.setColor(Color.GREEN);
                if (client.getPlayerID() == i) {
                    g.setColor(Color.BLUE);
                    g2.drawString("You",5,15+height*i);
                } else {
                    g2.drawString(game.getPlayerList().get(i).getName(),5,15+height*i);
                }
                g2.drawImage(AVATAR_IMAGES.get(i),5,25+height*i,70,70, this);
            }
            g2.setColor(Color.BLACK);

            // print cards
            for (int i = 0; i < 4; i++) {
                CardList cards = game.getPlayerList().get(i).getCardsInHand();
                if (i == client.getPlayerID() || game.endOfGame()) {
                    for (int j = 0; j < cards.size(); j++) {
                        Card card = cards.getCard(j);
                        Image imageFile = CARD_IMAGES.get(card.rank + card.suit * 13);
                        if (selected[j]) {
                            g2.drawImage(imageFile, 95 + (int) cardSeparate * j, 15 + height * i, (int) cardWidth, (int) cardHeight, this);
                        } else {
                            g2.drawImage(imageFile, 95 + (int) cardSeparate * j, 22 + height * i, (int) cardWidth, (int) cardHeight, this);
                        }
                    }
                } else {
                    for (int j = 0; j < cards.size(); j++) {
                        g2.drawImage(CARD_BACK,95 + (int) cardSeparate * j, 22 + height * i,(int) cardWidth, (int) cardHeight,this);
                    }
                }
            }

            // print last hand
            if (!game.getHandsOnTable().isEmpty()) {
                Hand lastHand = game.getHandsOnTable().get(game.getHandsOnTable().size() - 1);
                g2.drawString("Played by " + lastHand.getPlayer().getName(), 5, height * 4 + 15);
                for (int i = 0; i < lastHand.size(); i++) {
                    Card card = lastHand.getCard(i);
                    Image imageFile = CARD_IMAGES.get(card.rank + card.suit * 13);
                    g2.drawImage(imageFile,5 + (int) cardSeparate * i,height * 4 + 25, (int) cardWidth, (int) cardHeight,this);
                }
            }
        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            if (game.endOfGame()) return;
            height = Math.max((frame.getHeight() - 90) / 5, 102);
            cardHeight = (double) height / 102 * 73;
            cardWidth = cardHeight / 73 * 56;
            cardSeparate = cardWidth / 56 * 20;
            int x = e.getX();
            int y = e.getY();

            int numOfCards = game.getPlayerList().get(activePlayer).getNumOfCards();
            for (int i = numOfCards - 1; i >= 0; i--) {
                int isSelected = (selected[i])? 1 : 0;
                int up = 22 + height * activePlayer - isSelected * 7;
                int down = up + (int) cardHeight;
                int left = 95 + (int) cardSeparate * i;
                int right = left - 1 + (int) cardWidth;
                if (contain(x, y, up, down, left, right)) {
                    selected[i] = !selected[i];
                    break;
                }
            }
            frame.repaint();
        }

        /**
         * Invoked when the mouse enters a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        /**
         * Invoked when the mouse exits a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }
        /**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {

        }
        /**
         * Invoked when a mouse button has been pressed on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }

        /*
        This method returns whether the coordinates (x, y) falls in the region
         */
        private boolean contain(int x, int y, int up, int down, int left, int right) {
            return x >= left && x <= right && y >= up && y <= down;
        }
    }

    /*
    This inner class is used for modeling the MenuBar Panel.
     */
    private class MenuBar extends JMenuBar {
        // constructor for MenuBar Object
        private MenuBar () {
            JMenuItem connect = new JMenuItem("Connect");
            connect.addActionListener(new ConnectMenuItemListener());
            JMenuItem quit = new JMenuItem("Quit");
            quit.addActionListener(new QuitMenuItemListener());
            JMenuItem clrMsg = new JMenuItem("Clear game message");
            clrMsg.addActionListener(e -> msgArea.setText(""));
            JMenuItem clrChat = new JMenuItem("Clear chat history");
            clrChat.addActionListener(e -> chatArea.setText(""));
            gameMenu.add(connect);
            gameMenu.add(quit);
            messageMenu.add(clrMsg);
            messageMenu.add(clrChat);
            add(gameMenu);
            add(messageMenu);
        }
    }

    /*
    This inner class is used for modeling the Bottom Panel.
     */
    private class BottomPanel extends JPanel {
        // constructor for BottomPanel Object
        private BottomPanel() {
            chatInput.addKeyListener(new ChatInputListener());
            add(Box.createHorizontalStrut(160));
            add(playButton);
            add(Box.createHorizontalStrut(50));
            add(passButton);
            add(Box.createHorizontalStrut(180));
            add(messageLabel);
            chatInput.setPreferredSize(new Dimension(260, 20));
            add(chatInput);
        }
    }

    /*
    This inner class is used for modeling the Right Panel.
     */
    private class RightPanel extends JPanel {
        // constructor for RightPanel Object
        public RightPanel() {
            int width = frame.getWidth() / 550 * 350;
            int height = frame.getHeight() / 2;
            setPreferredSize(new Dimension(width,560));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JPanel rightUpperPanel = new JPanel();
            rightUpperPanel.setPreferredSize(new Dimension(width, height));
            rightUpperPanel.setLayout(new BorderLayout(0,0));
            JPanel rightLowerPanel = new JPanel();
            rightLowerPanel.setPreferredSize(new Dimension(width, height));
            rightLowerPanel.setLayout(new BorderLayout(0,0));

            chatArea.setForeground(new Color(42,42,255));
            JScrollPane msgSP = new JScrollPane(msgArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            msgSP.setPreferredSize(new Dimension(width, 0));
            DefaultCaret msgCaret = (DefaultCaret)msgArea.getCaret();
            msgCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            JScrollPane chatSP = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            DefaultCaret chatCaret = (DefaultCaret)chatArea.getCaret();
            chatCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

            rightUpperPanel.add(msgSP);
            rightLowerPanel.add(chatSP);
            add(rightUpperPanel);
            add(rightLowerPanel);
        }
    }

    /*
    This inner class is used for modeling the Play Button Listener.
     */
    private class PlayButtonListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            boolean flag = false;
            for (boolean b : selected) {
                if (b) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                disable();
                game.makeMove(activePlayer, getSelected());
            }
            repaint();
        }
    }

    /*
    This inner class is used for modeling the Play Button Listener.
     */
    private class PassButtonListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            disable();
            game.makeMove(activePlayer, null);
            repaint();
        }
    }

    /*
    This inner class is used for modeling the Restart MenuItem Listener.
     */
    private class ConnectMenuItemListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(game.getPlayerList().get(client.getPlayerID()).getName().equals("")) {
                client.connect();
            } else {
                JOptionPane.showMessageDialog(frame, "Already connected to the server!");
            }
        }
    }

    /*
    This inner class is used for modeling the Restart MenuItem Listener.
     */
    private class QuitMenuItemListener implements ActionListener {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /*
    This inner class is used for modeling the ChatInput Listener.
     */
    private class ChatInputListener implements KeyListener {

        /**
         * Invoked when a key has been typed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key typed event.
         *
         * @param e the event to be processed
         */
        @Override
        public void keyTyped(KeyEvent e) {

        }

        /**
         * Invoked when a key has been pressed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key pressed event.
         *
         * @param e the event to be processed
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                String input = chatInput.getText();
                chatInput.setText("");
                client.sendMessage(new CardGameMessage(CardGameMessage.MSG, activePlayer, input));
            }
        }

        /**
         * Invoked when a key has been released.
         * See the class description for {@link KeyEvent} for a definition of
         * a key released event.
         *
         * @param e the event to be processed
         */
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
