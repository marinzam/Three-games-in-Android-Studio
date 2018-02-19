package project01.csc214.project01;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
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
public class ConnectFourActivity extends Activity {

    RelativeLayout layout;
    TextView instructions;
    GridView gridview;
    TextView scorePlayer1;
    TextView scorePlayer2;
    Button quit;
    private boolean mIsPlayer1;
    private ConnectFourGame mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_four);

        layout = (RelativeLayout) findViewById(R.id.activity_connect_four);
        layout.setBackgroundColor(Color.parseColor("#cd9bcd"));

        mGame = new ConnectFourGame();
        mIsPlayer1 = true;

        quit = (Button) findViewById(R.id.connect_quit_button);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        scorePlayer1 = (TextView) findViewById(R.id.score_player1_connect);
        scorePlayer2 = (TextView) findViewById(R.id.score_player2_connect);

        instructions = (TextView) findViewById(R.id.player_instructions_textview);
        String msg = sPlayerName1+", pick a circle.";
        instructions.setText(msg);

        updateGrid();
    }
    void updateGrid() {
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, mGame.mTokens));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(ConnectFourActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                int event;
                if (mIsPlayer1) {
                    event = mGame.newGuess(1, position); //boolean, String
                    updateGrid();
                } else {
                    event = mGame.newGuess(2, position);
                    updateGrid();
                }
                eventOccurred(event);
            }
        });
    }
    void eventOccurred(int event) {
        String textInstruction = "";
        switch (event) {
            case -1: //invalid click, same player
                break;
            case 0: //no winner, next player
                if (mIsPlayer1) {
                    textInstruction = sPlayerName2 + ", pick a circle";
                    mIsPlayer1 = false;
                    layout.setBackgroundColor(Color.parseColor("#9BCD9B"));
                } else {
                    textInstruction = sPlayerName1 + ", pick a circle";
                    mIsPlayer1 = true;
                    layout.setBackgroundColor(Color.parseColor("#cd9bcd"));
                }
                break;
            case 1: //player 1 won
                textInstruction = sPlayerName1 + " won!";
                layout.setBackgroundColor(Color.parseColor("#FFA500"));
                sScore1 = sScore1 + mGame.getScore();
                String string1 = sPlayerName1 + ": "+sScore1;
                scorePlayer1.setText(string1);
                break;
            case 2: //player 2 won
                textInstruction = sPlayerName2 + " won!";
                layout.setBackgroundColor(Color.parseColor("#FFA500"));
                sScore2 = sScore2 + mGame.getScore();
                String string2 = sPlayerName2 + ": "+sScore2;
                scorePlayer2.setText(string2);
                break;
            default:
                textInstruction = "error message";
                break;
        }
        instructions.setText(textInstruction);
    }
}