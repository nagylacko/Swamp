package com.kovacsattila.swamp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kovács Attila on 2019-03-21.
 */

public class User extends Player {

    public User() {
        super();
    }

    @Override
    public void updateScreen(final PartyActivity partyActivity) {

        LinearLayout player_hand = partyActivity.findViewById(R.id.player_hand);

        int screenWidth = player_hand.getWidth();

        ArrayList<Button> buttons = new ArrayList<>();

        for (int i = 0; i < hand.getCards().size(); i++) {
            buttons.add(new Button(partyActivity));

        }


//        LinearLayout player_hand = partyActivity.findViewById(R.id.player_hand);
//
//        Button button_card_1 = new Button(partyActivity);
//        button_card_1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        button_card_1.setText("Card1");
//        //button_card_1.setId(button_card_1);
//        button_card_1.setX(300);
//        button_card_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(partyActivity.getApplicationContext(), "Click!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        player_hand.addView(button_card_1);
    }
}
