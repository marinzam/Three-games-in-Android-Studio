package project01.csc214.project01;

/**
 * Created by geraldine
 * gmarinza@u.rochester.edu
 * CSC 214
 * TA: Sid
 * Project01
 */

public class ConnectFourGame {

    boolean mIsPlayer1;
    public int[] mTokens;

    ConnectFourGame() {
        mIsPlayer1 = true;
        mTokens = new int[25];
    }
    public int getScore() {
        return 400;
    }

    public int newGuess(int playerNum, int position) {
        boolean mFourInARow = false;
        int mColorToken = playerNum;

        for(int i = (position+5); i<25; i+=5) {
            if(mColorToken == 0) {
                return -1; //invalid if grey circle below chosen circle
            }
        }

        if(mTokens[position] == 0) {
            mTokens[position] = mColorToken;
        } else {
            return -1; //invalid play
        }

        //Check for horizontal
        int mLeftEdge = position % 5; //pos = 21, left = 1

        int i =(position - mLeftEdge);
        if(mTokens[i] == mColorToken && mTokens[i+1] == mColorToken
                && mTokens[i+2] == mColorToken && mTokens[i+3] == mColorToken) {
            mFourInARow = true;
        }
        if(mTokens[i+1] == mColorToken && mTokens[i+2] == mColorToken
                && mTokens[i+3] == mColorToken && mTokens[i+4] == mColorToken) {
            mFourInARow = true;
        }


        //Check for vertical
        int j = mLeftEdge;
        if(mTokens[j] == mColorToken && mTokens[j+5] == mColorToken
                && mTokens[j+10] == mColorToken && mTokens[j+15] == mColorToken) {
            mFourInARow = true;
        }
        if(mTokens[j+5] == mColorToken && mTokens[j+10] == mColorToken
                && mTokens[j+15] == mColorToken && mTokens[j+20] == mColorToken) {
            mFourInARow = true;
        }


        //Check for left diagonal
        int k = 0;
        if(mTokens[k] == mColorToken && mTokens[k+6] == mColorToken
                && mTokens[k+12] == mColorToken && mTokens[k+18] == mColorToken) {
            mFourInARow = true;
        }
        k = 1;
        if(mTokens[k] == mColorToken && mTokens[k+6] == mColorToken
                && mTokens[k+12] == mColorToken && mTokens[k+18] == mColorToken) {
            mFourInARow = true;
        }
        k = 5;
        if(mTokens[k] == mColorToken && mTokens[k+6] == mColorToken
                && mTokens[k+12] == mColorToken && mTokens[k+18] == mColorToken) {
            mFourInARow = true;
        }
        k = 6;
        if(mTokens[k] == mColorToken && mTokens[k+6] == mColorToken
                && mTokens[k+12] == mColorToken && mTokens[k+18] == mColorToken) {
            mFourInARow = true;
        }


        //Check for right diagonal
        int l = 15;
        if(mTokens[l] == mColorToken && mTokens[l-4] == mColorToken
                && mTokens[l-8] == mColorToken && mTokens[l-12] == mColorToken) {
            mFourInARow = true;
        }
        l = 16;
        if(mTokens[l] == mColorToken && mTokens[l-4] == mColorToken
                && mTokens[l-8] == mColorToken && mTokens[l-12] == mColorToken) {
            mFourInARow = true;
        }
        l = 20;
        if(mTokens[l] == mColorToken && mTokens[l-4] == mColorToken
                && mTokens[l-8] == mColorToken && mTokens[l-12] == mColorToken) {
            mFourInARow = true;
        }
        l = 21;
        if(mTokens[l] == mColorToken && mTokens[l-4] == mColorToken
                && mTokens[l-8] == mColorToken && mTokens[l-12] == mColorToken) {
            mFourInARow = true;
        }
        if(mFourInARow) {
            return playerNum; //1 or 2
        }
        return 0;
    }
}
