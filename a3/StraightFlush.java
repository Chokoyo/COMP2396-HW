public class StraightFlush extends Hand {
    public StraightFlush(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    @Override
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
        this.sort();
        final String cardsStr = "34567890JQKA2";
        final String[] RANKS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "0", "J", "Q", "K" };
        String handStr = "";
        for (int i = 0; i < 5; i++) {
            handStr += RANKS[this.getCard(i).getRank()];
        }
        if (cardsStr.contains(handStr)) {
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "StraightFlush";
    }
}
