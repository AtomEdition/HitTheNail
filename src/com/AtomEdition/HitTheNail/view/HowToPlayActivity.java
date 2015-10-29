package com.AtomEdition.HitTheNail.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.AtomEdition.HitTheNail.R;

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
