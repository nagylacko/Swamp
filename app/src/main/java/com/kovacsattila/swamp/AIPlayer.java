package com.kovacsattila.swamp;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kovács Attila on 2019-03-18.
 */

public class AIPlayer extends Player {

    private static int index = 0;
    private int myIndex;

    private TextView myText;

    public AIPlayer() {
        super();
        myIndex = index++;
    }

    @Override
    public void updateScreen(final PartyActivity partyActivity) {

        final ConstraintLayout partyConstLayout = partyActivity.findViewById(R.id.party_const_layout);

        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.add((TextView) partyActivity.findViewById(R.id.AI_hand_0));
        textViews.add((TextView) partyActivity.findViewById(R.id.AI_hand_1));
        textViews.add((TextView) partyActivity.findViewById(R.id.AI_hand_2));
        textViews.add((TextView) partyActivity.findViewById(R.id.AI_hand_3));
        textViews.add((TextView) partyActivity.findViewById(R.id.AI_hand_4));

        myText = textViews.get(myIndex);
        myText.setVisibility(View.VISIBLE);

//        Log.i("tag", Integer.toString((int) (partyConstLayout.getWidth() * (myIndex + 1) / (index + 1)) - (myText.getWidth() / 2)));
//        Log.i("tag", Integer.toString((int) (partyConstLayout.getWidth() * (myIndex + 1) / (index + 1))));
//        Log.i("tag", Integer.toString((int) (myText.getWidth() / 2)));
        Log.i("tag", Integer.toString((int) (partyConstLayout.getWidth() * (myIndex + (index == 1 ? 1 : 0) / (index + 1)) /*- (myText.getWidth() / 2)*/)));
        myText.setX((partyConstLayout.getWidth() * (myIndex + (index == 1 ? 1 : 0) / (index + 1)) /*- (myText.getWidth() / 2)*/));

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

        myText.setText("AI_player_" + myIndex + "\n" + "Here is the hand:" + "\n" + temp);
    }

    @Override
    public void hit() {
        if (Table.isFirst()) {
            Table.setNumber(numberOfSameRank(cards.get(0).getRank()));
            Table.setRank(cards.get(0).getRank());
            deleteCards(Table.getNumber(), Table.getRank());
        } else {
            int rank = getLowestSet(Table.getNumber(), Table.getRank());
            if(rank != -1){
                Table.setRank(rank);
            }
        }

        //hit
        String temp = "";
        myText.setText("AI_player_" + myIndex + "\n" + "Here is the hand:" + "\n" + temp);
    }

    private int getLowestSet(int number, int rank) {
        for (int i = rank + 1; i < 15; i++) {
            if(numberOfSameRank(i) == number){
                deleteCards(number, i);
                return i; //rank
            }
        }
        return -1; //pass
    }

    private int numberOfSameRank(int rank) {
        int counter = 0;
        for (Card card : cards) {
            if (card.getRank() == rank) {
                counter++;
            }
        }
        return counter;
    }

    private void deleteCards(int number, int rank) {
        int counter = 0;
        for (int i = 0; i < number; i++) {
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
