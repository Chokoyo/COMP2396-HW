import java.util.ArrayList;

public class BigTwo implements CardGame{

    // private variables
    private int numOfPlayers = 4; // an int to store the number of player
    private Deck deck; // a Deck object to store the cards
    private ArrayList<CardGamePlayer> playerList = new ArrayList<CardGamePlayer>(); // an ArrayList to store a list of players
    private ArrayList<Hand> handsOnTable = new ArrayList<Hand>(); // an ArrayList to store a list of hands played on table
    private int currentPlayerIdx; // the index of the current player
    private BigTwoUI ui; // the ui object for providing the game interface

    private boolean illegalMove = false;

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
        ui.printMsg("Game ends\n");
        for (int i = 0; i < 4; i++) {
            if (currentPlayerIdx == i) {
                ui.printMsg("Player " + i + " wins the game.\n");
            } else {
                ui.printMsg("Player " + i + " has " + playerList.get(i).getNumOfCards() + " cards in hand.\n");
            }
        }
    }

    public void makeMove(int playerIdx, int[] cardIdx) {
        checkMove(playerIdx, cardIdx);
        if (illegalMove) return;
        CardList card = playerList.get(playerIdx).play(cardIdx);
        if (cardIdx != null) {
            card.sort();
            Hand thisHand = composeHand(playerList.get(playerIdx), card);
            if (thisHand != null && !checkMoveHelper(playerIdx, thisHand)) {
                playerList.get(playerIdx).removeCards(card);
                if  (playerList.get(playerIdx).getNumOfCards() == 0) return;
                currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                handsOnTable.add(thisHand);

                ui.printMsg("{" + thisHand.getType() + "} ");
                ui = new BigTwoUI(this);
                ui.setActivePlayer(currentPlayerIdx);
                ui.printMsg(card + "\n");
            }
        } else if (!handsOnTable.isEmpty()) { // when cardIdx is null, and the player is not the last player
            Hand lastHand = handsOnTable.get(handsOnTable.size() - 1);
            if (!lastHand.getPlayer().equals(playerList.get(playerIdx))) {
                currentPlayerIdx = (currentPlayerIdx + 1) % 4;
                ui.setActivePlayer(currentPlayerIdx);
                ui.printMsg("{Pass}\n");
            }

        }
    }

    public void checkMove(int playerIdx, int[] cardIdx) {
        illegalMove = false;
        if (handsOnTable.isEmpty() && cardIdx == null) {
            ui.printMsg("Not a legal move!!!\n");
            ui.promptActivePlayer();
            illegalMove = true;
            return;
        }
        CardList card = playerList.get(playerIdx).play(cardIdx);
        if (cardIdx != null) {
            card.sort();
            Hand thisHand = composeHand(playerList.get(playerIdx), card);
            if (thisHand == null && cardIdx.length != 0 || checkMoveHelper(playerIdx, thisHand)) { //
                ui.printMsg("Not a legal move!!!\n");
                ui.promptActivePlayer();
                illegalMove = true;
                return;
            }
        } else if (!handsOnTable.isEmpty() &&
                handsOnTable.get(handsOnTable.size() - 1).getPlayer().equals(playerList.get(playerIdx))) {
            ui.printMsg("Not a legal move!!!\n");
            ui.promptActivePlayer();
            illegalMove = true;
            return;
        }
    }

    private boolean checkMoveHelper(int playerIdx, Hand thisHand) {
        if (handsOnTable.isEmpty()) return false;
        Hand lastHand = handsOnTable.get(handsOnTable.size() - 1);
        if (!lastHand.getPlayer().equals(playerList.get(playerIdx)) && !thisHand.beats(lastHand)) {
            return true;
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
