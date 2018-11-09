package com.example.mylenovo.tictactoe;

public class Game {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;

    public Game() {
        // Create + fill board
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        movesPlayed = 0;
    }

    // Set new tilestate in board
    public TileState choose(int row, int column) {
         if (board[row][column] == TileState.BLANK) {
             if (playerOneTurn == true){
                 // Player x made a valid move
                 movesPlayed++;
                 board[row][column] = TileState.CROSS;
                 playerOneTurn = false;
                 return TileState.CROSS;
             } else {
                 // Player o made a valid move
                 movesPlayed++;
                 board[row][column] = TileState.CIRCLE;
                 playerOneTurn = true;
                 return TileState.CIRCLE;
             }
         }
        // Tile already filled
        return TileState.INVALID;
    }

    // Check current gamestate
    public GameState won() {
        for (int x=0; x<3; x++) {
            // horizontal check
            if ((board[x][0] ==  board[x][1]) && (board[x][1] == board[x][2])) {
                if (board[x][0] == TileState.CIRCLE) {
                    return GameState.PLAYER_TWO;
                } else if (board[x][0] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
            }
            // vertical check
            if ((board[0][x] ==  board[1][x]) && (board[1][x] == board[2][x])){
                if (board[0][x] == TileState.CIRCLE) {
                    return GameState.PLAYER_TWO;
                } else if (board[0][x] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
            }
        }
        // diagonal check
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

    public boolean playerOnTurn() {
        return playerOneTurn;
    }

    public void setPlayerOnTurn(Boolean bool) {
        playerOneTurn = bool;
    }


    public String getBoard(int x, int y) {
        return board[x][y].toString();
    }

    public void setBoard(int x, int y, String tile) {
        board[x][y] = TileState.valueOf(tile);
    }


    public int getMovesPlayed() {
        return movesPlayed;
    }

    public void setMovesPlayed(int moves) {
        movesPlayed = moves;
    }
}
