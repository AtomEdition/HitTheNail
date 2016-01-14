package com.AtomEdition.HitTheNail.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.AtomEdition.HitTheNail.R;
import com.AtomEdition.HitTheNail.service.PromotionService;
import com.AtomEdition.HitTheNail.service.StatisticsService;

import java.util.ArrayList;

/**
 * Created by Ì on 12.01.2016.
 */
public class ExitScreenActivity extends Activity implements View.OnClickListener {

    private SharedPreferences.Editor editor;

    private CheckBox checkBox;
    private ArrayList<Drawable> images;
    private int counter = 0;
    private static final String url[] = {
            "https://play.google.com/store/apps/details?id=com.atomEdition.FortuneCookies",
            "https://play.google.com/store/apps/details?id=com.AtomEdition.CatClicker",
            "https://play.google.com/store/apps/details?id=com.AtomEdition.MommyBalls",
            "https://play.google.com/store/apps/details?id=com.atomEdition.mexicanAdopt"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exitscreen_layout);
        createImageTable();
        checkBox = (CheckBox)findViewById(R.id.MessageCheckBox);

        editor = getSharedPreferences(PromotionService.OTHER_APPS_SCREEN, Context.MODE_PRIVATE).edit();
        editor.commit();
    }

    private void createImageTable () {

        images = new ArrayList<Drawable>();

        TableLayout tableLayout = (TableLayout)findViewById(R.id.appsTable);

        images.add(getResources().getDrawable(R.drawable.exit_screen_fortune_cookies));
        images.add(getResources().getDrawable(R.drawable.exit_screen_cat_clicker));
        images.add(getResources().getDrawable(R.drawable.exit_screen_mommy_balls));
        images.add(getResources().getDrawable(R.drawable.exit_screen_adopt));


        for (Drawable image : images) {

            TableRow tableRow = new TableRow(this);
            ImageButton button = new ImageButton(this);
            button.setBackgroundDrawable(image);
            button.setOnClickListener(this);
            button.setId(counter);
            counter++;
            tableRow.addView(button);
            tableLayout.addView(tableRow);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url[view.getId()]));
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), R.string.connection_failure,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        PromotionService.setPromotionState(editor, checkBox.isChecked());
        finish();
    }

    public void closeClick(View view) {
        onBackPressed();
    }


}
