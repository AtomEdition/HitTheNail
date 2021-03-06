package com.AtomEdition.HitTheNail.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.AtomEdition.HitTheNail.R;
import com.AtomEdition.HitTheNail.service.AdService;
import com.AtomEdition.HitTheNail.service.promotion.PromotionButtonController;
import com.AtomEdition.HitTheNail.service.promotion.PromotionService;

public class StartActivity extends Activity {

    private PromotionService promotionService = PromotionService.getInstance();
    private SharedPreferences otherAppsScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startmenu);
        init();
    }

    private void init(){

        setAd();
        otherAppsScreen = getSharedPreferences(PromotionService.OTHER_APPS_SCREEN, Context.MODE_PRIVATE);
    }

    private void setAd(){

        AdService adService = AdService.getInstance();
        adService.setAd(this);
        adService.showBanner(this);
        adService.displayInterstitial();
        PromotionButtonController.getInstance(this).startTimer();
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

        if (promotionService.getPromotionState(otherAppsScreen)) {
            openQuitDialog();
        }

        else {
            Intent intent = new Intent(this, ExitScreenActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void openQuitDialog (){

        AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
        quitDialog.setTitle(R.string.QuitDialog);

        quitDialog.setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
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

    public void onPromotionClick(View view) {

        PromotionButtonController promotionButtonController = PromotionButtonController.getInstance(this);
        promotionButtonController.makeUsFamous();
    }
}
