package com.example.myapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.myapp.R;

public class HowToPlayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howtoplay);
    }

    public void toStartFromHowToPlayClick(View view){
        onBackPressed();
    }
}
