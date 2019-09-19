package com.kovacsattila.swamp;

import android.app.ActionBar;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainThread extends Thread {

    private PartyActivity partyActivity;
    public static final AtomicBoolean wait4User = new AtomicBoolean(false);
    public static final AtomicBoolean stopThread = new AtomicBoolean(false);

    MainThread(PartyActivity partyActivity) {
        super();
        this.partyActivity = partyActivity;
    }

    @Override
    public void run() {

        Table.init();
        int hitCounter = 0;
        int currentPlayer = 0;
        int lastPlayerWhoHit = 0;

        while (!stopThread.get()) {

            hitCounter++;
            if (PlayersList.get(currentPlayer).haveCards()) {
                PlayersList.get(currentPlayer).hit();
                Table.isFirst = false;
                if (PlayersList.get(currentPlayer).getClass().toString().equals("class com.kovacsattila.swamp.User")) {
                    wait4User.set(true);
                    //wait for User onClick event
                    while (wait4User.get()) {
                        try {
                            sleep(20);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            if (Table.didLastPlayerHit) {
                lastPlayerWhoHit = currentPlayer;
                Table.didLastPlayerHit = false;
            }

            currentPlayer = (currentPlayer + 1) % PlayersList.size();

            try {
                sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (hitCounter == PlayersList.size()) {
                //end of round
                PlayersList.clearTable();
                currentPlayer = lastPlayerWhoHit;
                hitCounter = 0;
                Table.isFirst = true;
                Log.d("GAME", "----- End of Round -----");
            }
        }
    }
}
