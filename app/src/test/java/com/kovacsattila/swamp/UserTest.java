package com.kovacsattila.swamp;

import org.junit.Test;

import java.util.ArrayList;

public class UserTest {

    public User initUser() {
        User user = new User(0);

        user.addCard(new Card(2));
        user.addCard(new Card(2));
        user.addCard(new Card(3));
        user.addCard(new Card(3));
        user.addCard(new Card(3));
        user.addCard(new Card(4));
        user.addCard(new Card(4));
        user.addCard(new Card(4));
        user.addCard(new Card(4));
        user.addCard(new Card(5));
        user.addCard(new Card(5));
        user.addCard(new Card(5));
        user.addCard(new Card(5));
        user.addCard(new Card(5));
        user.addCard(new Card(6));
        user.addCard(new Card(6));

        return user;
    }

    @Test
    public void setCardPlayable() {
        User user = initUser();

        int rank = 2;
        int number = 1;

        Table.init();
        Table.setRank(rank);
        Table.setNumber(number);

        user.setCardPlayable();

        System.out.println("Cards:");
        for (int i = 0; i < user.getCards().size(); i++) {
            System.out.print(Integer.toString(user.getCards().get(i).getRank()) + ", ");
        }
        System.out.println("\nRank is " + Integer.toString(rank) + " Number is " + Integer.toString(number));
        System.out.println("Playable?:");
        for (int i = 0; i < user.getCards().size(); i++) {
            System.out.print((user.getCards().get(i).getPlayable() ? "t" : "f") + ", ");
        }
    }

    @Test
    public void getCardGroup() {
        User user = initUser();

        System.out.println("Cards:");
        for (int i = 0; i < user.getCards().size(); i++) {
            System.out.print(Integer.toString(user.getCards().get(i).getRank()) + ", ");
        }
        System.out.println("\nIndexes:");
        for (int i = 0; i < user.getCards().size(); i++) {
            System.out.print(Integer.toString(i) + ", ");
        }

        int rank = 4;
        ArrayList<Integer> cardGroup = user.getCardGroup(rank);
        System.out.println("\nIndexes of group by rank: " + Integer.toString(rank));
        for (int i = 0; i < cardGroup.size(); i++) {
            System.out.print(Integer.toString(cardGroup.get(i)) + ", ");
        }
    }

    @Test
    public void test() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(34);
        list.add(35);
        list.add(36);
        list.add(37);

        list.remove(2);
    }
}