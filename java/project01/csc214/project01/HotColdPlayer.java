package project01.csc214.project01;

/**
 * Created by geraldine
 * gmarinza@u.rochester.edu
 * CSC 214
 * TA: Sid
 * Project01
 */

public class HotColdPlayer {
    public int mNum;
    private int mNumGuesses = 0;
    private boolean mWinner = false;

    public HotColdPlayer(int playerNum) {
        mNum = playerNum;
    }
    void incNumGuesses() {
        mNumGuesses++;
    }
    int getNumGuesses() {
        return mNumGuesses;
    }
    boolean isWinner() {
        return mWinner;
    }
    void setWinner() {
        mWinner = true;
    }
}
