public abstract class Hand extends CardList{
    public CardGamePlayer player;

    public Hand(CardGamePlayer player, CardList card) {
        this.player = player;
        for (int i = 0; i < card.size(); i++) {
             this.addCard(card.getCard(i));
        }
    }

    public CardGamePlayer getPlayer() {
        return player;
    }

    public Card getTopCard() {
        this.sort();
        return getCard(this.size() - 1);
    }

    public abstract boolean isValid();
    public abstract String getType();
}
