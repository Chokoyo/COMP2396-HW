import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a subclass of CardList. It models a hand of card in a
 * big two game. It stores the info of the cards in hand can be used
 * to compare whether a hand can beat another.
 *
 * @author Zhuangcheng Gu
 */
public abstract class Hand extends CardList{
    public CardGamePlayer player; // the player (owner) of the hand

    /**
     * This is a constructor of the Hand. It initializes the player
     * and the card list.
     *
     * @param player player(owner) of the hand.
     * @param card
     */
    public Hand(CardGamePlayer player, CardList card) {
        this.player = player;
        for (int i = 0; i < card.size(); i++) {
             this.addCard(card.getCard(i));
        }
    }

    /**
     * Return the player(owner) of the hand.
     *
     * @return player owner of the hand
     */
    public CardGamePlayer getPlayer() {
        return player;
    }

    /**
     * Return the topCard of the hand
     * @return the top card of the hand.
     */
    public Card getTopCard() {
        this.sort();
        return getCard(this.size() - 1);
    }

    /**
     * This method compare two hand. and return whether the current
     * hand can beat the hand given according to the big two game rule.
     *
     * @param hand the hand being compared to the current hand
     * @return a boolean represent whether the hand is beaten by current hand
     */
    public boolean beats (Hand hand) {
        if (this.getType().equals(hand.getType())) {
            return this.getTopCard().compareTo(hand.getTopCard()) > 0;
        }
        
        final ArrayList<String> handTypes = new ArrayList<String>(
                Arrays.asList( "Straight", "Flush", "FullHouse", "Quad", "StraightFlush")
        );
        
        if (!handTypes.contains(this.getType())) {
            return false;
        } else return handTypes.indexOf(this.getType()) > handTypes.indexOf(hand.getType());
    }

    /**
     * This is an abstract method. It returns whether the current hand
     * is a valid hand.
     *
     * @return a boolean represent whether the hand is valid
     */
    public abstract boolean isValid();

    /**
     * This is an abstract method. It will return type of the hand.
     *
     * @return type of the hand
     */
    public abstract String getType();
}
