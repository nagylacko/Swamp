package com.kovacsattila.swamp;

import android.widget.TextView;

/**
 * Created by Kovács Attila on 2019-03-18.
 */

public class AIPlayer extends Player{

    private static int index = 0;
    private int myIndex;

    private TextView myText;

    public AIPlayer() {
        super();
        myIndex = index++;
    }

    @Override
    public void updateScreen(final PartyActivity partyActivity) {

        int ID;
        if(index == 1) {
            ID = R.id.AI_hand_2;
        } else if(index == 2) {
            switch (myIndex) {
                case 0: ID = R.id.AI_hand_1; break;
                case 1: ID = R.id.AI_hand_3; break;
                default: ID = 0;
            }
        } else if(index == 3) {
            switch (myIndex) {
                case 0: ID = R.id.AI_hand_0; break;
                case 1: ID = R.id.AI_hand_2; break;
                case 2: ID = R.id.AI_hand_4; break;
                default: ID = 0;
            }
        } else if(index == 4) {
            switch (myIndex) {
                case 0: ID = R.id.AI_hand_0; break;
                case 1: ID = R.id.AI_hand_1; break;
                case 2: ID = R.id.AI_hand_3; break;
                case 3: ID = R.id.AI_hand_4; break;
                default: ID = 0;
            }
        } else if(index == 5) {
            switch (myIndex) {
                case 0: ID = R.id.AI_hand_0; break;
                case 1: ID = R.id.AI_hand_1; break;
                case 2: ID = R.id.AI_hand_2; break;
                case 3: ID = R.id.AI_hand_3; break;
                case 4: ID = R.id.AI_hand_4; break;
                default: ID = 0;
            }
        } else {
            ID = 0;
        }
        myText = partyActivity.findViewById(ID);

        myText.setText("AI_player_" + myIndex + "\n" + "Here is the hand ");

        //asdasd
        
    }
}
