package com.kovacsattila.swamp;

import org.junit.Test;

import static org.junit.Assert.*;

public class OpponentTest {

    public Opponent initOpponent() {
        Opponent opponent = new Opponent(0);

        opponent.addCard(new Card(2));
        opponent.addCard(new Card(2));
        opponent.addCard(new Card(3));
        opponent.addCard(new Card(3));
        opponent.addCard(new Card(3));
        opponent.addCard(new Card(4));
        opponent.addCard(new Card(4));
        opponent.addCard(new Card(4));
        opponent.addCard(new Card(4));
        opponent.addCard(new Card(5));
        opponent.addCard(new Card(5));
        opponent.addCard(new Card(5));
        opponent.addCard(new Card(5));
        opponent.addCard(new Card(5));
        opponent.addCard(new Card(6));
        opponent.addCard(new Card(6));

        return opponent;
    }

    @Test
    public void getLowestGroup() {
        Opponent opponent = initOpponent();

        System.out.println("Cards in hand:");
        for (int i = 0; i < opponent.getCards().size(); i++) {
            System.out.print(Integer.toString(opponent.getCards().get(i).getRank()) + ",");
        }

        int number = 4;
        int rank = 3;

        System.out.println("\nCurrently there are " + Integer.toString(number) + " cards of rank " +
                Integer.toString(rank) + " on the table");
        System.out.println("Opponent hits it with rank " +
                Integer.toString(opponent.getLowestGroup(number, rank)));
        System.out.println("Cards left in hand:");
        for (int i = 0; i < opponent.getCards().size(); i++) {
            System.out.print(Integer.toString(opponent.getCards().get(i).getRank()) + ",");
        }
    }

    @Test
    public void numberOfSameRank() {
        Opponent opponent = initOpponent();

        System.out.println("Cards in hand:");
        for (int i = 0; i < opponent.getCards().size(); i++) {
            System.out.print(Integer.toString(opponent.getCards().get(i).getRank()) + ",");
        }

        int rank = 6;

        System.out.println("\nThere are " + Integer.toString(opponent.numberOfSameRank(rank))
                + " cards of rank " + Integer.toString(rank));
    }

    @Test
    public void deleteCards() {
        Opponent opponent = initOpponent();

        System.out.println("Cards originally in hand:");
        for (int i = 0; i < opponent.getCards().size(); i++) {
            System.out.print(Integer.toString(opponent.getCards().get(i).getRank()) + ",");
        }

        int number = 4;
        int rank = 5;

        opponent.deleteCards(number, rank);

        System.out.println("\n" + Integer.toString(number) + " cards of rank " +
                Integer.toString(rank) + " will be deleted");

        System.out.println("\nCards left in hand:");
        for (int i = 0; i < opponent.getCards().size(); i++) {
            System.out.print(Integer.toString(opponent.getCards().get(i).getRank()) + ",");
        }
    }
}