package project01.csc214.project01;

/**
 * Created by geraldine
 * gmarinza@u.rochester.edu
 * CSC 214
 * TA: Sid
 * Project01
 */

public class HangmanPlayer {
    public int num;
    private int numGuesses = 0;
    private boolean winner = false;

    public HangmanPlayer(int playerNum) {
        num = playerNum;
    }
    void incNumGuesses() {
        numGuesses++;
    }
    int getNumGuesses() {
        return numGuesses;
    }
    boolean isWinner() {
        return winner;
    }
    void setWinner() {
        winner = true;
    }
}
