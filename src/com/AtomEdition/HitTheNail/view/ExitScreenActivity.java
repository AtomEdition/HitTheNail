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
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
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
    private PromotionService promotionService = PromotionService.getInstance();

    private TableLayout tableLayout;
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

        tableLayout = (TableLayout)findViewById(R.id.appsTable);
        checkBox = (CheckBox)findViewById(R.id.MessageCheckBox);

        editor = getSharedPreferences(PromotionService.OTHER_APPS_SCREEN, Context.MODE_PRIVATE).edit();
        editor.commit();
    }

    private void createImageTable () {

        images = new ArrayList<>();
        double screenPercent = 0.6;
        TableLayout tableLayout = (TableLayout)findViewById(R.id.appsTable);

        images.add(getResources().getDrawable(R.drawable.promotion_fortune_cookies));
        images.add(getResources().getDrawable(R.drawable.promotion_cat_clicker));
        images.add(getResources().getDrawable(R.drawable.promotion_mommy_balls));
        images.add(getResources().getDrawable(R.drawable.promotion_adopt));

        float ratio = (float)images.get(0).getMinimumWidth()/images.get(0).getMinimumHeight();
        int bannerHeight = (int)(getResources().getDisplayMetrics().heightPixels*screenPercent)/images.size();
        int bannerWidth = (int)(ratio*bannerHeight);

        for (Drawable image : images) {

            TableRow tableRow = new TableRow(this);
            ImageView banner = new ImageView(this);
            banner.setBackgroundDrawable(image);
            banner.setOnClickListener(this);
            banner.setId(counter);
            counter++;
            tableRow.addView(banner, bannerWidth, bannerHeight);
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
        promotionService.setPromotionState(editor, checkBox.isChecked());
        finish();
    }

    public void closeClick(View view) {
        onBackPressed();
    }


}
