package com.sumukh.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    DialogClickListener dlgListener;
    private TicTacToeGame ticTacToeGame;
    private HashMap<Integer, String> map = new HashMap<>();
    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ticTacToeGame = new TicTacToeGame();
        dlgListener = new DialogClickListener(this,ticTacToeGame);
        buildMap();

    }
    public void buildMap()
    {
        map.put(R.id.b1, "0,0");
        map.put(R.id.b2, "0,1");
        map.put(R.id.b3, "0,2");
        map.put(R.id.b4, "1,0");
        map.put(R.id.b5, "1,1");
        map.put(R.id.b6, "1,2");
        map.put(R.id.b7, "2,0");
        map.put(R.id.b8, "2,1");
        map.put(R.id.b9, "2,2");

    }

    public void click(View v) {
        //Get Row amd Column of button

    Log.i(LOG_TAG, "Message");
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
      Button button = (Button) v;
        String []row_col = map.get(button.getId()).split(",");
        //call function to set board values for User
        try {
            ticTacToeGame.setBoardValues(Integer.parseInt(row_col[0]), Integer.parseInt(row_col[1]), TicTacToeGame.USER_PLAY);

            ticTacToeGame.computerPlay();
            updateBoard();


        }
        catch (Exception e)
        {
            Toast.makeText(this, "Re-enter", Toast.LENGTH_SHORT).show();
//            return;
        }
        if(ticTacToeGame.gameOver())
        {

            if(ticTacToeGame.getWinner()==ticTacToeGame.USER_PLAY) {
//                Toast.makeText(this, "User Win", Toast.LENGTH_SHORT).show();
                dlgAlert.setMessage("You Win");
                dlgAlert.setTitle("Congratulations :)");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok", dlgListener);
                dlgAlert.setNegativeButton("Exit", dlgListener);
                dlgAlert.show();

            }
            else if(ticTacToeGame.getWinner()==ticTacToeGame.COMP_PLAY) {
//                Toast.makeText(this, "Comp Win", Toast.LENGTH_SHORT).show();
                dlgAlert.setMessage("Computer Wins!");
                dlgAlert.setTitle("PLAY AGAIN :(");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok", dlgListener);
                dlgAlert.setNegativeButton("Exit", dlgListener);
                dlgAlert.show();
            }
            else if(ticTacToeGame.checkBoard())
            {
//                Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                dlgAlert.setMessage("Play Again");
                dlgAlert.setTitle("DRAW");
                dlgAlert.setCancelable(true);
                dlgAlert.setPositiveButton("Ok", dlgListener);
//                dlgAlert.show();
                dlgAlert.setNegativeButton("Exit", dlgListener);
                dlgAlert.show();

            }
            return;

        }

    }


    public void updateBoard()
    {

        for(Integer buttonID: map.keySet())
        {
            Button button = (Button)findViewById(buttonID);
            String values[] = map.get(buttonID).split(",");
            int value = ticTacToeGame.getBoardValues(Integer.parseInt(values[0]), Integer.parseInt(values[1]));

            if(value == ticTacToeGame.USER_PLAY ) {
                button.setBackgroundColor(Color.WHITE);
                button.setText("X");

            }
            else if(value==ticTacToeGame.COMP_PLAY) {
                button.setText("O");
                button.setBackgroundColor(Color.YELLOW);
            }
            else
            {
                button.setText("");
                button.setBackgroundColor(Color.rgb(21, 71, 235));
//                button.setBackgroundColor(getColor(R.color.buttonOriginal));
            }

        }
    }

}
