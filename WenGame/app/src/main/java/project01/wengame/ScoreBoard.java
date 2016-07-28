package project01.wengame;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import project01.wengame.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreBoard extends Fragment {
// has tried to use bundle here, but cannot solve some problems for updating the data on this fragment

    private TextView mPlayer1Name;
    private TextView mPlayer2Name;
    private TextView mPlayer1Score;
    private TextView mPlayer2Score;
//    Bundle args;

    private String p1n="Player 1";
    private String p2n="Player 2";
    private int p1s=0;
    private int p2s=0;

    public ScoreBoard() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_score_board, container, false);

//        mPlayer1Name = (TextView) view.findViewById(R.id.tv_player1_name);
//        mPlayer1Score = (TextView) view.findViewById(R.id.tv_player1_score);
//        mPlayer2Name = (TextView) view.findViewById(R.id.tv_player2_name);
//        mPlayer2Score = (TextView) view.findViewById(R.id.tv_player2_score);
//
//        Bundle args = getArguments();
//
//
//        if (args==null) {
//            mPlayer1Name.setText(p1n);
//            mPlayer1Score.setText(p1s);
//
//            mPlayer2Name.setText(p2n);
//            mPlayer2Score.setText(p2s);
//
//        }
//
//        else{
//            mPlayer1Name.setText(args.getString("player1name"));
//        }

        return view;

    }

}
