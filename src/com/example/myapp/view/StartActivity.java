package com.example.myapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.myapp.R;
import com.example.myapp.service.StatisticsService;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!StatisticsService.isAdWatched) {
            setAd();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startmenu);
    }

    public void onMainNewGameClick (View view){
        Intent intent = new Intent(StartActivity.this, LevelChoiceActivity.class);
        startActivity(intent);
    }

    public void onStatisticsClick (View view){
        Intent intent = new Intent(StartActivity.this, StatisticsActivity.class);
        startActivity(intent);
    }

    public void onHowToPlayClick (View view){
        Intent intent = new Intent(StartActivity.this, HowToPlayActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openQuitDialog();
    }

    private void openQuitDialog (){
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle("You really want to quit?");

        quitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        quitDialog.show();
    }

    public void onClickOther(View view) {
        Intent intent = new Intent(this, FollowActivity.class);
        startActivity(intent);
        finish();
    }

    private InterstitialAd interstitialAd;

    public void setAd() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-9550981282535152/8512948227");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayInterstitial();
            }
        });
    }

    public void displayInterstitial() {
        if (!StatisticsService.isAdWatched) {
            interstitialAd.show();
            StatisticsService.isAdWatched = true;
        }
    }
}
