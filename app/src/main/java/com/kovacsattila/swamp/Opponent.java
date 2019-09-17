package com.kovacsattila.swamp;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Opponent extends Player {

    private TextView myText;

    public Opponent(int role) {
        super(role);
    }

    @Override
    public void initScreen(final PartyActivity partyActivity, final int noOfPlayers) {
        super.initScreen(partyActivity, noOfPlayers);

        final ConstraintLayout partyConstLayout = partyActivity.findViewById(R.id.party_const_layout);

        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.add((TextView) partyActivity.findViewById(R.id.opp_hand_0));
        textViews.add((TextView) partyActivity.findViewById(R.id.opp_hand_1));
        textViews.add((TextView) partyActivity.findViewById(R.id.opp_hand_2));
        textViews.add((TextView) partyActivity.findViewById(R.id.opp_hand_3));
        textViews.add((TextView) partyActivity.findViewById(R.id.opp_hand_4));
        textViews.add((TextView) partyActivity.findViewById(R.id.opp_hand_5));

        myText = textViews.get(ID - 1);
        myText.setVisibility(View.VISIBLE);

        //for derailed description see \Swamp\app\src\main\res\drawable\partyscreen_layout_2.png

        int leftOffset = (int) (partyConstLayout.getWidth() * 0.05);
        int rightOffset = (int) (partyConstLayout.getWidth() * 0.1);
        int scaledWidth = partyConstLayout.getWidth() - leftOffset - rightOffset;

        ArrayList<Float> positions = new ArrayList<>();
        switch (noOfPlayers - 1) {
            case 1: positions.addAll(Arrays.asList(0.5f));
            case 2: positions.addAll(Arrays.asList(0.0f, 1.0f));
            case 3: positions.addAll(Arrays.asList(0.0f, 0.5f, 1.0f));
            case 4: positions.addAll(Arrays.asList(0.0f, 0.33f, 0.66f, 1.0f));
            default: positions.addAll(Arrays.asList(0.0f, 0.25f, 0.5f, 0.75f, 1.0f));
        }

        myText.setX(leftOffset + (positions.get(ID - 1) * scaledWidth));

        //temporary solution
        //myText.setX((ID - 1) * 400);

        //list the cards
        String temp = "";
        int i = 0;
        for (Card card : cards) {
            temp += Integer.toString(card.getRank()) + " ";
            if (i++ == 6) {
                temp += "\n";
                i = 0;
            }
        }
        myText.setText("Opponent_" + ID + "\nHere is the hand:\n" + temp);
    }

    @Override
    public void hit() {
        String hitStatus;
        if (Table.isFirst) {
            Table.setNumber(numberOfSameRank(cards.get(0).getRank()));
            Table.setRank(cards.get(0).getRank());
            deleteCards(Table.getNumber(), Table.getRank());
            Table.didLastPlayerHit = true;
            hitStatus = "Hits with " + Integer.toString(Table.getNumber()) + " pieces \nof " +
                    Integer.toString(Table.getRank());
        } else {
            int rank = getLowestGroup(Table.getNumber(), Table.getRank());
            if(rank != -1){
                Table.setRank(rank);
                Table.didLastPlayerHit = true;
                hitStatus = "Hits with " + Integer.toString(Table.getNumber()) + " pieces \nof " +
                        Integer.toString(Table.getRank());
            } else {
                hitStatus = "Passed";
            }
        }

        myText.setText("Opponent_" + ID + "\nhas" + Integer.toString(cards.size()) +
                " cards left\n" + hitStatus);

        /**
         * Log
         **/
        Log.d("GAME", "Opponent_" + Integer.toString(ID) + " tries to hit");
        Log.d("GAME", "Opponent_" + Integer.toString(ID) + " has the following cards:");
        String log = "";
        for (Card c : cards) {
            log += Integer.toString(c.getRank()) + ",";
        }
        Log.d("GAME", log);
    }

    public int getLowestGroup(int number, int rank) {
        for (int i = rank + 1; i < 15; i++) {
            if(numberOfSameRank(i) == number){
                deleteCards(number, i);
                return i; //rank
            }
        }
        return -1; //pass
    }

    public int numberOfSameRank(int rank) {
        int counter = 0;
        for (Card card : cards) {
            if (card.getRank() == rank) {
                counter++;
            }
        }
        return counter;
    }

    public void deleteCards(int number, int rank) {
        int counter = 0;
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i).getRank() == rank){
                cards.remove(i);
                counter++;
                i--;
                if(counter == number){
                    break;
                }
            }
        }
    }
}
