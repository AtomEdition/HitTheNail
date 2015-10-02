package com.example.myapp.service;

import com.example.myapp.model.Nail;
import com.example.myapp.model.NailField;

import java.util.Random;

/**
 * Created by Ì on 02.10.2015.
 */
public class NailFieldService {

    public NailField getGeneratedNailField(int height, int width) {

        NailField nailField = new NailField(height, width);
        Random random = new Random();
        for (Nail[] rows : nailField.getField()) {

            for (Nail nail : rows) {

                nail = new Nail(random.nextBoolean());
            }
        }

        return nailField;
    }

    public NailField changeFieldState(NailField nailField, int positionVertical, int positionHorizontal) {

        for (int i = 0; i < nailField.getTableWidth(); i++) {

            nailField.getField()[i][positionHorizontal].changeState();
        }

        for (int j = 0; j < nailField.getTableHeight(); j++) {

            nailField.getField()[positionVertical][j].changeState();
        }

        nailField.getField()[positionVertical][positionHorizontal].changeState();

        return nailField;
    }
}
