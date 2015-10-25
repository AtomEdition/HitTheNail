package com.example.myapp.service;

import android.content.SharedPreferences;
import android.widget.TextView;

public abstract class StatisticsService {

    public static final String APP_PREF = "statistics";

    public static final String EASY_CLICK_STAT = "easyClickStatistics";
    public static final String MEDIUM_CLICK_STAT = "mediumClickStatistics";
    public static final String HARD_CLICK_STAT = "hardClickStatistics";

    public static final String TOTAL_PLAYED_STAT = "totalPlayedStat";
    public static final String GAME_WON_STAT = "gameWonStat";


    public static void setStat(SharedPreferences stat, SharedPreferences.Editor editor,
                               int clickCount, boolean winStatus, String gameDifficulty){
            if (winStatus){

                editor.putInt(GAME_WON_STAT, stat.getInt(GAME_WON_STAT, 0)+1);

                switch (gameDifficulty){

                    case "easy": editor.putInt(EASY_CLICK_STAT,
                            clickCount<stat.getInt(EASY_CLICK_STAT, 0)? clickCount: stat.getInt(EASY_CLICK_STAT, 0));
                        break;
                    case "medium": editor.putInt(MEDIUM_CLICK_STAT,
                            clickCount<stat.getInt(MEDIUM_CLICK_STAT, 0)? clickCount: stat.getInt(MEDIUM_CLICK_STAT, 0));
                        break;
                    case "hard": editor.putInt(HARD_CLICK_STAT,
                            clickCount<stat.getInt(HARD_CLICK_STAT, 0)? clickCount: stat.getInt(HARD_CLICK_STAT, 0));
                        break;
                }

            }

        editor.putInt(TOTAL_PLAYED_STAT, stat.getInt(TOTAL_PLAYED_STAT, 0)+1);

        editor.commit();
    }

    public static void loadStat(SharedPreferences preferences, TextView easy, TextView medium, TextView hard,
                                TextView wonGame, TextView totalPlayed){

        easy.setText(Integer.toString(preferences.getInt(EASY_CLICK_STAT, 0)));
        medium.setText(Integer.toString(preferences.getInt(MEDIUM_CLICK_STAT, 0)));
        hard.setText(Integer.toString(preferences.getInt(HARD_CLICK_STAT, 0)));

        wonGame.setText(Integer.toString(preferences.getInt(GAME_WON_STAT, 0)));
        totalPlayed.setText(Integer.toString(preferences.getInt(TOTAL_PLAYED_STAT, 0)));

    }


}