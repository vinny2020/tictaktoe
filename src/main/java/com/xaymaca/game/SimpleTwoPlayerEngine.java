package com.xaymaca.game;

import java.util.Arrays;
import java.util.Scanner;

public class SimpleTwoPlayerEngine {


    public enum GameState {
        START, PLAYING, END
    }

    private GameState currentState = GameState.START;
    private Player currentPlayer;
    private Player waitingPlayer;
    private Player[] players = new Player[2];
    private TikTakGameGrid grid = new TikTakGameGrid();
    private int turnCounter = 1;


    public void setUpGame(Player player1) {
        player1.setFirst();
        currentPlayer = player1;
        players[0] = player1;
        waitingPlayer = player1.getMark().equals("X") ? new Player("O") : new Player("X");
        players[1] = waitingPlayer;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWaitingPlayer() {
        return waitingPlayer;
    }


    public Player getPlayer(int playerNumber) {
        return players[playerNumber - 1];
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public TikTakGameGrid getGrid() {
        return grid;
    }



    public void play() {

        Player one = new Player("");
        one.setFirst();
        Player two = new Player("");

        System.out.println("Current State: " + currentState.toString());

        if (currentState == GameState.START) {

            doStart(one, two);
        }

        if (currentState == GameState.PLAYING) {
            doPlay();
        }


        if (currentState == GameState.END) {
            doEnd();
        }
    }

    private void doEnd() {
        System.out.println("Game Over");
    }


    private GameCoordinate convertCoordinates(String imputedText) {
        String[] coordinates = imputedText.trim().split(" ");
        int row = Integer.parseInt(coordinates[0]);
        int column = Integer.parseInt(coordinates[1]);
        return new GameCoordinate(row, column);
    }


    private void doStart(Player one, Player two) {
        if (currentPlayer.isFirst() && Arrays.asList(players).containsAll(Arrays.asList(one, two)) && !players[0].getMark().equals(players[1].getMark())) {
            //Setup looks good, change state
            currentState = GameState.PLAYING;
            System.out.println("Changing state to " + currentState.toString());
        }
    }

    private void doPlay() {
        System.out.println("Starting turn number " + turnCounter);
        System.out.println("Player " + (currentPlayer.isFirst() ? "1" : "2") + ": place your mark(" + currentPlayer.getMark() + ") :");
        //wait for input
        Scanner scanner = new Scanner(System.in);
        String imputedText = scanner.nextLine();
        GameCoordinate coordinate = convertCoordinates(imputedText);
        grid.setMarkAt(currentPlayer, coordinate.getRow(), coordinate.getColumn());
        swapPlayers();
        System.out.println("Ending turn number " + turnCounter);
        turnCounter++;
        grid.checkForVictory();
        if (grid.isWon()) {
            currentState = GameState.END;
        }

        //If we are on our 9th turn, regardless of outcome, the game is over.
        if (turnCounter == 10) {
            currentState = GameState.END;

        }
    }


    private void swapPlayers() {
        Player current = getCurrentPlayer();
        currentPlayer = getWaitingPlayer();
        waitingPlayer = current;

    }


    // Todo: Task for each state

    // PLAYING:
    // Loop a prompt output directing the current player to play
    // Enforce that opposing marks are done in succession. Players cannot change marks
    // once in PLAYING state , only at the START
    // Play continues until either
    // A.
    // Victory condition is achieved or
    // B. all the squares are filled or
    // TODO C. any player types "q" or "quit"
    // State then changes to
    // END:  program terminates


}
