package project01.csc214.project01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 * Created by geraldine
 * gmarinza@u.rochester.edu
 * CSC 214
 * TA: Sid
 * Project01
 */
public class MainActivity extends Activity {
    public static String sPlayerName1;
    public static String sPlayerName2;
    public static int sScore1;
    public static int sScore2;

    EditText player1EditText;
    EditText player2EditText;
    Button hotterColder;
    Button hangman;
    Button connectFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1EditText = (EditText) findViewById(R.id.edit_text_player_1);
        player2EditText = (EditText) findViewById(R.id.edit_text_player_2);

        hotterColder = (Button) findViewById(R.id.hot_or_cold_button);

        hotterColder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPlayerName1 =  player1EditText.getText().toString();
                sPlayerName2 = player2EditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, HotColdActivity.class);
                startActivity(intent);
            }
        });

        hangman = (Button) findViewById(R.id.hangman_button);

        hangman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPlayerName1 =  player1EditText.getText().toString();
                sPlayerName2 = player2EditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, HangmanActivity.class);
                startActivity(intent);
            }
        });

        connectFour = (Button) findViewById(R.id.connect_4_button);

        connectFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPlayerName1 =  player1EditText.getText().toString();
                sPlayerName2 = player2EditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ConnectFourActivity.class);
                startActivity(intent);
            }
        });
    }
}
