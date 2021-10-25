public class Triple extends Hand {
    public Triple(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    @Override
    public boolean isValid() {
        if (this.size() != 3) {
            return false;
        }
        if (this.getCard(0).getRank() == this.getCard(1).getRank() &&
                this.getCard(2).getRank() == this.getCard(1).getRank()) {
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "Triple";
    }
}
