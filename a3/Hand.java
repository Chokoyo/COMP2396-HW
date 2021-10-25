import java.util.ArrayList;
import java.util.Arrays;

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

    public boolean beats (Hand hand) {
        if (this.getType().equals(hand.getType())) {
            return (this.getTopCard().compareTo(hand.getTopCard()) > 0)? true : false;
        }
        
        final ArrayList<String> handTypes = new ArrayList<String>(
                Arrays.asList( "Stright", "Flush", "FullHouse", "Quad", "StraightFlush")
        );
        
        if (!handTypes.contains(this.getType())) {
            return false;
        } else if (handTypes.indexOf(this.getType()) > handTypes.indexOf(hand.getType())) {
            return true;
        }
        return false;
    }

    public abstract boolean isValid();
    public abstract String getType();
}
