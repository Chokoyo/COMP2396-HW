public class Flush extends Hand {
    public Flush(CardGamePlayer player, CardList card) {
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
        return true;
    }

    @Override
    public String getType() {
        return "Flush";
    }
}
