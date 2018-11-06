package com.example.mylenovo.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Game game;
    int[][] coordinate = {{2131165220, 2131165222, 2131165223}, {2131165224, 2131165225, 2131165226}, {2131165227, 2131165228, 2131165229}};
//    Button button1 = (Button)findViewById(R.id.button1);
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);
    }

    public void tileClicked(View view) {
        Button button = (Button) view;
        int id = view.getId();

        if (game.won() == GameState.IN_PROGRESS) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (coordinate[i][j] == id) {
                        TileState state = game.choose(i, j);

                        switch (state) {
                            case CROSS:
                                // do something
                                button.setText("x");
                                break;
                            case CIRCLE:
                                button.setText("o");
                                // do something
                                break;
                            case INVALID:
                                // do something different
                                break;
                        }
                    }
                }
            }
        }
    }

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
    }

    // Safe current state of images in outstate
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); // always call super
        outState.putString("button1", (String) button1.getText());
        outState.putString("button2", (String) button2.getText());
        outState.putString("button3", (String) button3.getText());
        outState.putString("button4", (String) button4.getText());
        outState.putString("button5", (String) button5.getText());
        outState.putString("button6", (String) button6.getText());
        outState.putString("button7", (String) button7.getText());
        outState.putString("button8", (String) button8.getText());
        outState.putString("button9", (String) button9.getText());
    }

    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        button1.setText(inState.getString("button1"));
        button2.setText(inState.getString("button2"));
        button3.setText(inState.getString("button3"));
        button4.setText(inState.getString("button4"));
        button5.setText(inState.getString("button5"));
        button6.setText(inState.getString("button6"));
        button7.setText(inState.getString("button7"));
        button8.setText(inState.getString("button8"));
        button9.setText(inState.getString("button9"));
    }
}
