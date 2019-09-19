package com.kovacsattila.swamp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Opponent extends Player {

    private PartyActivity partyActivity;

    private ConstraintLayout partyConstLayout;

    private TextView myText;

    private ArrayList<Card> thrownCards = new ArrayList<>();

    public Opponent(int role) {
        super(role);
    }

    @Override
    public void initScreen(final PartyActivity partyActivity, final int noOfPlayers) {
        super.initScreen(partyActivity, noOfPlayers);

        this.partyActivity = partyActivity;

        partyConstLayout = partyActivity.findViewById(R.id.party_const_layout);

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

        int leftOffset = (int) (partyConstLayout.getWidth() * 0.025);
        int rightOffset = (int) (partyConstLayout.getWidth() * 0.2);
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
        myText.setText("Opponent_" + ID /*+ "\nHere is the hand:\n" + temp*/);
    }

    @Override
    public void hit() {
        String hitStatus;
        if (Table.isFirst) {
            Table.setNumber(numberOfSameRank(cards.get(0).getRank()));
            Table.setRank(cards.get(0).getRank());
            deleteCards(Table.getNumber(), Table.getRank());
            Table.didLastPlayerHit = true;
            hitStatus = "is the first in the round\n" + "Hits " +
                    Integer.toString(Table.getNumber()) + " pcs of " +
                    Integer.toString(Table.getRank());
            throwCards(Table.getNumber(), Table.getRank());
        } else {
            int rank = getLowestGroup(Table.getNumber(), Table.getRank());
            if(rank != -1){
                Table.setRank(rank);
                Table.didLastPlayerHit = true;
                hitStatus = "Hits " + Integer.toString(Table.getNumber()) + " pcs of " +
                        Integer.toString(Table.getRank());
                throwCards(Table.getNumber(), Table.getRank());
            } else {
                hitStatus = "Passed";
            }
        }

        final String finalHitStatus = hitStatus;

        partyActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myText.setText("Opponent_" + ID + "\n" + Integer.toString(cards.size()) +
                        " cards left\n" + finalHitStatus);

            }
        });

        /**
         * Log
         **/
        Log.d("GAME", "Opponent_" + Integer.toString(ID) + hitStatus);
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

    public void throwCards(int number, int rank) {

        thrownCards = new ArrayList<>();

        double originalCardWidth = 691; //original size of card images
        double originalCardHeight = 1056; //original size of card images
        double leftOffset = 0.02 * partyConstLayout.getWidth();
        double cardHeight = partyConstLayout.getHeight() * 0.28; //displayed card image size
        double cardWidth = cardHeight * originalCardWidth / originalCardHeight; //displayed card image size

        for(int i = 0; i < number; i++) {
            Card card = new Card(rank);

            final ImageView cardImage = new ImageView(partyActivity);
            Bitmap bitmap = card.getBitmap(partyActivity);
            Drawable drawable = new BitmapDrawable(partyActivity.getResources(), Bitmap.createScaledBitmap(bitmap, (int) cardWidth, (int) cardHeight, true));
            cardImage.setImageDrawable(drawable);

            cardImage.setX(myText.getX() + (int) (i * leftOffset));
            cardImage.setY(myText.getY() + 250);

            partyActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    partyConstLayout.addView(cardImage);
                }
            });

            card.setImage(cardImage);

            thrownCards.add(card);
        }
    }

    @Override
    public void clearTable () {
        partyActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (Card c : thrownCards) {
                    c.getImage().setVisibility(View.GONE);
                    myText.setText("Opponent_" + ID);
                }
                thrownCards.clear();
            }
        });
    }
}
