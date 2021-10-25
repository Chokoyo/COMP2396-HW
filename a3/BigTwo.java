import java.util.ArrayList;

public class BigTwo implements CardGame{

    // private variables
    private int numOfPlayers; // an int to store the number of player
    private Deck deck; // a Deck object to store the cards
    private ArrayList<CardGamePlayer> playerList = new ArrayList<CardGamePlayer>(); // an ArrayList to store a list of players
    private ArrayList<Hand> handsOnTable = new ArrayList<Hand>(); // an ArrayList to store a list of hands played on table
    private int currentPlayerIdx; // the index of the current player
    private BigTwoUI ui; // the ui object for providing the game interface

    public BigTwo() {
        for (int i = 0; i < 4; i++) {
            playerList.add(new CardGamePlayer());
        }
        ui = new BigTwoUI(this);
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<CardGamePlayer> getPlayerList() {
        return playerList;
    }

    public ArrayList<Hand> getHandsOnTable() {
        return handsOnTable;
    }

    public int getCurrentPlayerIdx() {
        return currentPlayerIdx;
    }

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

        while (!endOfGame()) {
            ui.repaint();
            ui.promptActivePlayer();
        }
    }

    public void makeMove(int playerIdx, int[] cardIdx) {
        checkMove(playerIdx, cardIdx);
        CardList card = playerList.get(playerIdx).play(cardIdx);
        card.sort();
        Hand thisHand = composeHand(playerList.get(playerIdx), card);
        if (thisHand != null && !checkMoveHelper(playerIdx, thisHand)) {
            currentPlayerIdx = (currentPlayerIdx + 1) % 4;

            playerList.get(playerIdx).removeCards(card);
            handsOnTable.add(thisHand);

            ui.printMsg("{" + thisHand.getType() + "} ");
            ui = new BigTwoUI(this);
            ui.setActivePlayer(currentPlayerIdx);
            for (int i = 0; i < cardIdx.length; i++) {
                ui.printMsg(card + "\n");
            }
        }
        if (cardIdx.length == 0) { // TODO: Pass first, or sort() will fail
            currentPlayerIdx = (currentPlayerIdx + 1) % 4;
            ui.setActivePlayer(currentPlayerIdx);
            ui.printMsg("{Pass}\n");
        }
    }

    public void checkMove(int playerIdx, int[] cardIdx) {
        CardList card = playerList.get(playerIdx).play(cardIdx);
        if (cardIdx != null) {
            card.sort();
            Hand thisHand = composeHand(playerList.get(playerIdx), card);
            if (thisHand == null && cardIdx.length != 0 || checkMoveHelper(playerIdx, thisHand)) { //
                ui.printMsg("Not a legal move!!!\n");
            }
        } else if (!handsOnTable.isEmpty() &&
                handsOnTable.get(handsOnTable.size() - 1).equals(playerList.get(playerIdx).getCardsInHand())) {
            ui.printMsg("Not a legal move!!!\n");
        }
    }

    private boolean checkMoveHelper(int playerIdx, Hand thisHand) {
        if (handsOnTable.isEmpty()) return false;
        Hand lastHand = handsOnTable.get(handsOnTable.size() - 1);
        if (!lastHand.equals(playerList.get(playerIdx).getCardsInHand())) {
            if (lastHand.getType() != thisHand.getType() ||
                    lastHand.getTopCard().compareTo(thisHand.getTopCard()) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean endOfGame() {
        for (CardGamePlayer player : playerList) {
            if (player.getNumOfCards() == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BigTwo game = new BigTwo();
        Deck deck = new BigTwoDeck();
        deck.shuffle();
        game.start(deck);
    }

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
