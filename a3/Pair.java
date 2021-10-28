/**
 * This class is a subclass of the Hand class. It models the pair
 * hand type in the BigTwo game, and also checks whether a hand is pair.
 *
 * @author Zhuangcheng Gu
 */
public class Pair extends Hand {

    /**
     * Create an instance of the Pair card by calling the superclass
     * constructor.
     *
     * @param player of the hand
     * @param card list of the hand
     */
    public Pair(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    /**
     * Return whether the current hand is a valid pair hand.
     *
     * @return whether the current hand is a valid pair hand
     */
    public boolean isValid() {
        if (this.size() != 2) {
            return false;
        }
        return this.getCard(0).getRank() == this.getCard(1).getRank();
    }

    /**
     * Return the type of current hand.
     *
     * @return the type of current hand "Pair"
     */
    public String getType() {
        return "Pair";
    }
}
