/**
 * This class is a subclass of the Hand class. It models the flush
 * hand type in the BigTwo game, and also checks whether a hand is single.
 *
 * @author Zhuangcheng Gu
 */
public class Flush extends Hand {

    /**
     * Create an instance of the Flush card by calling the superclass
     * constructor.
     *
     * @param player of the hand
     * @param card list of the hand
     */
    public Flush(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    /**
     * Return whether the current hand is a valid flush hand.
     *
     * @return whether the current hand is a valid flush hand
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
        return true;
    }

    /**
     * Return the type of current hand.
     *
     * @return the type of current hand "Flush"
     */
    public String getType() {
        return "Flush";
    }
}
