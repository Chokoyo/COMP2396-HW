public class Single extends Hand {
    public Single(CardGamePlayer player, CardList card) {
        super(player, card);
    }

    @Override
    public boolean isValid() {
//        System.out.println(this.getCard(0));
        if (this.size() == 1) {
//            System.out.println("size is 1");
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "Single";
    }
}
