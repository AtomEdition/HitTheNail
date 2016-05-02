package com.AtomEdition.HitTheNail.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.AtomEdition.HitTheNail.R;

public class LevelChoiceActivity extends Activity {

    static final String LEVEL = "level";
    static final String[] EASY = {"easy", "3"};
    static final String[] MEDIUM = {"medium", "4"};
    static final String[] HARD = {"hard", "5"};

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelchoice);
    }


    public void onLevelChoiceClick(View view){
        Intent intent = new Intent(LevelChoiceActivity.this, GameActivity.class);

            switch (view.getId()){
                case R.id.easy:
                    intent.putExtra(LEVEL, EASY);
                    break;
                case R.id.medium:
                    intent.putExtra(LEVEL, MEDIUM);
                    break;
                case R.id.hard:
                    intent.putExtra(LEVEL, HARD);
                    break;
                default: break;
            }
        startActivity(intent);
    }

    public void onLevelBackClick(View view){
        onBackPressed();
    }
}
