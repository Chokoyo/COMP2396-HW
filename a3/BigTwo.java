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
    private BigTwoUI ui; // the ui object for providing the game interface


    /**
     * The constructor of the BigTwo Object. It creates a BigTwo Object and
     * add the player to the playerList, and create an instance of BigTwoUI.
     */
    public BigTwo() {
        for (int i = 0; i < 4; i++) {
            playerList.add(new CardGamePlayer());
        }
        ui = new BigTwoUI(this);
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
        this.deck = deck;

        for (CardGamePlayer player : playerList) {
            player.removeAllCards();
        }
        handsOnTable = new ArrayList<Hand>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                playerList.get(i).addCard(deck.getCard(i * 13 + j));
                if (deck.getCard(i * 13 + j).equals(new BigTwoCard(0, 2))) {
                    currentPlayerIdx = i;
                    ui.setActivePlayer(i);
                }
            }
            playerList.get(i).getCardsInHand().sort();
        }
        ui.repaint();
        ui.promptActivePlayer();
    }

    /**
     * This method check and print out the move of the current player,
     * and update the handsOnTable is the user move is valid. Otherwise,
     * prompt the player to move again.
     *
     * @param playerIdx the index of the player who makes the move
     * @param cardIdx   the list of the indices of the cards selected by the player
     */
    public void makeMove(int playerIdx, int[] cardIdx) {
        checkMove(playerIdx, cardIdx);
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
                ui.printMsg("Not a legal move!!!\n");
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
                    ui.printMsg("Not a legal move!!!\n");
                    illegalMove = true;
                }
            }

        } else {
            cards = playerList.get(playerIdx).play(cardIdx);
            if (cardIdx != null) {
                cards.sort();
                Hand thisHand = composeHand(playerList.get(playerIdx), cards);
                if (thisHand == null && cardIdx.length != 0 || checkMoveHelper(playerIdx, thisHand)) { //
                    ui.printMsg("Not a legal move!!!\n");
                    illegalMove = true;
                }
            } else if (!handsOnTable.isEmpty() &&
                    handsOnTable.get(handsOnTable.size() - 1).getPlayer().equals(playerList.get(playerIdx))) {
                ui.printMsg("Not a legal move!!!\n");
                illegalMove = true;
            }
        }
        if (illegalMove) {
            ui.promptActivePlayer();
        } else {
            if (cardIdx == null) {
                currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                ui.setActivePlayer(currentPlayerIdx);
                ui.printMsg("{Pass}\n\n");
                ui.repaint();
                ui.promptActivePlayer();
            } else {
                Hand thisHand = composeHand(playerList.get(playerIdx), cards);
                playerList.get(playerIdx).removeCards(cards);
                currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                handsOnTable.add(thisHand);

                ui.printMsg("{" + thisHand.getType() + "} ");
                ui = new BigTwoUI(this);
                ui.setActivePlayer(currentPlayerIdx);
                ui.printMsg(cards + "\n\n");
                if (endOfGame()) {
                    ui.printMsg("Game ends\n");
                    for (int i = 0; i < 4; i++) {
                        if (currentPlayerIdx == (i + 1) % 4) {
                            ui.printMsg("Player " + i + " wins the game.\n");
                        } else {
                            ui.printMsg("Player " + i + " has " + playerList.get(i).getNumOfCards() + " cards in hand.\n");
                        }
                    }
                } else {
                    ui.repaint();
                    ui.promptActivePlayer();
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
        Deck deck = new BigTwoDeck();
        deck.shuffle();
        game.start(deck);
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
