package com.example.myapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.myapp.R;
import com.example.myapp.service.StatisticsService;

public class StatisticsActivity extends Activity{

    SharedPreferences preferences;

    TextView easyClickStat;
    TextView mediumClickStat;
    TextView hardClickStat;

    TextView gameWonStat;
    TextView totalPlayedStat;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        preferences = getSharedPreferences(StatisticsService.APP_PREF, Context.MODE_PRIVATE);

        easyClickStat = (TextView)findViewById(R.id.easyClickStat);
        mediumClickStat = (TextView)findViewById(R.id.mediumClickStat);
        hardClickStat = (TextView)findViewById(R.id.hardClickStat);

        gameWonStat = (TextView)findViewById(R.id.gameWonStat);
        totalPlayedStat = (TextView)findViewById(R.id.totalPlayedStat);

        StatisticsService.loadStat(preferences, easyClickStat, mediumClickStat, hardClickStat,
                                    gameWonStat, totalPlayedStat);

    }


    public void onBackStatClick (View view){
        onBackPressed();
    }

}
