package com.AtomEdition.HitTheNail.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.AtomEdition.HitTheNail.R;
import com.AtomEdition.HitTheNail.service.StatisticsService;

public class StatisticsActivity extends Activity{

    private SharedPreferences preferences;
    private StatisticsService statisticsService = StatisticsService.getInstance();


    private TextView easyClickStat;
    private TextView mediumClickStat;
    private TextView hardClickStat;

    private TextView gameWonStat;
    private TextView totalPlayedStat;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        preferences = getSharedPreferences(StatisticsService.APP_PREF, Context.MODE_PRIVATE);

        easyClickStat = (TextView)findViewById(R.id.easyClickStat);
        mediumClickStat = (TextView)findViewById(R.id.mediumClickStat);
        hardClickStat = (TextView)findViewById(R.id.hardClickStat);

        gameWonStat = (TextView)findViewById(R.id.gameWonStat);
        totalPlayedStat = (TextView)findViewById(R.id.totalPlayedStat);

        statisticsService.loadStat(preferences, easyClickStat, mediumClickStat, hardClickStat,
                                    gameWonStat, totalPlayedStat);

    }


    public void onBackStatClick (View view){
        onBackPressed();
    }

}
