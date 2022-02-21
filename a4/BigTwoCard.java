import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is the subclass of card. It's used to model a card
 * in big two games. Tt overrides the compareTo method of the card
 * according to the game rule of big two game.
 *
 * @author Zhuangcheng Gu
 */
public class BigTwoCard extends Card {

    /**
     * This is the constructor of the BigTwoCard.
     * It creates and returns an instance of the BigTwoCard class.
     *
     * @param suit an int value between 0 and 3 representing the suit of a card:
     *             <p>
     *             0 = Diamond, 1 = Club, 2 = Heart, 3 = Spade
     * @param rank an int value between 0 and 12 representing the rank of a card:
     *             <p>
     *             0 = 'A', 1 = '2', 2 = '3', ..., 8 = '9', 9 = '0', 10 = 'J', 11 =
     *             'Q', 12 = 'K'
     */
    public BigTwoCard(int suit, int rank) {
        super(suit, rank);
    }

    /**
     * This method overrides the compareTo method of the card
     * according to the game rule of big two game.
     * if this card can beat the card being compared, then will return 1,
     * if two card are the same, return 0, otherwise -1.
     *
     * @param card the card to be compared
     * @return an integer to present which card is bigger
     */
    public int compareTo(Card card) {
        final ArrayList<String> cardValueMap = new ArrayList<String>(
                Arrays.asList( "3", "4", "5", "6", "7", "8", "9", "0", "J", "Q", "K", "A", "2")
        );
        final String[] RANKS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "0", "J", "Q", "K" };
        if (cardValueMap.indexOf(RANKS[this.rank]) > cardValueMap.indexOf(RANKS[card.rank])) {
            return 1;
        } else if (cardValueMap.indexOf(RANKS[this.rank]) < cardValueMap.indexOf(RANKS[card.rank])){
            return -1;
        } else if (this.suit > card.suit) {
            return 1;
        } else if (this.suit < card.suit) {
            return -1;
        } else {
            return 0;
        }
    }
}
