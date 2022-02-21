/**
 * This class is a subclass of the Hand class. It models the triple
 * hand type in the BigTwo game, and also checks whether a hand is triple.
 *
 * @author Zhuangcheng Gu
 */
public class Triple extends Hand {

    /**
     * Create an instance of the Triple card by calling the superclass
     * constructor.
     *
     * @param player of the hand
     * @param card list of the hand
     */
    public Triple(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    /**
     * Return whether the current hand is a valid triple hand.
     *
     * @return whether the current hand is a valid triple hand
     */
    public boolean isValid() {
        if (this.size() != 3) {
            return false;
        }
        if (this.getCard(0).getRank() == this.getCard(1).getRank() &&
                this.getCard(2).getRank() == this.getCard(1).getRank()) {
            return true;
        }
        return false;
    }

    /**
     * Return the type of current hand.
     *
     * @return the type of current hand "Triple"
     */
    public String getType() {
        return "Triple";
    }
}
