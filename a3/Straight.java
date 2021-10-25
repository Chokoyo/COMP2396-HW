import java.util.ArrayList;
import java.util.Arrays;

public class Straight extends Hand {

    public Straight(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    @Override
    public boolean isValid() {
        if (this.size() != 5) {
            return false;
        }
        this.sort();
        final String cardsStr = "34567890JQKA2";
        String handStr = "";
        for (int i = 0; i < 5; i++) {
            handStr += this.getCard(i).toString();
        }
        if (cardsStr.contains(handStr)) {
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "Straight";
    }
}
