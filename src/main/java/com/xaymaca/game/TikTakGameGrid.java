package com.xaymaca.game;

import java.util.Arrays;

public class TikTakGameGrid {


    private String[] topRow = {"", "", ""};
    private String[] middleRow = {"", "", ""};
    private String[] bottomRow = {"", "", ""};

    private String[][] boxGrid = {
        topRow, middleRow, bottomRow
    };

    private boolean won = false;


    public String[][] getBoxGrid() {
        return boxGrid;
    }

    public void setMarkAt(Player player, int row, int column) {
        boxGrid[row][column] = player.getMark();
    }


    public void setMarkAt(String mark, int row, int column) {
        boxGrid[row][column] = mark;
    }

    public String getMarkAt(int row, int column) {
        return getBoxGrid()[row][column];
    }

    public String[] getTopRow() {
        return boxGrid[0];
    }
    public String[] getMiddleRow() {
        return boxGrid[1];
    }
    public String[] getBottomRow() {
        return boxGrid[2];
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public boolean checkForVictory() {

        boolean  won = false ;

         String[] leftColumn = {getTopRow()[0],getMiddleRow()[0], getBottomRow()[0]};
         String[] middleColumn = {getTopRow()[1],getMiddleRow()[1], getBottomRow()[1]};
         String[] rightColumn = {getTopRow()[2],getMiddleRow()[2], getBottomRow()[2]};
        threeInARow(leftColumn);
        threeInARow(middleColumn);
        threeInARow(rightColumn);
        threeInARow(getTopRow());
        threeInARow(getMiddleRow());
        threeInARow(getBottomRow());

        return won;
    }

    public void threeInARow(String[] testLocations) {

        if(Arrays.equals(testLocations, new String[]{"X", "X", "X"}) || Arrays.equals(testLocations, new String[]{"0", "0", "0"})){
            setWon(true);
        }

    }

}
