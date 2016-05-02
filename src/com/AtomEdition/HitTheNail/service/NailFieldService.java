package com.AtomEdition.HitTheNail.service;

import com.AtomEdition.HitTheNail.model.Nail;
import com.AtomEdition.HitTheNail.model.NailField;

import java.util.Random;

public class NailFieldService {

    private NailField nailField;

    private int height;
    private int width;

    private Random random = new Random();

    private static NailFieldService instance;

    private NailFieldService(){
        setNailField(new NailField());
    }

    public static NailFieldService getInstance(){
        if (instance == null) {
            instance = new NailFieldService();
        }
        return instance;
    }


    public void generateNailField() {

        height = nailField.getTableHeight();
        width = nailField.getTableWidth();

        int rand;

        nailField = new NailField(height, width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){
                nailField.getField()[i][j] = new Nail(true);
            }

            rand = random.nextInt(width);
            nailField.getField()[i][rand].setVisibility(false);
        }

        confuseCreatedNailField();

        if (winChecker()) {
            generateNailField();
        }

        createTempField();

    }

    private void confuseCreatedNailField() {

        int randIntH;
        int randIntW;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                randIntH = random.nextInt(height);
                randIntW = random.nextInt(width);

                if (nailField.getField()[randIntH][randIntW].isPressed() &&
                        nailField.getField()[randIntH][randIntW].isVisibility()) {
                    changeFieldState(randIntH, randIntW);
                }
            }
        }
    }

    private void createTempField() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                nailField.getTempField()[i][j] = new Nail(true);

                nailField.getTempField()[i][j].setPressed(getNailField().getField()[i][j].isPressed());

                if (!nailField.getField()[i][j].isVisibility()) {
                    nailField.getTempField()[i][j].setVisibility(false);
                }
            }
        }
    }


    public void changeFieldState(int positionVertical, int positionHorizontal) {

        for (int i = 0; i < width; i++) {

            if (nailField.getField()[positionVertical][i].isVisibility()) {
                nailField.getField()[positionVertical][i].changeState();
            }
        }

        for (int j = 0; j < height; j++) {

            if (nailField.getField()[j][positionHorizontal].isVisibility()){
                nailField.getField()[j][positionHorizontal].changeState();
            }
        }

        nailField.getField()[positionVertical][positionHorizontal].changeState();

    }

    public void changeFieldToFirstGeneration(){
        for (int i=0; i<height; i++){
            for (int j=0; j<width; j++){

                nailField.getField()[i][j].setPressed(getNailField().getTempField()[i][j].isPressed());

                if (!nailField.getTempField()[i][j].isVisibility()) {
                    nailField.getField()[i][j].setVisibility(false);
                }
            }
        }
    }


    public boolean winChecker(){

        int square = height*width-height;

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                if (nailField.getField()[i][j].isPressed() &&
                        nailField.getField()[i][j].isVisibility()){
                    square--;
                }
            }
        }

        return (square == 0);
    }


    public NailField getNailField() {
        return nailField;
    }

    public void setNailField(NailField nailField) {
        this.nailField = nailField;
    }
}
