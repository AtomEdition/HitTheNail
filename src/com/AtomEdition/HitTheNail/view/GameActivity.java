package com.AtomEdition.HitTheNail.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.AtomEdition.HitTheNail.R;
import com.AtomEdition.HitTheNail.service.AdService;
import com.AtomEdition.HitTheNail.service.NailFieldService;
import com.AtomEdition.HitTheNail.service.StatisticsService;

//todo: добавить локализацию

public class GameActivity extends Activity implements View.OnClickListener {

    private ImageButton[][] imageButtons;

    private NailFieldService nailFieldService = NailFieldService.getInstance();

    private MediaPlayer player;
    private ToggleButton sound;
    private static boolean soundState = false;

    private int height;
    private int width;

    private TextView gameText;
    private int clickCounter = 0;

    private boolean winStatus = false;
    private String gameDifficulty;

    private SharedPreferences statistics;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        player = MediaPlayer.create(this, R.raw.hit);
        player.setVolume(0, 0);
        sound = (ToggleButton) findViewById(R.id.soundSwitcher);
        if (soundState) {
            player.setVolume(1.0f, 1.0f);
            sound.setChecked(true);
        }

        statistics = getSharedPreferences(StatisticsService.APP_PREF, Context.MODE_PRIVATE);
        editor = statistics.edit();

        gameText = (TextView)findViewById(R.id.gameText);

        choiceLevelResult();

        height = getNailFieldService().getNailField().getTableHeight();
        width = getNailFieldService().getNailField().getTableWidth();

        getNailFieldService().generateNailField();
        createField();

        AdService.getInstance(this).displayInterstitial();
    }

    protected void choiceLevelResult() {

        switch (getIntent().getStringExtra(LevelChoiceActivity.LEVEL)) {

            case LevelChoiceActivity.EASY:
                getNailFieldService().getNailField().setTableHeight(3);
                getNailFieldService().getNailField().setTableWidth(3);
                gameDifficulty = "easy";
                break;

            case LevelChoiceActivity.MEDIUM:
                getNailFieldService().getNailField().setTableHeight(4);
                getNailFieldService().getNailField().setTableWidth(4);
                gameDifficulty = "medium";
                break;

            case LevelChoiceActivity.HARD:
                getNailFieldService().getNailField().setTableHeight(5);
                getNailFieldService().getNailField().setTableWidth(5);
                gameDifficulty = "hard";
                break;

            default:
                break;
        }
    }

    private void  createField() {

        TableLayout tableLayout = (TableLayout)findViewById(R.id.main_table);
        setImageButtons(new ImageButton[height][width]);

        for (int i = 0; i < height; i++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setId(i);

            for (int j = 0; j < width; j++) {

                ImageButton imageButton = new ImageButton(this);
                getImageButtons()[i][j] = imageButton;
                imageButton.setOnClickListener(this);
                imageButton.setId(j);

                imageButton = makeImageButton(i, j, imageButton);

                int displayWidth = getWindowManager().getDefaultDisplay().getWidth();
                int buttonHW = displayWidth / (width + 1);
                tableRow.addView(imageButton, buttonHW, buttonHW);
            }

            tableLayout.addView(tableRow);
        }
    }

    private ImageButton makeImageButton(int i, int j, ImageButton imageButton) {
        boolean isVisible = getNailFieldService().getNailField().getField()[i][j].isVisibility();
        boolean isClickable = !getNailFieldService().getNailField().getField()[i][j].isPressed()
                && isVisible;

        imageButton.setClickable(isClickable);
        if (isClickable) {

            imageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.okunhitnailshadow));

        } else if (isVisible) {

            imageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.okhitnail));
        } else {

            imageButton.setVisibility(View.INVISIBLE);
        }
        return imageButton;
    }

    public void onClick(View view) {

        player.start();

        int rowId = ((TableRow) view.getParent()).getId();
        int columnId = view.getId();

        getNailFieldService().changeFieldState(rowId, columnId);
        drawField();

        clickCounter++;

        if (getNailFieldService().winChecker()){

            winStatus = true;
            String text = getString(R.string.wonText);
            //gameText.setText("You Won!" + " In " + Integer.toString(clickCounter) + " click.");
            gameText.setText(String.format(text, clickCounter));
        } else {

            gameText.setText(Integer.toString(clickCounter));
        }
    }

    private void drawField() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                getImageButtons()[i][j].setBackgroundDrawable(getNailFieldService().getNailField().getField()[i][j].isPressed()
                        ? getResources().getDrawable(R.drawable.okhitnail)
                        : getResources().getDrawable(R.drawable.okunhitnailshadow));
                    getImageButtons()[i][j].setClickable(!getNailFieldService().getNailField().getField()[i][j].isPressed());
            }
        }
    }


    public void restartClick(View view){
        StatisticsService.setStat(statistics, editor, clickCounter, winStatus,gameDifficulty);
        gameText.setText("0");
        clickCounter = 0;
        winStatus = false;

        getNailFieldService().changeFieldToFirstGeneration();
        drawField();
    }
    
    public void soundOffOn(View view) {
        if (!sound.isChecked()) {
            player.setVolume(0, 0);
            soundState = false;
        } else {
            player.setVolume(1.0f, 1.0f);
            soundState = true;
        }
    }

    public void inGameToMainClick(View view){
        startActivity(new Intent(this, StartActivity.class));
    }

    public void inGameNewGameClick(View view){
        onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (clickCounter > 0) {
            StatisticsService.setStat(statistics, editor, clickCounter, winStatus,gameDifficulty);
        }
    }

    public ImageButton[][] getImageButtons() {
        return imageButtons;
    }

    public void setImageButtons(ImageButton[][] imageButtons) {
        this.imageButtons = imageButtons;
    }

    public NailFieldService getNailFieldService() {
        return nailFieldService;
    }
}
