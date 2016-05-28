package com.sumukh.tictactoe;

import android.content.DialogInterface;

class DialogClickListener implements DialogInterface.OnClickListener {
    private MainActivity mainActivity;
    private TicTacToeGame ticTacToeGame;
    DialogClickListener(MainActivity a,TicTacToeGame ticTacToeGame){
        super();
        mainActivity = a;
        this.ticTacToeGame = ticTacToeGame;


    }
    @Override
    public void onClick(DialogInterface dialog, int which) {

        if(which == -1) {

            ticTacToeGame.clearBoard();
            mainActivity.updateBoard();

        }
        else if(which == -2)
            mainActivity.finish();

    }

}