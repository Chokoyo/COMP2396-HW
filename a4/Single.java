/**
 * This class is a subclass of the Hand class. It models the single
 * hand type in the BigTwo game, and also checks whether a hand is single.
 *
 * @author Zhuangcheng Gu
 */
public class Single extends Hand {

    /**
     * Create an instance of the Single card by calling the superclass
     * constructor.
     *
     * @param player of the hand
     * @param card list of the hand
     */
    public Single(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    /**
     * Return whether the current hand is a valid single hand.
     *
     * @return whether the current hand is a valid single hand
     */
    public boolean isValid() {
        return this.size() == 1;
    }

    /**
     * Return the type of current hand.
     *
     * @return the type of current hand "Single"
     */
    public String getType() {
        return "Single";
    }
}
