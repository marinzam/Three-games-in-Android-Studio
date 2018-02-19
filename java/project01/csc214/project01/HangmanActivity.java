package project01.csc214.project01;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static project01.csc214.project01.MainActivity.sPlayerName1;
import static project01.csc214.project01.MainActivity.sPlayerName2;

import static project01.csc214.project01.MainActivity.sScore1;
import static project01.csc214.project01.MainActivity.sScore2;

/**
 * Created by geraldine
 * gmarinza@u.rochester.edu
 * CSC 214
 * TA: Sid
 * Project01
 */

public class HangmanActivity extends Activity {

    private HangmanGame mGame;
    private String mWordToGuess;
    private char mLetterGuess;
    private int mGuessCounterDrawable;
    public static HangmanPlayer sPlayer1;
    public static HangmanPlayer sPlayer2;

    private boolean mIsPlayer1;

    RelativeLayout layout;
    TextView wordTextView;
    TextView missedTextView;
    TextView triesLeft;
    EditText guessEditView;
    ImageView hangmanImage;
    TextView playerInstructions;
    Button guessButton;
    Button quit;

    TextView scorePlayer1;
    TextView scorePlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        layout = (RelativeLayout) findViewById(R.id.activity_hangman);
        layout.setBackgroundColor(Color.parseColor("#cd9bcd"));

        sPlayer1 = new HangmanPlayer(1);
        sPlayer2 = new HangmanPlayer(2);

        scorePlayer1 = (TextView) findViewById(R.id.score_player1_hangman);
        scorePlayer2 = (TextView) findViewById(R.id.score_player2_hangman);

        mLetterGuess = ' ';

        mGame = new HangmanGame();
        mIsPlayer1 = true;

        mWordToGuess = mGame.getRandomWord();

        quit = (Button) findViewById(R.id.hangman_quit_button);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mGuessCounterDrawable = 8;
        triesLeft = (TextView) findViewById(R.id.guess_left_textview);
        String triesText = "Tries left: "+mGuessCounterDrawable;
        triesLeft.setText(triesText);
        wordTextView = (TextView) findViewById(R.id.hangman_word_textview);
        missedTextView = (TextView) findViewById(R.id.missed_letters_textview);

        playerInstructions = (TextView) findViewById(R.id.guess_letter_textview);
        String newText = sPlayerName1+", guess a letter:";
        playerInstructions.setText(newText);

        guessEditView = (EditText) findViewById(R.id.enter_letter_editview);
        hangmanImage = (ImageView) findViewById(R.id.hangman_image);
        guessButton = (Button) findViewById(R.id.guess_letter_button);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send letter to java class
                String getText = guessEditView.getText().toString();
                if (getText.isEmpty()) {
                    getText = " ";
                    Toast.makeText(getApplicationContext(), "Guess is empty", Toast.LENGTH_SHORT).show();
                } else if(getText.length() > 1) {
                    Toast.makeText(getApplicationContext(), "Guess is too long", Toast.LENGTH_SHORT).show();
                }
                mLetterGuess = getText.charAt(0);

                int event;
                if (mIsPlayer1) {
                    event = mGame.newGuess(1, mLetterGuess); //boolean, String
                } else {
                    event = mGame.newGuess(2, mLetterGuess);
                }
                eventOccurred(event);
            }
        });
    }
    void eventOccurred(int event) {
        switch (event) {
            case -1: //missed, same player
                mGuessCounterDrawable--;
                String triesText = "Tries left: " + mGuessCounterDrawable;
                triesLeft.setText(triesText);
                //change drawable
                missedTextView.setText(mGame.getMsgMissed()); //update missed letters
                break;

            case 0: //guessed correctly, same player
                wordTextView.setText(mGame.getCurrentWord()); //update current word displayed
                break;

            case 1: //player 2 begins
                mGuessCounterDrawable = 8;
                String triesText2 = "Tries left: " + mGuessCounterDrawable;
                triesLeft.setText(triesText2);
                mIsPlayer1 = false;

                String newText = sPlayerName2 + ", guess a letter:";
                playerInstructions.setText(newText); //change player name displayed

                layout.setBackgroundColor(Color.parseColor("#9BCD9B")); //change background color
                //reset drawable
                missedTextView.setText(mGame.getMsgMissed()); //reset missed letters
                wordTextView.setText(mGame.getCurrentWord());//reset displayed word
                break;

            case 2:
                String winnerText;
                if (sPlayer1.isWinner()) {
                    winnerText = sPlayerName1 + " won!";
                    sScore1 = sScore1 + mGame.getScore();
                    String string1 = sPlayerName1 + ": "+sScore1;
                    scorePlayer1.setText(string1);
                } else {
                    winnerText = sPlayerName2 + " won!";
                    sScore2 = sScore2 + mGame.getScore();
                    String string2 = sPlayerName2 + ": "+sScore2;
                    scorePlayer2.setText(string2);
                }
                triesLeft.setText("");
                missedTextView.setText("");
                wordTextView.setText(winnerText);//game over,, display winner
                String answerWin = "(Word was: " + mGame.getRandomWord() + ")";
                playerInstructions.setText(answerWin);//display word
                layout.setBackgroundColor(Color.parseColor("#FFA500"));
                guessButton.setText("GAME OVER");
                break;
            case 3:
                missedTextView.setText("");
                wordTextView.setText("It was a tie!");//game over, display tie
                String answerTie = "(Word was: " + mGame.getRandomWord() + ")";
                playerInstructions.setText(answerTie);//display word
                layout.setBackgroundColor(Color.parseColor("#FFA500"));
                guessButton.setText("GAME OVER");
                break;

            default:
                Toast.makeText(getApplicationContext(), "Error Msg - default case", Toast.LENGTH_SHORT).show();
                break;
        }
        guessEditView.setText("");
    }
}
