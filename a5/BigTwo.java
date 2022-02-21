import javax.swing.*;
import java.util.ArrayList;

/**
 * This class implements the BigTwo game interface. it creates a console
 * based game without graphical interface. It contains the info of the game
 * such as the card deck and the player in the game.
 *
 * @author Zhuangcheng Gu
 */
public class BigTwo implements CardGame{

    // private variables
    private int numOfPlayers = 4; // an int to store the number of player
    private Deck deck; // a Deck object to store the cards
    private ArrayList<CardGamePlayer> playerList = new ArrayList<CardGamePlayer>(); // an ArrayList to store a list of players
    private ArrayList<Hand> handsOnTable = new ArrayList<Hand>(); // an ArrayList to store a list of hands played on table
    private int currentPlayerIdx; // the index of the current player
    private BigTwoGUI gui; // the ui object for providing the game interface


    /**
     * The constructor of the BigTwo Object. It creates a BigTwo Object and
     * add the player to the playerList, and create an instance of BigTwoUI.
     */
    public BigTwo() {
        for (int i = 0; i < 4; i++) {
            playerList.add(new CardGamePlayer(""));
        }
        gui = new BigTwoGUI(this);

    }

    /**
     * This method return the number of players in the game.
     *
     * @return number of players
     */
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     * This method return the card deck.
     *
     * @return card deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * This method return a list of player in the game.
     *
     * @return a list of player
     */
    public ArrayList<CardGamePlayer> getPlayerList() {
        return playerList;
    }

    /**
     * This method return the hands on table.
     *
     * @return a list contain hands on table
     */
    public ArrayList<Hand> getHandsOnTable() {
        return handsOnTable;
    }

    /**
     * This method return the index of the current player.
     *
     * @return index of current player
     */
    public int getCurrentPlayerIdx() {
        return currentPlayerIdx;
    }



    /**
     * This method (re)starts the game, distribute cards,
     * and prompt player move until the end of game.
     *
     * @param deck the deck of (shuffled) cards to be used in this game
     */
    public void start(Deck deck) {
        handsOnTable = new ArrayList<Hand>();
        this.deck = deck;
        for (CardGamePlayer player : playerList) {
            player.removeAllCards();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                playerList.get(i).addCard(deck.getCard(i * 13 + j));
                if (deck.getCard(i * 13 + j).equals(new BigTwoCard(0, 2))) {
                    currentPlayerIdx = i;
                    gui.setActivePlayer(i);
                }
            }
            playerList.get(i).getCardsInHand().sort();
        }
        gui.repaint();
        gui.promptActivePlayer();
    }

    /**
     * This method check and make the move of the current player,
     * and update the handsOnTable is the user move is valid by sending
     * a message to the server.
     *
     * @param playerIdx the index of the player who makes the move
     * @param cardIdx   the list of the indices of the cards selected by the player
     */
    public void makeMove(int playerIdx, int[] cardIdx) {
        gui.getClient().sendMessage(new CardGameMessage(CardGameMessage.MOVE, playerIdx, cardIdx));
    }

    /**
     * This method check whether the move of the current player is valid,
     * it prints out the warning message is th move is invalid.
     *
     * @param playerIdx the index of the player who makes the move
     * @param cardIdx   the list of the indices of the cards selected by the player
     */
    public void checkMove(int playerIdx, int[] cardIdx) {
        boolean illegalMove = false;
        CardList cards = null;
        if (handsOnTable.isEmpty()) {
            if (cardIdx == null){
                gui.printMsg("Not a legal move!!!\n");
                illegalMove = true;
            } else {
                cards = playerList.get(playerIdx).play(cardIdx);
                boolean flag = false;
                for (int i = 0; i < cards.size(); i++) {
                    if (cards.getCard(i).equals(new BigTwoCard(0,2))) {
                        flag = true;
                    }
                }
                if (!flag) {
                    gui.printMsg("Not a legal move!!!\n");
                    illegalMove = true;
                }
                Hand thisHand = composeHand(playerList.get(playerIdx), cards);
                if (thisHand == null) {
                    gui.printMsg("Not a legal move!!!\n");
                    illegalMove = true;
                }
            }
        } else {
            cards = playerList.get(playerIdx).play(cardIdx);
            if (cardIdx != null) {
                cards.sort();
                Hand thisHand = composeHand(playerList.get(playerIdx), cards);
                if (thisHand == null && cardIdx.length != 0 || checkMoveHelper(playerIdx, thisHand)) { //
                    gui.printMsg("Not a legal move!!!\n");
                    illegalMove = true;
                }
            } else if (!handsOnTable.isEmpty() &&
                    handsOnTable.get(handsOnTable.size() - 1).getPlayer().equals(playerList.get(playerIdx))) {
                gui.printMsg("Not a legal move!!!\n");
                illegalMove = true;
            }
        }
        if (illegalMove) {
            gui.promptActivePlayer();
        } else {
            if (cardIdx == null) {
                currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                gui.setActivePlayer(currentPlayerIdx);
                gui.printMsg("{Pass}\n");
                gui.repaint();
                gui.promptActivePlayer();
            } else {
                Hand thisHand = composeHand(playerList.get(playerIdx), cards);
                playerList.get(playerIdx).removeCards(cards);
                currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                handsOnTable.add(thisHand);

                gui.printMsg("{" + thisHand.getType() + "} ");
                gui.setActivePlayer(currentPlayerIdx);
                gui.printMsg(cards + "\n");
                if (endOfGame()) {
                    StringBuilder text = new StringBuilder();
                    for (int i = 0; i < 4; i++) {
                        if (currentPlayerIdx == (i + 1) % 4) {
                            text.append(playerList.get(i).getName()).append(" wins the game.\n");
                        } else {
                            text.append(playerList.get(i).getName()).append(" has ").append(playerList.get(i).getNumOfCards()).append(" cards in hand.\n");
                        }
                    }
                    String title = "Game ends";
                    int optionType = JOptionPane.OK_CANCEL_OPTION;
                    int result = JOptionPane.showConfirmDialog(null, text.toString(), title, optionType);
                    if (result == JOptionPane.OK_OPTION) {
                        gui.getClient().sendMessage(new CardGameMessage(CardGameMessage.READY, -1, null));
                    } else {
                        System.exit(0);
                    }
                } else {
                    gui.repaint();
                    gui.promptActivePlayer();
                }
            }
        }
    }

    /* This is a helper method for the checkMove */
    private boolean checkMoveHelper(int playerIdx, Hand thisHand) {
        if (handsOnTable.isEmpty()) return false;
        Hand lastHand = handsOnTable.get(handsOnTable.size() - 1);
        return !lastHand.getPlayer().equals(playerList.get(playerIdx)) && !thisHand.beats(lastHand);
    }

    /**
     * This method return whether the game is end.
     * The game ends if any player has no card in hand.
     *
     * @return a boolean to represent the ending state.
     */
    public boolean endOfGame() {
        for (CardGamePlayer player : playerList) {
            if (player.getNumOfCards() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This is the main method of the project, the program starts here.
     *
     * @param args the input arguments when executing the program
     */
    public static void main(String[] args) {
        BigTwo game = new BigTwo();
    }

    /**
     * This method return th type  of hand according to the cards the player selected.
     * if the hand is invalid, the method will return a null value.
     *
     * @param player the player(owner) of the hand
     * @param card the hand in the form of a card list
     * @return the hand type of the cardList
     */
    public static Hand composeHand(CardGamePlayer player, CardList card) {
        Hand hand = null;

        hand = ((new Single(player, card)).isValid())? (new Single(player, card)) : null;
        hand = ((new Pair(player, card)).isValid())? new Pair(player, card) : hand;
        hand = ((new Triple(player, card)).isValid())? new Triple(player, card) : hand;
        hand = ((new Straight(player, card)).isValid())? new Straight(player, card) : hand;
        hand = ((new Flush(player, card)).isValid())? new Flush(player, card) : hand;
        hand = ((new FullHouse(player, card)).isValid())? new FullHouse(player, card) : hand;
        hand = ((new Quad(player, card)).isValid())? new Quad(player, card) : hand;
        hand = ((new StraightFlush(player, card)).isValid())? new StraightFlush(player, card) : hand;
        return hand;
    }
}
