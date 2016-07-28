package project01.wengame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HangMan extends AppCompatActivity {
    //the widgets that needs to be change during this activity
    private TextView p1s;
    private TextView p2s;
    private TextView result;
    private TextView mGuesser;
    private EditText etGuess;
    private TextView tv1,tv2,tv3,tv4,tv5;
    private ImageView p1life1, p1life2, p1life3, p1life4, p1life5, p1life6, p1life7, p1life8;
    private ImageView p2life1, p2life2, p2life3, p2life4, p2life5, p2life6, p2life7, p2life8;

    //saved player names and scores
    private int p1score;
    private int p2score;
    private String p1name;
    private String p2name;

    //words for guessing
    private String[][] words = {{"a","p","p","l","e"},{"m","a","n","g","o"},{"b","e","r","r","y"},
                                {"g","r","a","p","e"},{"p","e","a","c","h"},{"m","e","l","o","n"},
                                {"g","u","a","v","a"},{"l","e","m","o","n"}};

    //used to determine whether the character has already been guessed
    private ArrayList p1guesed = new ArrayList();
    private ArrayList p2guesed = new ArrayList();

    private int round=0; //each round has different word to guess
    private int turn=1;  //to determine which player's turn it is

    //to record how many times each player has guessed
    private int p1guess=0;
    private int p2guess=0;

    //each player's chances
    private int p1life=8;
    private int p2life=8;

    //for each character, to check whether each player has guessed correct or not
    private int p1c1,p1c2,p1c3,p1c4,p1c5 =0;
    private int p2c1,p2c2,p2c3,p2c4,p2c5 =0;

    private boolean end = false; //to check if the game is ended
    private boolean backButtonClickedOnce=false;//for double click the back to home button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man);

        TextView p1n = (TextView) findViewById(R.id.tv_player1_name);
        TextView p2n = (TextView) findViewById(R.id.tv_player2_name);
        mGuesser = (TextView) findViewById(R.id.tv_hm_guesser);
        p1s = (TextView) findViewById(R.id.tv_player1_score);
        p2s = (TextView) findViewById(R.id.tv_player2_score);
        result = (TextView) findViewById(R.id.hm_result);

        //get data passed from the main activity
        Intent intent = getIntent();
        if (intent!= null){
            p1name = intent.getStringExtra(MainActivity.PLAYER1_NAME);
            p2name = intent.getStringExtra(MainActivity.PLAYER2_NAME);
            p1n.setText(p1name);
            p2n.setText(p2name);
            mGuesser.setText(p1name);
            p1score = intent.getIntExtra(MainActivity.PLAYER1_SCORE,1);
            p2score = intent.getIntExtra(MainActivity.PLAYER2_SCORE,1);
            p1s.setText(""+p1score);
            p2s.setText(""+p2score);

        }

        p1life1=(ImageView) findViewById(R.id.hm_p1life1);
        p1life2=(ImageView) findViewById(R.id.hm_p1life2);
        p1life3=(ImageView) findViewById(R.id.hm_p1life3);
        p1life4=(ImageView) findViewById(R.id.hm_p1life4);
        p1life5=(ImageView) findViewById(R.id.hm_p1life5);
        p1life6=(ImageView) findViewById(R.id.hm_p1life6);
        p1life7=(ImageView) findViewById(R.id.hm_p1life7);
        p1life8=(ImageView) findViewById(R.id.hm_p1life8);
        p2life1=(ImageView) findViewById(R.id.hm_p2life1);
        p2life2=(ImageView) findViewById(R.id.hm_p2life2);
        p2life3=(ImageView) findViewById(R.id.hm_p2life3);
        p2life4=(ImageView) findViewById(R.id.hm_p2life4);
        p2life5=(ImageView) findViewById(R.id.hm_p2life5);
        p2life6=(ImageView) findViewById(R.id.hm_p2life6);
        p2life7=(ImageView) findViewById(R.id.hm_p2life7);
        p2life8=(ImageView) findViewById(R.id.hm_p2life8);

        tv1= (TextView) findViewById(R.id.hm_character1);
        tv2= (TextView) findViewById(R.id.hm_character2);
        tv3= (TextView) findViewById(R.id.hm_character3);
        tv4= (TextView) findViewById(R.id.hm_character4);
        tv5= (TextView) findViewById(R.id.hm_character5);

        etGuess = (EditText) findViewById(R.id.hm_et_guess);

        Button okButton = (Button) findViewById(R.id.hm_ok);
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if(end){ //the current round is end
                    Toast.makeText(HangMan.this, "Game is Over.", Toast.LENGTH_SHORT).show();
                }
                else {
                    String guessChar = etGuess.getText().toString();
                    int nthword = round % 8;

                    if (turn % 2 == 1) { //player 1
                        //first check whether this character has already been guessed
                        int used = p1guesed.indexOf(guessChar);
                        if (used != -1){ //already in the arraylist
                            Toast.makeText(HangMan.this, "You've already guessed this character, try another.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            p1guess++;
                            p1guesed.add(guessChar);

                            if (words[nthword][0].equals(guessChar)) {
                                tv1.setText(guessChar);
                                p1c1 = 1;
                            }
                            if (words[nthword][1].equals(guessChar)) {
                                tv2.setText(guessChar);
                                p1c2 = 1;
                            }
                            if (words[nthword][2].equals(guessChar)) {
                                tv3.setText(guessChar);
                                p1c3 = 1;
                            }
                            if (words[nthword][3].equals(guessChar)) {
                                tv4.setText(guessChar);
                                p1c4 = 1;
                            }
                            if (words[nthword][4].equals(guessChar)) {
                                tv5.setText(guessChar);
                                p1c5 = 1;
                            } else if ((!words[nthword][0].equals(guessChar)) &&
                                    (!words[nthword][1].equals(guessChar)) &&
                                    (!words[nthword][2].equals(guessChar)) &&
                                    (!words[nthword][3].equals(guessChar)) &&
                                    (!words[nthword][4].equals(guessChar))) {  //miss this guess
                                Toast.makeText(HangMan.this, "Miss one guess", Toast.LENGTH_SHORT).show();
                                if (p1life == 8)
                                    p1life8.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p1life == 7)
                                    p1life7.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p1life == 6)
                                    p1life6.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p1life == 5)
                                    p1life5.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p1life == 4)
                                    p1life4.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p1life == 3)
                                    p1life3.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p1life == 2)
                                    p1life2.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p1life == 1)
                                    p1life1.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                p1life--;
                            }
                        }

                    } else { //player 2
                        int used = p2guesed.indexOf(guessChar);
                        if (used != -1){ //already in the arraylist
                            Toast.makeText(HangMan.this, "You've already guessed this character, try another.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            p2guess++;
                            p2guesed.add(guessChar);
                            if (words[nthword][0].equals(guessChar)) {
                                tv1.setText(guessChar);
                                p2c1 = 1;
                            }
                            if (words[nthword][1].equals(guessChar)) {
                                tv2.setText(guessChar);
                                p2c2 = 1;
                            }
                            if (words[nthword][2].equals(guessChar)) {
                                tv3.setText(guessChar);
                                p2c3 = 1;
                            }
                            if (words[nthword][3].equals(guessChar)) {
                                tv4.setText(guessChar);
                                p2c4 = 1;
                            }
                            if (words[nthword][4].equals(guessChar)) {
                                tv5.setText(guessChar);
                                p2c5 = 1;
                            } else if ((!words[nthword][0].equals(guessChar)) &&
                                    (!words[nthword][1].equals(guessChar)) &&
                                    (!words[nthword][2].equals(guessChar)) &&
                                    (!words[nthword][3].equals(guessChar)) &&
                                    (!words[nthword][4].equals(guessChar))) {
                                Toast.makeText(HangMan.this, "Miss one guess", Toast.LENGTH_SHORT).show();
                                if (p2life == 8)
                                    p2life8.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p2life == 7)
                                    p2life7.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p2life == 6)
                                    p2life6.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p2life == 5)
                                    p2life5.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p2life == 4)
                                    p2life4.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p2life == 3)
                                    p2life3.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p2life == 2)
                                    p2life2.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                if (p2life == 1)
                                    p2life1.setImageDrawable(getDrawable(R.drawable.ic_life_end));
                                p2life--;
                            }
                        }

                    }
                    checkGuess();
                }
            }
        });

        Button mNewRound = (Button) findViewById(R.id.hm_replay);
        mNewRound.setOnClickListener(new View.OnClickListener(){ //reset everything, except round#
            @Override
            public void onClick (View v){
                resetWord();
                resetGuess();
                resetLife();
                end=false;
                turn=1;
            }
        });


    }

    //to check whether guess right or use up all lives
    //alse check the winner / tie game
    public void checkGuess(){
        if (turn % 2 ==1) {//player 1
            if (p1c1==1 && p1c2==1 && p1c3==1 && p1c4==1 && p1c5==1 && p1life>=0){
                Toast.makeText(HangMan.this, "You got it! With "+p1guess+" guesses.", Toast.LENGTH_SHORT).show();
                turn++;
                mGuesser.setText(p2name);
                resetWord();
            }
            else if(p1life==0){
                Toast.makeText(HangMan.this, "You've used up all your chances.", Toast.LENGTH_SHORT).show();
                p1guess=1000;
                turn++;
                mGuesser.setText(p2name);
                resetWord();
            }
        }
        else{  //after player2‘turn, check the winner of this round
            if (p2c1==1 && p2c2==1 && p2c3==1 && p2c4==1 && p2c5==1 && p2life>=0){
                Toast.makeText(HangMan.this, "You got it! With "+p1guess+" guesses.", Toast.LENGTH_SHORT).show();
                turn++;
                mGuesser.setText(p2name);
                resetWord();

                if (p1guess < p2guess){
                    result.setText(p1name+" wins!");
                    p1score++;
                    p1s.setText(""+p1score);
                    round++;
                    resetGuess();
                    end=true;
                }else if (p1guess > p2guess){
                    result.setText(p2name+" wins!");
                    p2score++;
                    p2s.setText(""+p2score);
                    round++;
                    resetGuess();
                    end=true;
                }
                else{
                    result.setText("Draw!");
                    round++;
                    resetGuess();
                    end=true;
                }
            }
            else if(p2life==0){
                Toast.makeText(HangMan.this, "You've used up all your chances.", Toast.LENGTH_SHORT).show();
                p2guess=1000;
                turn++;
                mGuesser.setText(p2name);
                resetWord();

                if (p1guess < p2guess){
                    result.setText(p1name+" wins!");
                    p1score++;
                    p1s.setText(""+p1score);
                    round++;
                    resetGuess();
                    end=true;
                }else if (p1guess > p2guess){
                    result.setText(p2name+" wins!");
                    p2score++;
                    p2s.setText(""+p2score);
                    round++;
                    resetGuess();
                    end=true;
                }
                else{
                    result.setText("Draw!");
                    round++;
                    resetGuess();
                    end=true;
                }
            }
        }
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

    public void resetWord(){
        tv1.setText("_ ");
        tv2.setText("_ ");
        tv3.setText("_ ");
        tv4.setText("_ ");
        tv5.setText("_ ");
        etGuess.setText("Enter your guess below:");
    }

    public void resetGuess(){
        p1c1=0;
        p1c2=0;
        p1c3=0;
        p1c4=0;
        p1c5=0;
        p2c1=0;
        p2c2=0;
        p2c3=0;
        p2c4=0;
        p2c5=0;
    }

    public void resetLife(){
        p1guess=0;
        p2guess=0;
        p1life=8;
        p2life=8;
        p1life1.setImageDrawable(getDrawable(R.drawable.ic_life));
        p1life2.setImageDrawable(getDrawable(R.drawable.ic_life));
        p1life3.setImageDrawable(getDrawable(R.drawable.ic_life));
        p1life4.setImageDrawable(getDrawable(R.drawable.ic_life));
        p1life5.setImageDrawable(getDrawable(R.drawable.ic_life));
        p1life6.setImageDrawable(getDrawable(R.drawable.ic_life));
        p1life7.setImageDrawable(getDrawable(R.drawable.ic_life));
        p1life8.setImageDrawable(getDrawable(R.drawable.ic_life));

        p2life1.setImageDrawable(getDrawable(R.drawable.ic_life));
        p2life2.setImageDrawable(getDrawable(R.drawable.ic_life));
        p2life3.setImageDrawable(getDrawable(R.drawable.ic_life));
        p2life4.setImageDrawable(getDrawable(R.drawable.ic_life));
        p2life5.setImageDrawable(getDrawable(R.drawable.ic_life));
        p2life6.setImageDrawable(getDrawable(R.drawable.ic_life));
        p2life7.setImageDrawable(getDrawable(R.drawable.ic_life));
        p2life8.setImageDrawable(getDrawable(R.drawable.ic_life));

    }

}
