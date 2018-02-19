package project01.csc214.project01;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class HotColdActivity extends Activity {

    private HotColdGame mGame;
    public static HotColdPlayer sPlayer1;
    public static HotColdPlayer sPlayer2;
    private boolean mIsPlayer1;

    private int mGuess;
    private int MIN = 0;
    private int MAX = 10;

    RelativeLayout layout;
    TextView textInstruction;
    EditText editGuess;
    Button guessButton;
    TextView guess1Counter;
    TextView guess2Counter;
    Button quit;
    TextView scorePlayer1;
    TextView scorePlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotter_colder);

        layout = (RelativeLayout) findViewById(R.id.activity_hotter_colder);
        layout.setBackgroundColor(Color.parseColor("#cd9bcd"));
        sPlayer1 = new HotColdPlayer(1);
        sPlayer2 = new HotColdPlayer(2);

        mGame = new HotColdGame();
        mIsPlayer1 = true; //changes after player successfully guesses number

        scorePlayer1 = (TextView) findViewById(R.id.score_player1_hot_cold);
        scorePlayer2 = (TextView) findViewById(R.id.score_player2_hot_cold);

        textInstruction = (TextView) findViewById(R.id.enter_guess_textview);
        String newText = sPlayerName1 + ", enter your mGuess:";
        textInstruction.setText(newText);

        editGuess = (EditText) findViewById(R.id.enter_guess_editview);

        quit = (Button) findViewById(R.id.hot_quit_button);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guessButton = (Button) findViewById(R.id.guess_button);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuess = Integer.valueOf(editGuess.getText().toString().trim());
                if (mGuess < MIN || mGuess > MAX) {
                    Toast.makeText(getApplicationContext(), "Guess not in range", Toast.LENGTH_SHORT).show();
                }

                int event;
                if (mIsPlayer1) {
                    event = mGame.newGuess(1, mGuess); //boolean, int
                } else {
                    event = mGame.newGuess(2, mGuess);
                }
                eventOccurred(event);
            }
        });
        guess1Counter = (TextView) findViewById(R.id.player1_guesses);
        guess2Counter = (TextView) findViewById(R.id.player2_guesses);
    }

    void eventOccurred(int event) {
        if (event == 1) //if player 1 guessed correctly
        {
            guess1Counter.setText(String.valueOf(sPlayer1.getNumGuesses()));
            layout.setBackgroundColor(Color.parseColor("#9BCD9B"));
            Toast.makeText(getApplicationContext(), "NUMBER WAS GUESSED!", Toast.LENGTH_SHORT).show();
            nextTurn();   //change who is playing next
            editGuess.setText("");
            //continue mGame
        } else if (event == 2) //if player 2 guessed correctly
        {
            guess2Counter.setText(String.valueOf(sPlayer2.getNumGuesses())); //save number of guesses
            //end mGame
            String newText;
            if (sPlayer1.isWinner() && !sPlayer2.isWinner()) //declare winner
            {
                newText = sPlayerName1 + " won!";
                sScore1 = sScore1 + mGame.getFinalScore();
                String string1 = sPlayerName1 + ": "+sScore1;
                scorePlayer1.setText(string1);
                //set score on scoreboard for player 1 mGame.getFinalScore();
            } else if(sPlayer2.isWinner() && !sPlayer1.isWinner()){
                newText = sPlayerName2 + " won!";
                sScore2 = sScore2 + mGame.getFinalScore();
                String string2 = sPlayerName2 + ": "+sScore2;
                scorePlayer2.setText(string2);
                //set score on scoreboard for player 2 mGame.getFinalScore();
            } else {
                newText = "It was a tie!";
            }
            textInstruction.setText(newText);
            layout.setBackgroundColor(Color.parseColor("#FFA500"));

            guessButton.setText("GAME OVER");
            quitGame();
        } else //if mGame not won yet
        {
            guess1Counter.setText(String.valueOf(sPlayer1.getNumGuesses()));
            guess2Counter.setText(String.valueOf(sPlayer2.getNumGuesses()));
            Toast.makeText(getApplicationContext(), mGame.getMsg(), Toast.LENGTH_SHORT).show();
            editGuess.setText(""); //reset EditText
            //continue mGame
        }
    }

    void nextTurn() {
        String newText;
        if (mIsPlayer1) {
            newText = sPlayerName2 + ", enter your mGuess:";
            mIsPlayer1 = false;
        } else {
            newText = sPlayerName1 + ", enter your mGuess:";
            mIsPlayer1 = true;
        }
        textInstruction.setText(newText);
    }

    void quitGame() {

    }
}
