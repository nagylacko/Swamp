package com.kovacsattila.swamp;

import java.util.ArrayList;

/**
 * Created by Kovács Attila on 2019-03-18.
 */

public class Hand {

    private ArrayList<Card> cards /*= new ArrayList<Card>()*/;

    public Hand() {
        cards = new ArrayList<>();
    }

    //erre csinalni unit testet
    //most nem tudom, hogy itt kell-e copy-zni, vagy csak oda lehet adni??
    public Hand(ArrayList<Card> cards) {
        //this.cards = cards;
        for(Card card : cards){
            this.cards.add(card);
        }
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public void sortCards() {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < cards.size() - 1; i++) {
                if (cards.get(i).getNumber() > cards.get(i + 1).getNumber()) {
                    Card temp = new Card(cards.get(i));
                    cards.set(i, cards.get(i + 1));
                    cards.set(i + 1, temp);
                    flag = true;
                }
            }
        }
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void hit(int index) {

    }

    public void hit(int index1, int index2) {

    }
}
