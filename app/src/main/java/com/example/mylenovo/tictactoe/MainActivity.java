package com.example.mylenovo.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Game game;
    // Create matrix with buttons ID
    int[][] coordinate = {{2131165220, 2131165222, 2131165223}, {2131165224, 2131165225, 2131165226}, {2131165227, 2131165228, 2131165229}};
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create new game
        game = new Game();

        // Initialize buttons + textview
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);
        text = (TextView)findViewById(R.id.text);

        text.setText("Players X turn");
    }

    // When clicked on tile-button
    public void tileClicked(View view) {
        Button button = (Button) view;
        int id = view.getId();

        // Game still in progress
        if (game.won() == GameState.IN_PROGRESS) {

            // Get buttons coordinates
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (coordinate[i][j] == id) {

                        // Get current TileState of clicked button + set new TileState
                        TileState state = game.choose(i, j);

                        switch (state) {
                            case CROSS:
                                // Player x made a valid move
                                button.setText("x");
                                text.setText("Players O turn");
                                break;
                            case CIRCLE:
                                // Player o made a valid move
                                button.setText("o");
                                text.setText("Players X turn");
                                break;
                            case INVALID:
                                break;
                        }
                    }
                }
            }
            // Change Textview message
            if (game.playerOnTurn() == true) {
                text.setText("Players X turn");
            } else {
                text.setText("Players O turn");
            }
        }
        // If game ended
        if (game.won() == GameState.PLAYER_ONE) {
            text.setText("Player X won!");
        } else if (game.won() == GameState.PLAYER_TWO) {
            text.setText("Player O won!");
        } else if (game.won() == GameState.DRAW) {
            text.setText("Draw!");
        }
    }

    // Reset game
    public void resetClicked(View view) {
        game = new Game();
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        text.setText("Players X turn");
    }

    // Safe current state of images in outstate
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Buttons
        outState.putString("button1", (String) button1.getText());
        outState.putString("button2", (String) button2.getText());
        outState.putString("button3", (String) button3.getText());
        outState.putString("button4", (String) button4.getText());
        outState.putString("button5", (String) button5.getText());
        outState.putString("button6", (String) button6.getText());
        outState.putString("button7", (String) button7.getText());
        outState.putString("button8", (String) button8.getText());
        outState.putString("button9", (String) button9.getText());

        // Player on turn
        outState.putBoolean("player", game.playerOnTurn());

        // Amount of moves played
        outState.putInt("moves", game.getMovesPlayed());

        // Board
        outState.putString("board1", game.getBoard(0, 0));
        outState.putString("board2", game.getBoard(0, 1));
        outState.putString("board3", game.getBoard(0, 2));
        outState.putString("board4", game.getBoard(1, 0));
        outState.putString("board5", game.getBoard(1, 1));
        outState.putString("board6", game.getBoard(1, 2));
        outState.putString("board7", game.getBoard(2, 0));
        outState.putString("board8", game.getBoard(2, 1));
        outState.putString("board9", game.getBoard(2, 2));
    }

    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        // Buttons
        button1.setText(inState.getString("button1"));
        button2.setText(inState.getString("button2"));
        button3.setText(inState.getString("button3"));
        button4.setText(inState.getString("button4"));
        button5.setText(inState.getString("button5"));
        button6.setText(inState.getString("button6"));
        button7.setText(inState.getString("button7"));
        button8.setText(inState.getString("button8"));
        button9.setText(inState.getString("button9"));

        // Player on turn
        game.setPlayerOnTurn(inState.getBoolean("player"));

        // Amount of moves played
        game.setMovesPlayed(inState.getInt("moves"));

        // Board
        game.setBoard(0, 0, inState.getString("board1"));
        game.setBoard(0, 1, inState.getString("board2"));
        game.setBoard(0, 2, inState.getString("board3"));
        game.setBoard(1, 0, inState.getString("board4"));
        game.setBoard(1, 1, inState.getString("board5"));
        game.setBoard(1, 2, inState.getString("board6"));
        game.setBoard(2, 0, inState.getString("board7"));
        game.setBoard(2, 1, inState.getString("board8"));
        game.setBoard(2, 2, inState.getString("board9"));

        // Textview
        if (game.playerOnTurn() == false) {
            text.setText("Players O turn");
        } else {
            text.setText("Players X turn");
        }
    }
}
