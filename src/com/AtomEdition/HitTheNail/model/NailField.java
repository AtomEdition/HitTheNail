package com.AtomEdition.HitTheNail.model;


public class NailField {

    private int tableHeight;
    private int tableWidth;

    private Nail[][] field;
    private Nail[][] tempField;

    public NailField() {
        setField(new Nail[0][0]);
        setTempField(new Nail[0][0]);
    }

    public NailField(int tableHeight, int tableWidth) {

        setTableHeight(tableHeight);
        setTableWidth(tableWidth);

        setField(new Nail[tableHeight][tableWidth]);
        setTempField(new Nail[tableHeight][tableWidth]);
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

    public Nail[][] getTempField() {
        return tempField;
    }

    public void setTempField(Nail[][] tempField) {
        this.tempField = tempField;
    }
}
