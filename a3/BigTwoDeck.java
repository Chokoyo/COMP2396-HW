import java.util.ArrayList;

public class BigTwoDeck extends Deck {
    /**
     * Initializes the deck of cards (called implicitly inside the constructor).
     */
    public void initialize() {
        removeAllCards();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new BigTwoCard(i, j);
                addCard(card);
            }
        }
    }
}
