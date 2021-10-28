/**
 * This class is a subclass of the Hand class. It models the quad
 * hand type in the BigTwo game, and also checks whether a hand is single.
 *
 * @author Zhuangcheng Gu
 */
public class Quad extends Hand {
    private int topCardRank; // the rank of the top card

    /**
     * Create an instance of the Quad card by calling the superclass
     * constructor.
     *
     * @param player of the hand
     * @param card list of the hand
     */
    public Quad(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    /**
     * Return whether the current hand is a valid quad hand.
     *
     * @return whether the current hand is a valid quad hand
     */
    public boolean isValid() {
        if (this.size() != 5) {
            return false;
        }
        int rankA = -1;
        int rankB = -1;
        int countA = 0;
        int countB = 0;
        for (int i = 0; i < 5; i++) {
            int thisRank = this.getCard(i).getRank();
            if (rankA == -1) {
                rankA = thisRank;
                countA++;
            } else if (rankB == -1) {
                rankB = thisRank;
                countB++;
            } else if (thisRank != rankA && thisRank != rankB) {
                return false;
            } else if (thisRank == rankA) {
                countA++;
            } else {
                countB++;
            }
        }
        if (countA == 1 && countB == 4) {
            topCardRank = rankB;
            return true;
        } else if (countA == 4 && countB == 1) {
            topCardRank = rankA;
            return true;
        }
        return false;
    }

    /**
     * Return the topCard of the hand according to the big two game rule.
     *
     * @return topCard of the hand according to the big two game rule
     */
    public Card getTopCard() {
        this.isValid();
        Card topCard = new Card(0,3);
        for (int i = 0; i < 5; i++) {
            if (this.getCard(i).getRank() == topCardRank) {
                topCard = (topCard.getSuit() > this.getCard(i).getSuit())? topCard : this.getCard(i);
            }
        }
        return topCard;
    }

    /**
     * Return the type of current hand.
     *
     * @return the type of current hand "Quad"
     */
    public String getType() {
        return "Quad";
    }
}
