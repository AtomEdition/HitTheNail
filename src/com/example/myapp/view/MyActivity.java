package com.example.myapp.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.example.myapp.R;

import java.util.Random;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */

    TableLayout tableLayout;
    private static final int GF = 3;
    ToggleButton[][] toggleButtons = new ToggleButton[GF][GF];
    private int number;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tableLayout = (TableLayout)findViewById(R.id.main_table);

        for (int i = 0; i<GF; i++){
            TableRow tableRow = new TableRow(this);  //создаем строки таблицы
            tableRow.setId(i);
                for (int j = 0; j<GF; j++){ //помещаем в них кнопки
                    ToggleButton toggleButton = new ToggleButton(this);
                    toggleButton.setOnClickListener(this);
                    toggleButton.setId(j);
                    toggleButton.setTextOn("");
                    toggleButton.setTextOff("");
                    toggleButton.setChecked(new Random().nextBoolean());

                        if (!toggleButton.isChecked()){ //установка состояния
                            // и бэкграунда
                           toggleButton.setClickable(false);
                           toggleButton.setBackground(getDrawable(R.drawable.hitnail));
                        } else toggleButton.setBackground(getDrawable(R.drawable.unhitnail1));

                    toggleButtons[i][j]=toggleButton;
                    tableRow.setPadding(0, 10, 0, 10);
                    tableRow.addView(toggleButton, 70, 70);
                }
                tableLayout.addView(tableRow);
        }

        for (int i = 0; i<GF; i++) { //рандомно убираем по элементу из строки
            int rand = (int)(Math.random()*GF);
            toggleButtons[i][rand].setVisibility(View.INVISIBLE);
            toggleButtons[i][rand].setChecked(false);
            toggleButtons[i][rand].setClickable(false);
        }
    }


    @Override
    public void onClick(View view) {
        int rowId =((TableRow)view.getParent()).getId();
        int tbId = ((ToggleButton)view).getId();

        for (int i = 0; i<GF; i++) {
            int zero = GF;
            checker(i, tbId);
            checker(rowId, i);
        }
    }

    public void checker(int a, int b){ // при клике по элементу меняем
        // состояние элементов в соотв. сроке и столбце на противоположное
        if (toggleButtons[a][b].getVisibility()!=View.INVISIBLE){
            toggleButtons[a][b].setChecked(!toggleButtons[a][b].isChecked());
            if (toggleButtons[a][b].isChecked()){
                toggleButtons[a][b].setClickable(true);
                toggleButtons[a][b].setBackground(getDrawable(R.drawable.unhitnail1));
            } else{
               toggleButtons[a][b].setClickable(false);
               toggleButtons[a][b].setBackground(getDrawable(R.drawable.hitnail));
            }
        }
    }
}
