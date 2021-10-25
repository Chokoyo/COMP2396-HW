public class Triple extends Hand {
    public Triple(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    @Override
    public boolean isValid() {
        if (this.size() != 3) {
            return false;
        }
        if (this.getCard(0) == this.getCard(1) && this.getCard(2) == this.getCard(1)) {
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "Triple";
    }
}
