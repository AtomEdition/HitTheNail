package com.example.myapp.model;

/**
 * Created by Ì on 02.10.2015.
 */
public class NailField {

    private int tableHeight;
    private int tableWidth;

    private Nail[][] field;

    public NailField(int tableHeight, int tableWidth) {

        this.field = new Nail[tableHeight][tableWidth];
    }

    public int getTableHeight() {
        return tableHeight;
    }

    public void setTableHeight(int tableHeight) {
        this.tableHeight = tableHeight;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public void setTableWidth(int tableWidth) {
        this.tableWidth = tableWidth;
    }

    public Nail[][] getField() {
        return field;
    }

    public void setField(Nail[][] field) {
        this.field = field;
    }
}
