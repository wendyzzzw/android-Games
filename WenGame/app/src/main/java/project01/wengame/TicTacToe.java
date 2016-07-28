package project01.wengame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe extends AppCompatActivity {
    //the three TextViews that needs to be change during this activity
    private TextView p1s;
    private TextView p2s;
    private TextView result;

    //saved player names and scores
    private int p1score;
    private int p2score;
    private String p1name;
    private String p2name;

    private int turn=0; //to decide which player is playing
    private boolean end = false; //to check if the game is ended
    private int[][] board= new int[4][4]; // our checkerboard for this game (empty:0, player1:1, player2:2)

    //the 3*3 checkerboard buttons
    private ImageButton mb11;
    private ImageButton mb12;
    private ImageButton mb13;
    private ImageButton mb21;
    private ImageButton mb22;
    private ImageButton mb23;
    private ImageButton mb31;
    private ImageButton mb32;
    private ImageButton mb33;

    //for double click the back to home button
    private boolean backButtonClickedOnce=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        TextView p1n = (TextView) findViewById(R.id.tv_player1_name);
        TextView p2n = (TextView) findViewById(R.id.tv_player2_name);
        TextView mfirst = (TextView) findViewById(R.id.tv_ttt_first);
        TextView msecond = (TextView) findViewById(R.id.tv_ttt_second);
        p1s = (TextView) findViewById(R.id.tv_player1_score);
        p2s = (TextView) findViewById(R.id.tv_player2_score);
        result = (TextView) findViewById(R.id.ttt_result);

        //get data passed from the main activity
        Intent intent = getIntent();
        if (intent!= null){
            p1name = intent.getStringExtra(MainActivity.PLAYER1_NAME);
            p2name = intent.getStringExtra(MainActivity.PLAYER2_NAME);
            p1n.setText(p1name);
            p2n.setText(p2name);
            mfirst.setText(p1name);
            msecond.setText(p2name);
            p1score = intent.getIntExtra(MainActivity.PLAYER1_SCORE,1);
            p2score = intent.getIntExtra(MainActivity.PLAYER2_SCORE,1);
            p1s.setText(""+p1score);
            p2s.setText(""+p2score);

        }

        //initialize the checkerboard
        for (int i=1; i<=3; i++){
            for (int j=1; j<=3; j++){
                board[i][j]=0;
            }
        }

        //set all the 9 buttons
        mb11 = (ImageButton) findViewById(R.id.ttt_11);
        mb11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true){
                    Toast.makeText(TicTacToe.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else if ((board[1][1] != 0)) {
                    Toast.makeText(TicTacToe.this,"A mark is already here, try other place",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){
                        board[1][1]=1;
                        mb11.setImageDrawable(getDrawable(R.drawable.ic_x));
                    }
                    else{
                        board[1][1]=2;
                        mb11.setImageDrawable(getDrawable(R.drawable.ic_y));
                    }
                    checkWin();
                }
            }
        });

        mb12 = (ImageButton) findViewById(R.id.ttt_12);
        mb12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true){
                    Toast.makeText(TicTacToe.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else if ((board[1][2] != 0)) {
                    Toast.makeText(TicTacToe.this,"A mark is already here, try other place",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){
                        board[1][2]=1;
                        mb12.setImageDrawable(getDrawable(R.drawable.ic_x));
                    }
                    else{
                        board[1][2]=2;
                        mb12.setImageDrawable(getDrawable(R.drawable.ic_y));
                    }
                    checkWin();
                }
            }
        });

        mb13 = (ImageButton) findViewById(R.id.ttt_13);
        mb13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true){
                    Toast.makeText(TicTacToe.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else if ((board[1][3] != 0)) {
                    Toast.makeText(TicTacToe.this,"A mark is already here, try other place",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){
                        board[1][3]=1;
                        mb13.setImageDrawable(getDrawable(R.drawable.ic_x));
                    }
                    else{
                        board[1][3]=2;
                        mb13.setImageDrawable(getDrawable(R.drawable.ic_y));
                    }
                    checkWin();
                }
            }
        });

        mb21 = (ImageButton) findViewById(R.id.ttt_21);
        mb21.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true){
                    Toast.makeText(TicTacToe.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else if ((board[2][1] != 0)) {
                    Toast.makeText(TicTacToe.this,"A mark is already here, try other place",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){
                        board[2][1]=1;
                        mb21.setImageDrawable(getDrawable(R.drawable.ic_x));
                    }
                    else{
                        board[2][1]=2;
                        mb21.setImageDrawable(getDrawable(R.drawable.ic_y));
                    }
                    checkWin();
                }
            }
        });

        mb22 = (ImageButton) findViewById(R.id.ttt_22);
        mb22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true){
                    Toast.makeText(TicTacToe.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else if ((board[2][2] != 0)) {
                    Toast.makeText(TicTacToe.this,"A mark is already here, try other place",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){
                        board[2][2]=1;
                        mb22.setImageDrawable(getDrawable(R.drawable.ic_x));
                    }
                    else{
                        board[2][2]=2;
                        mb22.setImageDrawable(getDrawable(R.drawable.ic_y));
                    }
                    checkWin();
                }
            }
        });

        mb23 = (ImageButton) findViewById(R.id.ttt_23);
        mb23.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true){
                    Toast.makeText(TicTacToe.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else if ((board[2][3] != 0)) {
                    Toast.makeText(TicTacToe.this,"A mark is already here, try other place",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){
                        board[2][3]=1;
                        mb23.setImageDrawable(getDrawable(R.drawable.ic_x));
                    }
                    else{
                        board[2][3]=2;
                        mb23.setImageDrawable(getDrawable(R.drawable.ic_y));
                    }
                    checkWin();
                }
            }
        });

        mb31 = (ImageButton) findViewById(R.id.ttt_31);
        mb31.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true){
                    Toast.makeText(TicTacToe.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else if ((board[3][1] != 0)) {
                    Toast.makeText(TicTacToe.this,"A mark is already here, try other place",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){
                        board[3][1]=1;
                        mb31.setImageDrawable(getDrawable(R.drawable.ic_x));
                    }
                    else{
                        board[3][1]=2;
                        mb31.setImageDrawable(getDrawable(R.drawable.ic_y));
                    }
                    checkWin();
                }
            }
        });

        mb32 = (ImageButton) findViewById(R.id.ttt_32);
        mb32.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true){
                    Toast.makeText(TicTacToe.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else if ((board[3][2] != 0)) {
                    Toast.makeText(TicTacToe.this,"A mark is already here, try other place",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){
                        board[3][2]=1;
                        mb32.setImageDrawable(getDrawable(R.drawable.ic_x));
                    }
                    else{
                        board[3][2]=2;
                        mb32.setImageDrawable(getDrawable(R.drawable.ic_y));
                    }
                    checkWin();
                }
            }
        });

        mb33 = (ImageButton) findViewById(R.id.ttt_33);
        mb33.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true){
                    Toast.makeText(TicTacToe.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else if ((board[3][3] != 0)) {
                    Toast.makeText(TicTacToe.this,"A mark is already here, try other place",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){
                        board[3][3]=1;
                        mb33.setImageDrawable(getDrawable(R.drawable.ic_x));
                    }
                    else{
                        board[3][3]=2;
                        mb33.setImageDrawable(getDrawable(R.drawable.ic_y));
                    }
                    checkWin();
                }
            }
        });

        //the Replay button
        Button mReplay = (Button) findViewById(R.id.ttt_replay);
        mReplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                resetBoard();
            }
        });

    }

    //to check if there is a winner or a tie game
    public void checkWin(){
        if (checkRow()==1 || checkColumn()==1 || checkDiagnol()==1){
            p1score++;
            result.setText(p1name+" wins!");
            p1s.setText(""+p1score);
            end=true;

        }
        if (checkRow()==2 || checkColumn()==2 || checkDiagnol()==2){
            p2score++;
            result.setText(p2name+" wins!");
            p2s.setText(""+p2score);
            end=true;
        }
        else { if (checkFull()){
            result.setText("Draw!");
            end=true;
            }
        }
    }

    //to check if the checkerboard is full
    public boolean checkFull(){
        for (int i=1; i<=3; i++){
            for (int j=1; j<=3; j++){
                if (board[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }

    public int checkRow(){
        for (int i = 1; i <= 3; i++) {
            if ((board[i][1]==1)&&(board[i][2]==1)&&board[i][3]==1) {
                return 1;
            }
            else if ((board[i][1]==2)&&(board[i][2]==2)&&board[i][3]==2) {
                return 2;
            }
        }
        return 0;
    }

    public int checkColumn(){
        for (int i = 1; i <= 3; i++) {
            if ((board[1][i]==1)&&(board[2][i]==1)&&board[3][i]==1) {
                return 1;
            }
            else if ((board[1][i]==2)&&(board[2][i]==2)&&board[3][i]==2) {
                return 2;
            }
        }
        return 0;
    }

    public int checkDiagnol(){
        if ((board[1][1]==1 && board[2][2]==1 && board[3][3]==1) ||
                (board[3][1]==1 && board[2][2]==1 && board[1][3]==1)) {
            return 1;
        }
        else if ((board[1][1]==2 && board[2][2]==2 && board[3][3]==2) ||
                (board[3][1]==2 && board[2][2]==2 && board[1][3]==2)) {
            return 2;
        }
        else {
            return 0;
        }
    }

    //reset the game
    public void resetBoard(){
        for (int i=1; i<=3; i++){
            for (int j=1; j<=3; j++){
                board[i][j]=0;
            }
        }

        turn=0;
        end=false;

        mb11.setImageDrawable(getDrawable(R.drawable.ic_action_name));
        mb12.setImageDrawable(getDrawable(R.drawable.ic_action_name));
        mb13.setImageDrawable(getDrawable(R.drawable.ic_action_name));
        mb21.setImageDrawable(getDrawable(R.drawable.ic_action_name));
        mb22.setImageDrawable(getDrawable(R.drawable.ic_action_name));
        mb23.setImageDrawable(getDrawable(R.drawable.ic_action_name));
        mb31.setImageDrawable(getDrawable(R.drawable.ic_action_name));
        mb32.setImageDrawable(getDrawable(R.drawable.ic_action_name));
        mb33.setImageDrawable(getDrawable(R.drawable.ic_action_name));

        result.setText("Game result will be shown here.");
    }

    //the back to home button (need to be clicked twice within 2.5s)
    public void backToHome (View view){
        if(backButtonClickedOnce){ //the second click, go back to main activity with extra putted
            Intent intent =new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.PLAYER1_NAME,p1name);
            intent.putExtra(MainActivity.PLAYER2_NAME,p2name);
            intent.putExtra(MainActivity.PLAYER1_SCORE,p1score);
            intent.putExtra(MainActivity.PLAYER2_SCORE,p2score);
            setResult(0, intent);
            finish();
        }
        this.backButtonClickedOnce = true; //the first click
        Toast.makeText(this, "Are you sure you wanna leave? Click again to confirm.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backButtonClickedOnce=false;
            }
        }, 2500); //if there is no second click within 2.5 seconds, reset the boolean
    }


}
