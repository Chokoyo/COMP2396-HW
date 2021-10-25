public class Quad extends Hand {
    private int topCardRank;

    public Quad(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    @Override
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

    @Override
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

    @Override
    public String getType() {
        return "Quad";
    }
}
