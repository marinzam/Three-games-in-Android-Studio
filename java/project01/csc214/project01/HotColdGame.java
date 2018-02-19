package project01.csc214.project01;

/**
 * Created by geraldine
 * gmarinza@u.rochester.edu
 * CSC 214
 * TA: Sid
 * Project01
 */


import static project01.csc214.project01.HotColdActivity.sPlayer1;
import static project01.csc214.project01.HotColdActivity.sPlayer2;

public class HotColdGame {
    private int mNum;
    private int mGuess1;
    private int mGuess2;

    private int mPlayerNum;
    private int mScore;
    private String mMsg;

    public HotColdGame(){
        mGuess1 = 0;
        mGuess2 = 0;
        mNum = (int)(Math.random() * 10);   //random int between 0 and 10
        mScore = 0;
        mMsg = "Guess again!";
    }
    public String getMsg() {
        return mMsg;
    }
    public int getFinalScore() {
        return mScore;
    }

    //sets mScore and winner flag
    private void setWinningScore() {
        int diff = mGuess1 - mGuess2;
        if(diff < 0) {
            sPlayer1.setWinner();
        } else if(diff > 0) {
            sPlayer2.setWinner();
        }
        mScore = Math.abs(diff) * 100;
    }

    public int isGameOver() {
        if(mPlayerNum == 1)
        {
            mGuess1 = sPlayer1.getNumGuesses();
            //player 2's turn
            return 1;
        }
        else
        {
            mGuess2 = sPlayer2.getNumGuesses();
            // game over
            setWinningScore();
            return 2;
        }
    }

    // increments guesses, saves message to be displayed
    // return 0 if guess again
    // return 1 if player 1 succeeded
    // return 2 if player 2 succeeded
    public int newGuess(int playerNum, int guess) {
        this.mPlayerNum = playerNum;

        if(playerNum == 1)
        {
            sPlayer1.incNumGuesses();
        }
        else
        {
            sPlayer2.incNumGuesses();
        }
        int diff = Math.abs(mNum - guess);
        switch (diff) {
            case 0:
                mMsg = "Correct! You've guessed the number "+ mNum +"!";
                return isGameOver(); //check who's turn it is
            case 1:
                mMsg = "On Fire!";
                break;
            case 2:
                mMsg = "Hot";
                break;
            case 3:
                mMsg = "Warmer";
                break;
            case 4:
                mMsg = "Warm";
                break;
            case 5:
                mMsg = "Cold";
                break;
            case 6:
                mMsg = "Colder";
                break;
            case 7:
                mMsg = "Freezing!";
                break;
            case 8:
            case 9:
            case 10:
                mMsg = "Absolute Zero!";
                break;
            default:
                mMsg = "Keep guessing!";
                break;
        }
        return 0;
    }
}
