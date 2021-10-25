import java.util.ArrayList;
import java.util.Arrays;

public class BigTwoCard extends Card {

    /**
     * Creates and returns an instance of the BigTwoCard class.
     *
     * @param suit an int value between 0 and 3 representing the suit of a card:
     *             <p>
     *             0 = Diamond, 1 = Club, 2 = Heart, 3 = Spade
     * @param rank an int value between 0 and 12 representing the rank of a card:
     *             <p>
     *             0 = 'A', 1 = '2', 2 = '3', ..., 8 = '9', 9 = '0', 10 = 'J', 11 =
     */
    public BigTwoCard(int suit, int rank) {
        super(suit, rank);
    }

    @Override
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
