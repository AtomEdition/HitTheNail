package com.example.myapp.service;

import com.example.myapp.model.Nail;
import com.example.myapp.model.NailField;

import java.util.Random;

public class NailFieldService {

    private NailField nailField;
    private static NailFieldService instance;

    public NailFieldService(){
        this.nailField = new NailField();
    }

    public static NailFieldService getInstance(){
        if (instance == null) {
            instance = new NailFieldService();
        }
        return instance;
    }

    public void generateNailField(int height, int width) {

        Random random = new Random();

        setNailField(new NailField(height, width));
        int rows = getNailField().getTableHeight();
        int columns = getNailField().getTableWidth();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++){
                getNailField().getField()[i][j] = new Nail(random.nextBoolean());
            }
        }
    }

    public NailField changeFieldState(int positionVertical, int positionHorizontal) {

        for (int i = 0; i < getNailField().getTableWidth(); i++) {
            getNailField().getField()[positionVertical][i].changeState();
        }

        for (int j = 0; j < getNailField().getTableHeight(); j++) {
            getNailField().getField()[j][positionHorizontal].changeState();
        }

        getNailField().getField()[positionVertical][positionHorizontal].changeState();

        return getNailField();
    }

    public NailField getNailField() {
        return nailField;
    }

    public void setNailField(NailField nailField) {
        this.nailField = nailField;
    }
}
