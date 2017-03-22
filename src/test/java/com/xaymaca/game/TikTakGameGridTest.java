package com.xaymaca.game;

import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;


public class TikTakGameGridTest {

    @Test
    public void verifyGridContainsNoEntriesAtStartup() {
        TikTakGameGrid tikTakGameGrid = new TikTakGameGrid();
        assertEquals(3, tikTakGameGrid.getBoxGrid().length);
        assertEquals(3, tikTakGameGrid.getBoxGrid()[0].length);

        String[] row1 = tikTakGameGrid.getBoxGrid()[0];
        String[] row2 = tikTakGameGrid.getBoxGrid()[1];
        String[] row3 = tikTakGameGrid.getBoxGrid()[2];
        assertArrayEquals(new String[]{"", "", ""}, row1);
        assertArrayEquals(new String[]{"", "", ""}, row2);
        assertArrayEquals(new String[]{"", "", ""}, row3);

    }


    @Test
    public void testSetMarks() {
        TikTakGameGrid board = new TikTakGameGrid();
        String markX = "X";
        String markO = "O";
        board.setMarkAt(markX, 0, 0);
        assertArrayEquals(new String[]{"X", "", ""}, board.getBoxGrid()[0]);
        assertArrayEquals(new String[]{"", "", ""}, board.getBoxGrid()[1]);
        assertArrayEquals(new String[]{"", "", ""}, board.getBoxGrid()[2]);
        board.setMarkAt(markO, 1, 2);
        assertArrayEquals(new String[]{"X", "", ""}, board.getBoxGrid()[0]);
        assertArrayEquals(new String[]{"", "", "O"}, board.getBoxGrid()[1]);
        assertArrayEquals(new String[]{"", "", ""}, board.getBoxGrid()[2]);
        board.setMarkAt(markX, 1, 0);
        assertArrayEquals(new String[]{"X", "", ""}, board.getBoxGrid()[0]);
        assertArrayEquals(new String[]{"X", "", "O"}, board.getBoxGrid()[1]);
        assertArrayEquals(new String[]{"", "", ""}, board.getBoxGrid()[2]);
        board.setMarkAt(markO, 2, 1);
        assertArrayEquals(new String[]{"X", "", ""}, board.getBoxGrid()[0]);
        assertArrayEquals(new String[]{"X", "", "O"}, board.getBoxGrid()[1]);
        assertArrayEquals(new String[]{"", "O", ""}, board.getBoxGrid()[2]);

    }


    @Test
    public void testGameEngineStart() {
        SimpleTwoPlayerEngine gameEngine = new SimpleTwoPlayerEngine();
        Player playerOne = new Player("X");
        gameEngine.setUpGame(playerOne);
        assertEquals(playerOne, gameEngine.getCurrentPlayer());
        assertTrue(gameEngine.getPlayer(2).getMark().equals("O"));
        assertTrue(gameEngine.getPlayer(1).getMark().equals("X"));
        assertEquals(SimpleTwoPlayerEngine.GameState.START, gameEngine.getCurrentState());

    }

    @Test
    public void testGameSetupAndPlay() {
        System.setIn(new ByteArrayInputStream("0 2".getBytes()));
        SimpleTwoPlayerEngine gameEngine = new SimpleTwoPlayerEngine();
        Player playerOne = new Player("X");
        gameEngine.setUpGame(playerOne);
        Player currentPlayer = gameEngine.getCurrentPlayer();
        assertEquals("X", currentPlayer.getMark());
        assertEquals(playerOne, currentPlayer);
        gameEngine.play();
        assertEquals(SimpleTwoPlayerEngine.GameState.PLAYING, gameEngine.getCurrentState());

    }

    @Test
    public void testTakingTurns() {


        SimpleTwoPlayerEngine gameEngine = new SimpleTwoPlayerEngine();
        Player playerOne = new Player("X");
        gameEngine.setUpGame(playerOne);
        TikTakGameGrid board = gameEngine.getGrid();


        System.setIn(new ByteArrayInputStream("0 0".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "", ""}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getBottomRow());

        System.setIn(new ByteArrayInputStream("0 1".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", ""}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getBottomRow());

        System.setIn(new ByteArrayInputStream("0 2".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getBottomRow());

        System.setIn(new ByteArrayInputStream("1 0".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"O", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""},  board.getBottomRow());

        System.setIn(new ByteArrayInputStream("1 1".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"O", "X", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""},  board.getBottomRow());

        System.setIn(new ByteArrayInputStream("1 2".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"O", "X", "O"}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""},  board.getBottomRow());

        System.setIn(new ByteArrayInputStream("2 0".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"O", "X", "O"}, board.getMiddleRow());
        assertArrayEquals(new String[]{"X", "", ""},  board.getBottomRow());

        System.setIn(new ByteArrayInputStream("2 1".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"O", "X", "O"}, board.getMiddleRow());
        assertArrayEquals(new String[]{"X", "O", ""},  board.getBottomRow());

        System.setIn(new ByteArrayInputStream("2 2".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"O", "X", "O"}, board.getMiddleRow());
        assertArrayEquals(new String[]{"X", "O", "X"},  board.getBottomRow());



        assertEquals(SimpleTwoPlayerEngine.GameState.END, gameEngine.getCurrentState());
    }


    @Test public void testForVictory() {

        // Victory is

        //When 3 adjacent cells are filled
        //includes 2 diagonal options

        //brute force vs elegant
        //in a 3x3 grid there are only 8 possibilities for either side
        //can predefine 8 conditions and manually check
        //otherwise
        // use dynamic logic to determine the condition
        // for this simple game it may not be worth the effort
        // the 8 known victory conditions are:
        // top row filled
        // middle  row filled
        // bottom  row filled

        // left column filled
        // middle column filled
        // bottom column filled

        //TODO 3 in a row diagonal positive slope
        //TODO 3 in a row diagonal negative  slope
        SimpleTwoPlayerEngine gameEngine = new SimpleTwoPlayerEngine();
        Player playerOne = new Player("X");
        gameEngine.setUpGame(playerOne);
        TikTakGameGrid board = gameEngine.getGrid();

        System.setIn(new ByteArrayInputStream("0 0".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "", ""}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getBottomRow());
        System.setIn(new ByteArrayInputStream("2 0".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "", ""}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"O", "", ""}, board.getBottomRow());
        System.setIn(new ByteArrayInputStream("0 1".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "X", ""}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"O", "", ""}, board.getBottomRow());
        System.setIn(new ByteArrayInputStream("2 1".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "X", ""}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"O", "O", ""}, board.getBottomRow());
        System.setIn(new ByteArrayInputStream("0 2".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "X", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"O", "O", ""}, board.getBottomRow());
        assertTrue(board.isWon());


    }



    @Test
    public void testVictoryCondition() {


        SimpleTwoPlayerEngine gameEngine = new SimpleTwoPlayerEngine();
        Player playerOne = new Player("X");
        gameEngine.setUpGame(playerOne);
        TikTakGameGrid board = gameEngine.getGrid();


        System.setIn(new ByteArrayInputStream("0 0".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "", ""}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getBottomRow());

        System.setIn(new ByteArrayInputStream("0 1".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", ""}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getBottomRow());

        System.setIn(new ByteArrayInputStream("0 2".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""}, board.getBottomRow());

        System.setIn(new ByteArrayInputStream("1 1".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"", "O", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""},  board.getBottomRow());

        System.setIn(new ByteArrayInputStream("1 0".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"X", "O", ""}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""},  board.getBottomRow());

        System.setIn(new ByteArrayInputStream("1 2".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"X", "O", "O"}, board.getMiddleRow());
        assertArrayEquals(new String[]{"", "", ""},  board.getBottomRow());

        System.setIn(new ByteArrayInputStream("2 0".getBytes()));
        gameEngine.play();
        assertArrayEquals(new String[]{"X", "O", "X"}, board.getTopRow());
        assertArrayEquals(new String[]{"X", "O", "O"}, board.getMiddleRow());
        assertArrayEquals(new String[]{"X", "", ""},  board.getBottomRow());

        assertTrue(board.isWon());
        assertEquals(SimpleTwoPlayerEngine.GameState.END, gameEngine.getCurrentState());
    }


    @After
    public void resetSystem() {
        System.setIn(System.in);
    }


}
