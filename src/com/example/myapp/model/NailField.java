package com.example.myapp.model;


public class NailField {

    private int tableHeight;
    private int tableWidth;

    private Nail[][] field;

    public NailField() {
        this.field = new Nail[0][0];
    }

    public NailField(int tableHeight, int tableWidth) {

        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.field = new Nail[this.tableHeight][this.tableWidth];
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
