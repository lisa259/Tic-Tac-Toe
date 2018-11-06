package com.example.mylenovo.tictactoe;

public class Game {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
        movesPlayed = 0;
    }

    public TileState choose(int row, int column) {
         if (board[row][column] == TileState.BLANK) {
             if (playerOneTurn == true){
                 movesPlayed++;
                 board[row][column] = TileState.CROSS;
                 playerOneTurn = false;
                 return TileState.CROSS;
             } else {
                 movesPlayed++;
                 board[row][column] = TileState.CIRCLE;
                 playerOneTurn = true;
                 return TileState.CIRCLE;
             }
         }
        return TileState.INVALID;
    }

    public GameState won() {
        for (int x=0; x<3; x++) {
            // horizontaal checken
            if ((board[x][0] ==  board[x][1]) && (board[x][1] == board[x][2])) {
                if (board[x][0] == TileState.CIRCLE) {
                    return GameState.PLAYER_TWO;
                } else if (board[x][0] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
            }
            // verticaal checken
            if ((board[0][x] ==  board[1][x]) && (board[1][x] == board[2][x])){
                if (board[0][x] == TileState.CIRCLE) {
                    return GameState.PLAYER_TWO;
                } else if (board[0][x] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
            }
        }
        // diagonaal checken
        if (((board[0][0] ==  board[1][1]) && (board[1][1] == board[2][2])) || (board[2][0] ==  board[1][1]) && (board[1][1] == board[0][2])){
            if (board[1][1] == TileState.CIRCLE) {
                return GameState.PLAYER_TWO;
            } else if (board[1][1] == TileState.CROSS) {
                return GameState.PLAYER_ONE;
            }
        }
        // Draw
        if (movesPlayed == 9) {
            return GameState.DRAW;
        }
        return GameState.IN_PROGRESS;
    }
}
