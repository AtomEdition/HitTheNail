package com.example.myapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.myapp.R;
import com.example.myapp.service.NailFieldService;

// todo: переписать onclick
public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */

    private ImageButton[][] imageButtons;

    private NailFieldService nailFieldService = NailFieldService.getInstance();

    private int random;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getNailFieldService().generateNailField(3, 3);
        createField();

    }

    private void  createField() {

        TableLayout tableLayout = (TableLayout)findViewById(R.id.main_table);

        int rows = getNailFieldService().getNailField().getTableWidth();
        int columns = getNailFieldService().getNailField().getTableHeight();

        setImageButtons(new ImageButton[rows][columns]);

        for (int i = 0; i < rows; i++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setId(i);

            for (int j = 0; j < columns; j++) {

                ImageButton imageButton = new ImageButton(this);
                imageButton.setOnClickListener(this);
                imageButton.setId(j);

                if (!getNailFieldService().getNailField().getField()[i][j].isPressed()) {

                    imageButton.setClickable(true);
                    imageButton.setBackground(getDrawable(R.drawable.unhitnail2));

                } else {

                    imageButton.setClickable(false);
                    imageButton.setBackground(getDrawable(R.drawable.hitnail2));
                }
                getImageButtons()[i][j] = imageButton;
                tableRow.setPadding(0, 10, 0, 10);
                tableRow.addView(imageButton, 70, 70);
            }

                //рандомно убираем по элементу из строки
                random = (int) (Math.random() * 3);
                imageButtons[i][random].setVisibility(View.INVISIBLE);
                imageButtons[i][random].setClickable(false);

            tableLayout.addView(tableRow);
        }
    }

    @Override
    public void onClick(View view) {

        int rowId = ((TableRow) view.getParent()).getId();
        int columnId = ((ImageButton) view).getId();

        getNailFieldService().changeFieldState(rowId, columnId);
        drawField();
    }

    private void drawField() {

        int height = getNailFieldService().getNailField().getTableHeight();
        int width = getNailFieldService().getNailField().getTableWidth();

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {

                    getImageButtons()[i][j].setBackground(getNailFieldService().getNailField().getField()[i][j].isPressed() ?
                        getDrawable(R.drawable.hitnail2) : getDrawable(R.drawable.unhitnail2));
                    getImageButtons()[i][j].setClickable(!getNailFieldService().getNailField().getField()[i][j].isPressed());
            }
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

    public void setNailFieldService(NailFieldService nailFieldService) {
        this.nailFieldService = nailFieldService;
    }
}
