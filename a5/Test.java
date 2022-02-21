public class Test {
    public static void main(String[] args) {
        CardGamePlayer player = new CardGamePlayer();
        CardList cardList = new CardList();
        cardList.addCard(new BigTwoCard(0,1));
        cardList.addCard(new BigTwoCard(1,1));
        cardList.addCard(new BigTwoCard(2,1));
        Hand triHand = BigTwo.composeHand(player, cardList);
        cardList.removeAllCards();
        cardList.addCard(new BigTwoCard(0,2));
        cardList.addCard(new BigTwoCard(1,2));
        cardList.addCard(new BigTwoCard(2,2));
        Hand triHand2 = BigTwo.composeHand(player, cardList);
        System.out.println(triHand.getType()+triHand.toString());
        System.out.println(triHand2.getType()+triHand2.toString());
        System.out.println(triHand.beats(triHand2));

        cardList.removeAllCards();
        cardList.addCard(new BigTwoCard(0,2));
        cardList.addCard(new BigTwoCard(1,3));
        cardList.addCard(new BigTwoCard(2,4));
        cardList.addCard(new BigTwoCard(2,5));
        cardList.addCard(new BigTwoCard(2,6));
        Hand strHand = BigTwo.composeHand(player, cardList);
        cardList.removeAllCards();
        cardList.addCard(new BigTwoCard(0,10));
        cardList.addCard(new BigTwoCard(1,11));
        cardList.addCard(new BigTwoCard(2,12));
        cardList.addCard(new BigTwoCard(2,0));
        cardList.addCard(new BigTwoCard(2,9));
        Hand strHand2 = BigTwo.composeHand(player, cardList);
        System.out.println(strHand.getType()+strHand);
        System.out.println(strHand2.getType()+strHand2);
        System.out.println(strHand2.beats(strHand));

        cardList.removeAllCards();
        cardList.addCard(new BigTwoCard(0,2));
        cardList.addCard(new BigTwoCard(0,3));
        cardList.addCard(new BigTwoCard(0,8));
        cardList.addCard(new BigTwoCard(0,5));
        cardList.addCard(new BigTwoCard(0,6));
        Hand fluHand = BigTwo.composeHand(player, cardList);
        cardList.removeAllCards();
        cardList.addCard(new BigTwoCard(0,8));
        cardList.addCard(new BigTwoCard(0,3));
        cardList.addCard(new BigTwoCard(0,4));
        cardList.addCard(new BigTwoCard(0,5));
        cardList.addCard(new BigTwoCard(0,6));
        Hand fluHand2 = BigTwo.composeHand(player, cardList);
        System.out.println(fluHand.getType()+fluHand);
        System.out.println(fluHand2.getType()+fluHand2);
        System.out.println(fluHand.beats(fluHand2));

        cardList.removeAllCards();
        cardList.addCard(new BigTwoCard(1,2));
        cardList.addCard(new BigTwoCard(1,3));
        cardList.addCard(new BigTwoCard(2,2));
        cardList.addCard(new BigTwoCard(0,2));
        cardList.addCard(new BigTwoCard(3,3));
        Hand fulHand = BigTwo.composeHand(player, cardList);
        cardList.removeAllCards();
        cardList.addCard(new BigTwoCard(2,8));
        cardList.addCard(new BigTwoCard(0,8));
        cardList.addCard(new BigTwoCard(1,1));
        cardList.addCard(new BigTwoCard(3,1));
        cardList.addCard(new BigTwoCard(0,8));
        Hand fulHand2 = BigTwo.composeHand(player, cardList);
        System.out.println(fulHand.getType()+fulHand);
        System.out.println(fulHand2.getType()+fulHand2);
        System.out.println(fulHand.beats(fulHand2));

        cardList.removeAllCards();
        cardList.addCard(new BigTwoCard(1,2));
        cardList.addCard(new BigTwoCard(1,3));
        cardList.addCard(new BigTwoCard(2,2));
        cardList.addCard(new BigTwoCard(0,2));
        cardList.addCard(new BigTwoCard(3,2));
        Hand quaHand = BigTwo.composeHand(player, cardList);
        cardList.removeAllCards();
        cardList.addCard(new BigTwoCard(2,8));
        cardList.addCard(new BigTwoCard(3,8));
        cardList.addCard(new BigTwoCard(1,8));
        cardList.addCard(new BigTwoCard(3,1));
        cardList.addCard(new BigTwoCard(0,8));
        Hand quaHand2 = BigTwo.composeHand(player, cardList);
        System.out.println(quaHand.getType()+quaHand);
        System.out.println(quaHand2.getType()+quaHand2);
        System.out.println(quaHand.beats(quaHand2));

        System.out.println(strHand.beats(fluHand2));
        System.out.println(fluHand.beats(strHand2));
    }
}
