package com.example.myapp.service;

import com.example.myapp.model.Nail;
import com.example.myapp.model.NailField;

import java.util.Random;

public class NailFieldService {

    private NailField nailField;

    int height;
    int width;

    private static NailFieldService instance;

    public NailFieldService(){
        setNailField(new NailField());
    }

    public static NailFieldService getInstance(){
        if (instance == null) {
            instance = new NailFieldService();
        }
        return instance;
    }

    //todo: ������� ��������� ��� �������� �������� �������, ����� ������ � ������ ���������� � NailField ��� ��� ��������
    public void generateNailField() {

        height = getNailField().getTableHeight();
        width = getNailField().getTableWidth();

        setNailField(new NailField(height, width));

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){
               getNailField().getField()[i][j] = new Nail(true);
               getNailField().getTempField()[i][j] = new Nail(true);
            }
        }

        Random random = new Random();
        int randIntH;
        int randIntW;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                randIntH = random.nextInt(height);
                randIntW = random.nextInt(width);

                if (getNailField().getField()[randIntH][randIntW].isPressed()) {
                    changeFieldState(randIntH, randIntW);
                }
            }

            randIntW = random.nextInt(width);

            getNailField().getField()[i][randIntW].setVisibility(false);
            getNailField().getField()[i][randIntW].setPressed(true);
        }

        if(winChecker()){
            generateNailField();
        }

        //todo:������������
        for (int i=0; i<height; i++){
            for (int j=0; j<width; j++){

                getNailField().getTempField()[i][j].setPressed(getNailField().getField()[i][j].isPressed());

                if (!getNailField().getField()[i][j].isVisibility()) {
                    getNailField().getTempField()[i][j].setVisibility(false);
                }
            }
        }
    }

//    private void cloneField(){
//        getNailField().setTempField(getNailField().getField().clone());
//        for (int i=0; i<getNailField().getField().length; i++){
//            getNailField().getTempField()[i]=getNailField().getField()[i].clone();
//        }
//
//
//    }

    public void changeFieldState(int positionVertical, int positionHorizontal) {

        for (int i = 0; i < width; i++) {

            if (getNailField().getField()[positionVertical][i].isVisibility()) {
                getNailField().getField()[positionVertical][i].changeState();
            }
        }

        for (int j = 0; j < height; j++) {

            if (getNailField().getField()[j][positionHorizontal].isVisibility()){
                getNailField().getField()[j][positionHorizontal].changeState();
            }
        }

        getNailField().getField()[positionVertical][positionHorizontal].changeState();

    }

    public void changeFieldToFirstGeneration(){
        for (int i=0; i<height; i++){
            for (int j=0; j<width; j++){

                getNailField().getField()[i][j].setPressed(getNailField().getTempField()[i][j].isPressed());

                if (!getNailField().getTempField()[i][j].isVisibility()) {
                    getNailField().getField()[i][j].setVisibility(false);
                }
            }
        }
    }


    public boolean winChecker(){

        int square = height*width-height;

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                if (getNailField().getField()[i][j].isPressed() &&
                        getNailField().getField()[i][j].isVisibility()){
                    square--;
                }
            }
        }

        if (square==0) return true;
        else return false;
    }

    public NailField getNailField() {
        return nailField;
    }

    public void setNailField(NailField nailField) {
        this.nailField = nailField;
    }
}
