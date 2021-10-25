public class Pair extends Hand {
    public Pair(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    @Override
    public boolean isValid() {
        if (this.size() != 2) {
            return false;
        }
        if (this.getCard(0).getRank() == this.getCard(1).getRank()) {
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "Pair";
    }
}
