package com.example.myapp.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.myapp.R;

public class LevelChoiceActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelchoice);
    }


    public void onLevelChoiceClick(View view){
        Intent intent = new Intent(LevelChoiceActivity.this, GameActivity.class);

            switch (view.getId()){

                case R.id.easy:  intent.putExtra("level", "easy");
                    break;
                case R.id.medium:  intent.putExtra("level", "medium");
                    break;
                case R.id.hard:  intent.putExtra("level", "hard");
                    break;

                default: break;
            }

        startActivity(intent);
    }

    public void onLevelBackClick(View view){
        onBackPressed();
    }
}
