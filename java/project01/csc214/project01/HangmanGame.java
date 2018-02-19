package project01.csc214.project01;

import static project01.csc214.project01.HangmanActivity.sPlayer1;
import static project01.csc214.project01.HangmanActivity.sPlayer2;

/**
 * Created by geraldine
 * gmarinza@u.rochester.edu
 * CSC 214
 * TA: Sid
 * Project01
 */

public class HangmanGame {
    private String mRandomWord;
    private String mCurrentWord;
    private String mMissedLetters;

    private char[] mRandomWordArray;
    private char[] mCurrentWordArray;

    private int mGuess1;
    private int mGuess2;

    private int mPlayerNum;
    private int mScore;

    private String mMsgMissed;
    private int mMsgCount;

    private static int GUESS_LIMIT = 8;

    public HangmanGame() {
        mRandomWord = pickRandomWord(); //method to generate string
        mScore = 0;
        mMissedLetters = "";
        mMsgMissed = "Already missed: ";
        mRandomWordArray = mRandomWord.toCharArray(); //{'y','e','l','l','o','w'}
        mCurrentWord = "______";
        mCurrentWordArray = mCurrentWord.toCharArray();
    }
    public String getRandomWord() {
        return mRandomWord;
    }
    public String getMsgMissed() {
        return mMsgMissed;
    }
    public String getCurrentWord() {
        return mCurrentWord;
    }
    public int getScore() {
        return mScore;
    }

    //sets mScore and winner flag
    private void setWinningScore() {
        int diff = mGuess1 - mGuess2;
        if(diff < 0 && mGuess1 != GUESS_LIMIT) {
            sPlayer1.setWinner();
            mScore = Math.abs(diff) * 100;
        } else if(diff > 0 && mGuess2 != GUESS_LIMIT) {
            sPlayer2.setWinner();
            mScore = Math.abs(diff) * 100;
        } else { //diff = 0: tie
            mScore = 0;
        }
    }

    private int isGameOver() {
        if(mPlayerNum == 1)
        {
            mGuess1 = sPlayer1.getNumGuesses();
            this.mPlayerNum = 2; //switch to player2
            mMissedLetters = " ";
            mMsgMissed = "Already missed: ";
            mCurrentWord = "______";
            mCurrentWordArray = mCurrentWord.toCharArray();
            return 1; //player 2's turn
        }
        else
        {
            // game over - either player2 guessed correctly or ran out of tries
            mGuess2 = sPlayer2.getNumGuesses();
            if(mGuess2 == GUESS_LIMIT && mGuess1 == GUESS_LIMIT) { return 3; } //tie, no winner
            setWinningScore();
            return 2; //game over, display winner
        }
    }

    public int newGuess(int playerNum, char guess) {
        this.mPlayerNum = playerNum;

        boolean mIsInWord = false;
        String guessString = ""+guess;
        if (mRandomWord.contains(guessString)) {
            mIsInWord = true;
        }

        //if guess is wrong
        if (!mIsInWord) {
            //if not already guessed, increment guess count
            boolean alreadyGuessed = false;
            if (mMissedLetters.contains(guessString)) {
                alreadyGuessed = true;
            }

            if (alreadyGuessed == false) {
                //increase number of guesses for current player
                if (playerNum == 1) {
                    sPlayer1.incNumGuesses();
                    mMsgCount = sPlayer1.getNumGuesses();
                } else {
                    sPlayer2.incNumGuesses();
                    mMsgCount = sPlayer2.getNumGuesses();
                }
                //add to missed letters string
                //change missed message string (to display)
                if (mMsgCount < GUESS_LIMIT) //if at limit
                {
                    mMissedLetters = mMissedLetters.concat(" "+ guessString); //space before
                    mMsgMissed = "Already guessed: " + mMissedLetters;
                    return -1;
                } else //if at GUESS_LIMIT
                {
                    return isGameOver(); //return 1 or
                }
            }
        }
        // mIsInWord = true (if guessed correctly)
        else
        {
            for (int i = 0; i < mRandomWordArray.length; i++) //loop through to set letters
            {
                if (mRandomWordArray[i] == guess) //index of matching letter
                {
                    mCurrentWordArray[i] = guess; //letter saved to this array at index
                }
            }
            mCurrentWord = "";
            // save progress in Current Word
            for (int i = 0; i < mCurrentWordArray.length; i++) //loop through to set letters
            {
                if (mCurrentWordArray[i] != '_') {
                    String letter = ""+mRandomWordArray[i];
                    mCurrentWord = mCurrentWord.concat(letter); //if letter, then save
                } else {
                    mCurrentWord = mCurrentWord.concat("_"); //if no letter, then '_'
                }
            }
            // if word all the way guessed, next person's turn
            if((mCurrentWord).equals(mRandomWord)) {
                return isGameOver(); //return 1 or 2
            } else {
                return 0; //continue guessing
            }
        }
        return 0;
    }
    String pickRandomWord() {
        String[] words = {"iphone", "yellow", "tragic", "bottle", "carpet",
            "pencil", "teacup", "summer", "polish", "roller", "towels",
        "fidget", "friday","tissue", "string", "purple"};
        int index = (int)(Math.random() * 15);
        return words[index];
    }
}
