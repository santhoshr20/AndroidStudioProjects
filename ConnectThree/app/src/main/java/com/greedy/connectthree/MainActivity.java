package com.greedy.connectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winningpositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activeplayer = 0;
    boolean gameactive = true;

    public void dropin(View view){
        ImageView counter = (ImageView) view;
        Log.i("Tag",counter.getTag().toString());
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcounter]==2 && gameactive == true) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);

            if (activeplayer == 0 ) {

                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(7000).setDuration(300);

            for (int[] winningposition : winningpositions) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                    String winner = "";
                    gameactive = false;
                    if (activeplayer == 1) {
                        winner = "YELLOW";
                    } else if(activeplayer == 0) {
                        winner = "RED";
                    }
                    //Toast.makeText(this, winner + " has won!!", Toast.LENGTH_SHORT).show();
                    Button button = (Button) findViewById(R.id.button);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(winner + " has won!!");
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public  void  playAgain(View view){
        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        activeplayer = 0;
        gameactive = true;

        androidx.gridlayout.widget.GridLayout mygridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<mygridLayout.getChildCount(); i++){
            ((ImageView) mygridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
