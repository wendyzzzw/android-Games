package project01.wengame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    //keys
    public static final String PLAYER1_NAME="main.p1n";
    public static final String PLAYER2_NAME="main.p2n";
    public static final String PLAYER1_SCORE="main.p1s";
    public static final String PLAYER2_SCORE="main.p2s";

    //TextViews on Score Board
    private TextView mTVp1n;
    private TextView mTVp2n;
    private TextView mTVp1s;
    private TextView mTVp2s;

    //saved player names and scores
    private String player1="";
    private String player2="";
    private int score1=0;
    private int score2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditTexts for entering players' names
        final EditText mP1N = (EditText) findViewById(R.id.et_player1_name);
        final EditText mP2N = (EditText) findViewById(R.id.et_player2_name);

        mTVp1n = (TextView) findViewById(R.id.tv_player1_name);
        mTVp2n = (TextView) findViewById(R.id.tv_player2_name);
        mTVp1s = (TextView) findViewById(R.id.tv_player1_score);
        mTVp2s = (TextView) findViewById(R.id.tv_player2_score);

        //initial score board
        mTVp1n.setText("Player 1");
        mTVp2n.setText("Player 2");
        mTVp1s.setText("Player 1 Score");
        mTVp2s.setText("Player 2 Score");

        //OK button, when clicked, players' names are updated to score board
        Button mButtonOK = (Button) findViewById(R.id.button_OK);
        mButtonOK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                mTVp1n.setText(mP1N.getText().toString());
                mTVp2n.setText(mP2N.getText().toString());
                mTVp1s.setText(""+score1);
                mTVp2s.setText(""+score2);
                player1 = mP1N.getText().toString();
                player2 = mP2N.getText().toString();
            }
        });

        //Game buttons, when clicked, start the corresponding game
        Button mTTT = (Button) findViewById(R.id.game_ttt);
        Button mC4 = (Button) findViewById(R.id.game_c4);
        Button mHM = (Button) findViewById(R.id.game_hm);

        mTTT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this,TicTacToe.class);
                intent.putExtra(PLAYER1_NAME,player1);
                intent.putExtra(PLAYER2_NAME,player2);
                intent.putExtra(PLAYER1_SCORE,score1);
                intent.putExtra(PLAYER2_SCORE,score2);
                startActivityForResult(intent,0);
            }
        });

        mC4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this,ConnectFour.class);
                intent.putExtra(PLAYER1_NAME,player1);
                intent.putExtra(PLAYER2_NAME,player2);
                intent.putExtra(PLAYER1_SCORE,score1);
                intent.putExtra(PLAYER2_SCORE,score2);
                startActivityForResult(intent,0);
            }
        });

        mHM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this,HangMan.class);
                intent.putExtra(PLAYER1_NAME,player1);
                intent.putExtra(PLAYER2_NAME,player2);
                intent.putExtra(PLAYER1_SCORE,score1);
                intent.putExtra(PLAYER2_SCORE,score2);
                startActivityForResult(intent,0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data!= null){
            //get data
            player1 = data.getStringExtra(PLAYER1_NAME);
            player2 = data.getStringExtra(PLAYER2_NAME);
            score1 = data.getIntExtra(PLAYER1_SCORE,1);
            score2 = data.getIntExtra(PLAYER2_SCORE,1);
            //set TextView on Score Board
            mTVp1n.setText(player1);
            mTVp2n.setText(player2);
            mTVp1s.setText(""+score1);
            mTVp2s.setText(""+score2);
        }
    }
}
