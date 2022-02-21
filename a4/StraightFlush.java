/**
 * This class is a subclass of the Hand class. It models the straight flush
 * hand type in the BigTwo game, and also checks whether a hand is straight flush.
 *
 * @author Zhuangcheng Gu
 */
public class StraightFlush extends Hand {

    /**
     * Create an instance of the StraightFlush card by calling the superclass
     * constructor.
     *
     * @param player of the hand
     * @param card list of the hand
     */
    public StraightFlush(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    /**
     * Return whether the current hand is a valid straight flush hand.
     *
     * @return whether the current hand is a valid straight flush hand
     */
    public boolean isValid() {
        if (this.size() != 5) {
            return false;
        }
        final int suit = this.getCard(0).getSuit();
        for (int i = 1; i < 5; i++) {
            if (this.getCard(i).getSuit() != suit) {
                return false;
            }
        }
        this.sort();
        final String cardsStr = "34567890JQKA2";
        final String[] RANKS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "0", "J", "Q", "K" };
        String handStr = "";
        for (int i = 0; i < 5; i++) {
            handStr += RANKS[this.getCard(i).getRank()];
        }
        return cardsStr.contains(handStr);
    }

    /**
     * Return the type of current hand.
     *
     * @return the type of current hand "StraightFlush"
     */
    public String getType() {
        return "StraightFlush";
    }
}
