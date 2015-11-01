package com.AtomEdition.HitTheNail.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.AtomEdition.HitTheNail.R;
import com.AtomEdition.HitTheNail.service.AdService;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AdService.getInstance(this).displayInterstitial();
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
        quitDialog.setTitle("Are you really want to quit?");

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
}
