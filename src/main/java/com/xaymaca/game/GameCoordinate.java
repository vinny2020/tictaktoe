package com.xaymaca.game;

public class GameCoordinate {

    int column = 0;
    int row = 0;

    public GameCoordinate(int row, int column) {
        this.row = row;
        this.column = column;

    }

    public GameCoordinate(){

    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
