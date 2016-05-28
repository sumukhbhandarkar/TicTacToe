package com.sumukh.tictactoe;

import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by Sumukh on 16-05-2016.
 */
public class TicTacToeGame {

    private int winner = 0;
    public static int NUM_ROWS, NUM_COLS;
    public static int USER_PLAY, COMP_PLAY;
    private int board[][];

    TicTacToeGame(int n_rows, int n_cols) {
        NUM_ROWS = n_rows;
        NUM_COLS = n_cols;
        USER_PLAY = 5;
        COMP_PLAY = 2;
        initBoard();
    }

    TicTacToeGame() {

        this(3, 3);
    }

    public void initBoard()
    {
        board = new int[NUM_ROWS][NUM_COLS];
        for(int i = 0; i<NUM_ROWS; i++)
            for(int j=0; j<NUM_COLS; j++)
                board[i][j]=0;
    }

    public void setBoardValues(int row, int col, int value) throws IllegalAccessException{
        setWinner();
        if(checkEmpty(row,col) && !gameOver()) {
            System.out.println(gameOver());
            board[row][col] = value;
        }
    else if(gameOver())
        {
            return;
        }
        else
        {
            throw new IllegalAccessException("Error");
        }

    }
    public int getBoardValues(int row, int col)
    {
        return board[row][col];
    }

    public void computerPlay()throws IllegalAccessException {
        //   ViewGroup parent = (ViewGroup) findViewById(R.id.parentView);
        setWinner();
        String bestPosition = checkUserWin();
        if(bestPosition == null)
        {
            System.out.print("FULL");
            return;
        }
        String row_col[] = bestPosition.split(",");
        int row = Integer.parseInt(row_col[0]);
        int col = Integer.parseInt(row_col[1]);

        setBoardValues(row,col,COMP_PLAY);

    }

    public int getWinner()
    {
        if(rowSum()==USER_PLAY*NUM_ROWS || princiDiaSum()==USER_PLAY*NUM_ROWS || secDiaSum()==USER_PLAY*NUM_COLS||colSum()==USER_PLAY*NUM_ROWS)
            return USER_PLAY;
        else if(rowSum()==COMP_PLAY*NUM_ROWS || princiDiaSum()==COMP_PLAY*NUM_ROWS || secDiaSum()==COMP_PLAY*NUM_COLS||colSum()==COMP_PLAY*NUM_ROWS)
            return COMP_PLAY;
        else
            return 0;
    }

    public int rowSum()
    {
        int comp_l = COMP_PLAY*NUM_ROWS;
        int user_l = USER_PLAY*NUM_ROWS;
        int sum = 0;
        for(int i=0; i<NUM_ROWS; i++) {
            sum = 0;
            for (int j = 0; j < NUM_COLS; j++) {
                sum = sum + board[i][j];
            }
            if(sum==comp_l|| sum==user_l)
                return sum;
        }
        return sum;
    }

    public int princiDiaSum()
    {
        int sum = 0;
        for(int i =0 ; i<NUM_ROWS; i++)
        {
         //    sum = 0;
            for(int j=0; j<NUM_COLS; j++) {
                if (i == j)
                    sum = sum + board[i][j];

            }
        }
        return sum;
    }

    public int secDiaSum()
    {
        int sum = 0;
        int i, j;
        for(i=0; i<NUM_ROWS; i++)
        {
            for(j=0; j<NUM_COLS; j++)
            {
                if(i+j==NUM_ROWS-1)
                    sum = sum+board[i][j];
            }
        }
        return sum;
    }

    public boolean checkEmpty(int row, int col)
    {
        if(board[row][col]==0)
            return true;
        else return false;
    }

    public void printBoard()
    {
        for(int i=0; i<NUM_ROWS; i++) {
            System.out.println();
            for (int j = 0; j < NUM_COLS; j++)
                System.out.print(board[i][j] + ",");
        }
        System.out.println("---------");
    }

    public String checkUserWin()
    {
        int i, j;
        String testPosition = null;
        String bestPosition = null;
        if(!gameOver()) {
            for (i = 0; i < NUM_ROWS; i++)
                for (j = 0; j < NUM_COLS; j++) {

                    //Place user at every position and check for winner. If User likely to win, place comp
                    if (checkEmpty(i, j)) {
                        bestPosition = "" + i + "," + j;
                        //Check if Comp play can win
                        board[i][j] = COMP_PLAY;
//                    printBoard();
                        if (getWinner() == COMP_PLAY) {
                            return bestPosition;
                        } else {
                            board[i][j] = 0;
                        }
                        //Check if User can win
                        board[i][j] = USER_PLAY;
//                    printBoard();
                        if (getWinner() == USER_PLAY) {
                            board[i][j] = 0;
                            testPosition = bestPosition;
                        } else {
                            board[i][j] = 0;
                        }
                    }

                }
        }
        if(testPosition!=null)
        {
            bestPosition= testPosition;
        }
        return bestPosition;
    }

    public int colSum()
    {
        int comp_l = COMP_PLAY*NUM_COLS;
        int user_l = USER_PLAY*NUM_COLS;
        int sum = 0;
        for(int i=0; i<NUM_COLS; i++) {
            sum = 0;
            for (int j = 0; j < NUM_ROWS; j++) {
                sum = sum + board[j][i];
            }
            if(sum==comp_l|| sum==user_l)
                return sum;
        }
        return sum;
    }

    public void setWinner()
    {
        winner = getWinner();
    }

    public boolean gameOver()
    {
        if(winner==USER_PLAY || winner == COMP_PLAY || checkBoard())
            return true;
        else
            return false;

    }
    public boolean checkBoard()
    {
        int flag=0;
        for(int i = 0; i<NUM_ROWS; i++)
        {
            for(int j=0; j<NUM_COLS; j++)
            {
                if(board[i][j] == COMP_PLAY || board[i][j]==USER_PLAY)
                   flag++;
            }
        }
        if(flag==NUM_COLS*NUM_ROWS)
            return true;
        return false;
    }

    public void clearBoard()
    {
        for(int i = 0; i<NUM_ROWS; i++)
            for(int j=0; j<NUM_COLS; j++)
                board[i][j]=0;
    }


}


