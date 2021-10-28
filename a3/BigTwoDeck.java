/**
 * This is a subclass of the Deck. It overrides the initialize method
 * by add BigTwoCard into the Deck.
 *
 * @author Zhuangcheng Gu
 */
public class BigTwoDeck extends Deck {

    /**
     * Initializes the deck of cards (called implicitly inside the constructor)
     * by adding BigTwoCard to the deck in order.
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
