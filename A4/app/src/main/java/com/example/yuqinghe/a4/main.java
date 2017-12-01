package com.example.yuqinghe.a4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class main extends AppCompatActivity implements Observer {

    SimonModel model;
    TextView score;
    TextView message;
    Button back;
    int size;
    Button [] buttons;
    Button start;
    TextView s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = SimonModel.getInstance();
        model.addObserver(this);

        size = model.getNumButtons();

        setContentView(R.layout.activity_main);

        back = (Button) findViewById(R.id.view3_back);
        score = (TextView) findViewById(R.id.view3_score);
        message = (TextView) findViewById(R.id.view3_message);
        start = (Button) findViewById(R.id.view3_start);
        s = findViewById(R.id.view3_s);

        buttons = new Button[6];
        final int[] bid = {
                R.id.view3_button1,
                R.id.view3_button2,
                R.id.view3_button3,
                R.id.view3_button4,
                R.id.view3_button5,
                R.id.view3_button6,
        };



        for (int i = 0; i < 6; i++) {
            buttons[i] = findViewById(bid[i]);
            buttons[i].setBackgroundColor(Color.LTGRAY);
            buttons[i].setEnabled(false);
            if (i < size) {
                buttons[i].setText(Integer.toString(i+1));
                buttons[i].setVisibility(View.VISIBLE);
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Button b = (Button) view;
                        b.setBackgroundColor(Color.YELLOW);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b.setBackgroundColor(Color.LTGRAY);
                                int num = Integer.parseInt(b.getText().toString());
                                boolean bool = model.verifyButton(num-1);
                                if (model.getStateAsString() == "LOSE") {
                                    message.setText("You lose. You can start a new game or go back to menu");
                                    model.newRound();
                                    score.setText("0");
                                    setBack(false);
                                }
                                else if (model.getStateAsString() == "WIN") {
                                    message.setText("You win! Press start game to continue");
                                    score.setText(Integer.toString(model.getScore()));
                                    setBack(false);
                                    model.newRound();
                                }
                                else {

                                }
                            }
                        }, 200);
                    }
                });
            }
            else {
                buttons[i].setVisibility(View.INVISIBLE);
            }
        }


        model.newRound();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.lose();
                backToWelcome();
            }

        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newRound();
            }
        });
    }

    private void newRound() {
        setBack(false);
        message.setText("Follow me!");
        if (model.getStateAsString() == "COMPUTER") {

            int index = model.nextButton();
            buttons[index].setBackgroundColor(Color.YELLOW);
            final Button b = buttons[index];

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    b.setBackgroundColor(Color.LTGRAY);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            newRound();
                        }
                    }, 500);
                }
            }, model.getWaitTime());
        } else {
            message.setText("It's your turn now!");
            setBack(true);
        }
    }

    public void backToWelcome() {
        Intent intent = new Intent(this, welcomePage.class);
        startActivity(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        model.deleteObserver(this);

    }

    private void setBack(boolean b) {
        for (int i = 0; i < size; i++) {
            buttons[i].setEnabled(b);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }


}
