package project01.wengame;

import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ConnectFour extends AppCompatActivity {
    //saved player names and scores
    private int p1score;
    private int p2score;
    private String p1name;
    private String p2name;

    //the three TextViews that needs to be change during this activity
    private TextView result;
    private TextView p1s;
    private TextView p2s;

    private int turn=0; //to decide which player is playing
    private boolean end = false;  //to check if the game is ended
    private int[][] board= new int[5][5]; // our checkerboard for this game (empty:0, player1:1, player2:2)

    //the 5*5 checkerboard ImageViews
    private ImageView b00, b01, b02, b03, b04,
                      b10, b11, b12, b13, b14,
                      b20, b21, b22, b23, b24,
                      b30, b31, b32, b33, b34,
                      b40, b41, b42, b43, b44;

    //for double click the back to home button
    private boolean backButtonClickedOnce=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_four);

        TextView p1n = (TextView) findViewById(R.id.tv_player1_name);
        TextView p2n = (TextView) findViewById(R.id.tv_player2_name);
        TextView mfirst = (TextView) findViewById(R.id.tv_c4_first);
        TextView msecond = (TextView) findViewById(R.id.tv_c4_second);
        p1s = (TextView) findViewById(R.id.tv_player1_score);
        p2s = (TextView) findViewById(R.id.tv_player2_score);
        result = (TextView) findViewById(R.id.c4_result);

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
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                board[i][j]=0;
            }
        }

        //find all the imageViews
        b00=(ImageView) findViewById(R.id.c4_00);
        b01=(ImageView) findViewById(R.id.c4_01);
        b02=(ImageView) findViewById(R.id.c4_02);
        b03=(ImageView) findViewById(R.id.c4_03);
        b04=(ImageView) findViewById(R.id.c4_04);
        b10=(ImageView) findViewById(R.id.c4_10);
        b11=(ImageView) findViewById(R.id.c4_11);
        b12=(ImageView) findViewById(R.id.c4_12);
        b13=(ImageView) findViewById(R.id.c4_13);
        b14=(ImageView) findViewById(R.id.c4_14);
        b20=(ImageView) findViewById(R.id.c4_20);
        b21=(ImageView) findViewById(R.id.c4_21);
        b22=(ImageView) findViewById(R.id.c4_22);
        b23=(ImageView) findViewById(R.id.c4_23);
        b24=(ImageView) findViewById(R.id.c4_24);
        b30=(ImageView) findViewById(R.id.c4_30);
        b31=(ImageView) findViewById(R.id.c4_31);
        b32=(ImageView) findViewById(R.id.c4_32);
        b33=(ImageView) findViewById(R.id.c4_33);
        b34=(ImageView) findViewById(R.id.c4_34);
        b40=(ImageView) findViewById(R.id.c4_40);
        b41=(ImageView) findViewById(R.id.c4_41);
        b42=(ImageView) findViewById(R.id.c4_42);
        b43=(ImageView) findViewById(R.id.c4_43);
        b44=(ImageView) findViewById(R.id.c4_44);

        //set the 5 buttons
        ImageButton IB0 = (ImageButton) findViewById(R.id.c4_b0);
        IB0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true) {
                    Toast.makeText(ConnectFour.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){ //player 1
                        if (board[4][0]==0){
                            board[4][0]=1;
                            b40.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[3][0]==0){
                            board[3][0]=1;
                            b30.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[2][0]==0){
                            board[2][0]=1;
                            b20.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[1][0]==0){
                            board[1][0]=1;
                            b10.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[0][0]==0){
                            board[0][0]=1;
                            b00.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    else{ //player 2
                        if (board[4][0]==0){
                            board[4][0]=2;
                            b40.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[3][0]==0){
                            board[3][0]=2;
                            b30.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[2][0]==0){
                            board[2][0]=2;
                            b20.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[1][0]==0){
                            board[1][0]=2;
                            b10.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[0][0]==0){
                            board[0][0]=2;
                            b00.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    checkWin();
                }
            }
        });

        ImageButton IB1 = (ImageButton) findViewById(R.id.c4_b1);
        IB1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true) {
                    Toast.makeText(ConnectFour.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){ //player 1
                        if (board[4][1]==0){
                            board[4][1]=1;
                            b41.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[3][1]==0){
                            board[3][1]=1;
                            b31.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[2][1]==0){
                            board[2][1]=1;
                            b21.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[1][1]==0){
                            board[1][1]=1;
                            b11.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[0][1]==0){
                            board[0][1]=1;
                            b01.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    else{ //player 2
                        if (board[4][1]==0){
                            board[4][1]=2;
                            b41.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[3][1]==0){
                            board[3][1]=2;
                            b31.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[2][1]==0){
                            board[2][1]=2;
                            b21.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[1][1]==0){
                            board[1][1]=2;
                            b11.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[0][1]==0){
                            board[0][1]=2;
                            b01.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    checkWin();
                }
            }
        });

        ImageButton IB2 = (ImageButton) findViewById(R.id.c4_b2);
        IB2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true) {
                    Toast.makeText(ConnectFour.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){ //player 1
                        if (board[4][2]==0){
                            board[4][2]=1;
                            b42.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[3][2]==0){
                            board[3][2]=1;
                            b32.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[2][2]==0){
                            board[2][2]=1;
                            b22.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[1][2]==0){
                            board[1][2]=1;
                            b12.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[0][2]==0){
                            board[0][2]=1;
                            b02.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    else{ //player 2
                        if (board[4][2]==0){
                            board[4][2]=2;
                            b42.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[3][2]==0){
                            board[3][2]=2;
                            b32.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[2][2]==0){
                            board[2][2]=2;
                            b22.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[1][2]==0){
                            board[1][2]=2;
                            b12.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[0][2]==0){
                            board[0][2]=2;
                            b02.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    checkWin();
                }
            }
        });

        ImageButton IB3 = (ImageButton) findViewById(R.id.c4_b3);
        IB3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true) {
                    Toast.makeText(ConnectFour.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){ //player 1
                        if (board[4][3]==0){
                            board[4][3]=1;
                            b43.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[3][3]==0){
                            board[3][3]=1;
                            b33.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[2][3]==0){
                            board[2][3]=1;
                            b23.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[1][3]==0){
                            board[1][3]=1;
                            b13.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[0][3]==0){
                            board[0][3]=1;
                            b03.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    else{ //player 2
                        if (board[4][3]==0){
                            board[4][3]=2;
                            b43.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[3][3]==0){
                            board[3][3]=2;
                            b33.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[2][3]==0){
                            board[2][3]=2;
                            b23.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[1][3]==0){
                            board[1][3]=2;
                            b13.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[0][3]==0){
                            board[0][3]=2;
                            b03.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    checkWin();
                }
            }
        });

        ImageButton IB4 = (ImageButton) findViewById(R.id.c4_b4);
        IB4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (end==true) {
                    Toast.makeText(ConnectFour.this,"Game is over",Toast.LENGTH_SHORT).show();
                }else{
                    turn++;
                    if (turn % 2 ==1){ //player 1
                        if (board[4][4]==0){
                            board[4][4]=1;
                            b44.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[3][4]==0){
                            board[3][4]=1;
                            b34.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[2][4]==0){
                            board[2][4]=1;
                            b24.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[1][4]==0){
                            board[1][4]=1;
                            b14.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else if (board[0][4]==0){
                            board[0][4]=1;
                            b04.setImageDrawable(getDrawable(R.drawable.ic_pink));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    else{ //player 2
                        if (board[4][4]==0){
                            board[4][4]=2;
                            b44.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[3][4]==0){
                            board[3][4]=2;
                            b34.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[2][4]==0){
                            board[2][4]=2;
                            b24.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[1][4]==0){
                            board[1][4]=2;
                            b14.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else if (board[0][4]==0){
                            board[0][4]=2;
                            b04.setImageDrawable(getDrawable(R.drawable.ic_green));
                        } else{
                            Toast.makeText(ConnectFour.this,"This column is full, try other place",Toast.LENGTH_SHORT).show();
                            turn--;
                        }
                    }
                    checkWin();
                }
            }
        });

        //the Replay button
        Button mReplay = (Button) findViewById(R.id.c4_replay);
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
        else {if (checkFull()){
            result.setText("Draw!");
            end=true;
            }
        }
    }

    //to check if the checkerboard is full
    public boolean checkFull(){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if (board[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }

    public int checkRow(){
        for (int i=0; i<5; i++){
            if ((board[i][0]==1 && board[i][1]==1 && board[i][2]==1 && board[i][3]==1)||
                    (board[i][1]==1 && board[i][2]==1 && board[i][3]==1 && board[i][4]==1)){
                return 1;
            }
            else if ((board[i][0]==2 && board[i][1]==2 && board[i][2]==2 && board[i][3]==2)||
                    (board[i][1]==2 && board[i][2]==2 && board[i][3]==2 && board[i][4]==2)){
                return 2;
            }
        }
        return 0;
    }

    public int checkColumn(){
        for (int i=0; i<5; i++){
            if ((board[0][i]==1 && board[1][i]==1 && board[2][i]==1 && board[3][i]==1)||
                    (board[1][i]==1 && board[2][i]==1 && board[3][i]==1 && board[4][i]==1)){
                return 1;
            }
            else if ((board[0][i]==2 && board[1][i]==2 && board[2][i]==2 && board[3][i]==2)||
                    (board[1][i]==2 && board[2][i]==2 && board[3][i]==2 && board[4][i]==2)){
                return 2;
            }
        }
        return 0;
    }

    public int checkDiagnol(){
        if ((board[0][0]==1 && board[1][1]==1 && board[2][2]==1 && board[3][3]==1)||
                (board[4][4]==1 && board[1][1]==1 && board[2][2]==1 && board[3][3]==1)||
                (board[1][0]==1 && board[2][1]==1 && board[3][2]==1 && board[4][3]==1)||
                (board[0][1]==1 && board[1][2]==1 && board[2][3]==1 && board[3][4]==1)||
                (board[0][4]==1 && board[1][3]==1 && board[2][2]==1 && board[3][1]==1)||
                (board[1][3]==1 && board[2][2]==1 && board[3][1]==1 && board[4][0]==1)||
                (board[0][3]==1 && board[1][2]==1 && board[2][1]==1 && board[3][0]==1)||
                (board[1][4]==1 && board[2][3]==1 && board[3][2]==1 && board[4][1]==1)){
            return 1;
        }
        else if((board[0][0]==2 && board[1][1]==2 && board[2][2]==2 && board[3][3]==2)||
                (board[4][4]==2 && board[1][1]==2 && board[2][2]==2 && board[3][3]==2)||
                (board[1][0]==2 && board[2][1]==2 && board[3][2]==2 && board[4][3]==2)||
                (board[0][1]==2 && board[1][2]==2 && board[2][3]==2 && board[3][4]==2)||
                (board[0][4]==2 && board[1][3]==2 && board[2][2]==2 && board[3][1]==2)||
                (board[1][3]==2 && board[2][2]==2 && board[3][1]==2 && board[4][0]==2)||
                (board[0][3]==2 && board[1][2]==2 && board[2][1]==2 && board[3][0]==2)||
                (board[1][4]==2 && board[2][3]==2 && board[3][2]==2 && board[4][1]==2)){
            return 2;
        }
        else {
            return 0;
        }
    }

    //reset the game
    public void resetBoard(){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                board[i][j]=0;
            }
        }

        turn=0;
        end=false;

        b00.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b01.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b02.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b03.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b04.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b10.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b11.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b12.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b13.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b14.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b20.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b21.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b22.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b23.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b24.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b30.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b31.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b32.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b33.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b34.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b40.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b41.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b42.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b43.setImageDrawable(getDrawable(R.drawable.ic_blank));
        b44.setImageDrawable(getDrawable(R.drawable.ic_blank));

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
