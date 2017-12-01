package com.example.yuqinghe.a4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class setting extends AppCompatActivity implements Observer {
    SimonModel model;
    TextView num;
    TextView level;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button easy;
    Button normal;
    Button hard;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        model = SimonModel.getInstance();
        model.addObserver(this);
        num = findViewById(R.id.view2_text1);
        level = findViewById(R.id.view2_text2);
        button1 = findViewById(R.id.view2_button1);
        button2 = findViewById(R.id.view2_button2);
        button3 = findViewById(R.id.view2_button3);
        button4 = findViewById(R.id.view2_button4);
        button5 = findViewById(R.id.view2_button5);
        button6 = findViewById(R.id.view2_button6);
        easy = findViewById(R.id.view2_easy);
        normal = findViewById(R.id.view2_normal);
        hard = findViewById(R.id.view2_hard);
        back = findViewById(R.id.view2_back);

        switch(model.getb()) {
            case 1:
                button1.setBackgroundColor(Color.YELLOW);
                break;
            case 2:
                button2.setBackgroundColor(Color.YELLOW);
                break;
            case 3:
                button3.setBackgroundColor(Color.YELLOW);
                break;
            case 4:
                button4.setBackgroundColor(Color.YELLOW);
                break;
            case 5:
                button5.setBackgroundColor(Color.YELLOW);
                break;
            case 6:
                button6.setBackgroundColor(Color.YELLOW);
                break;
        }

        switch(model.getdl()) {
            case 0:
                easy.setBackgroundColor(Color.YELLOW);
                break;
            case 1:
                normal.setBackgroundColor(Color.YELLOW);
                break;
            case 2:
                hard.setBackgroundColor(Color.YELLOW);
                break;
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(1);
                model.setb(1);
                reset();
                button1.setBackgroundColor(Color.YELLOW);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(2);
                model.setb(2);
                reset();
                button2.setBackgroundColor(Color.YELLOW);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(3);
                model.setb(3);
                reset();
                button3.setBackgroundColor(Color.YELLOW);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(4);
                model.setb(4);
                reset();
                button4.setBackgroundColor(Color.YELLOW);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setb(5);
                model.setButtons(5);
                reset();
                button5.setBackgroundColor(Color.YELLOW);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setButtons(6);
                model.setb(6);
                reset();
                button6.setBackgroundColor(Color.YELLOW);
            }
        });

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setTime(0);
                resetdl();
                model.setdl(0);
                easy.setBackgroundColor(Color.YELLOW);
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setTime(1);
                resetdl();
                model.setdl(1);
                normal.setBackgroundColor(Color.YELLOW);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setTime(2);resetdl();
                model.setdl(2);
                hard.setBackgroundColor(Color.YELLOW);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToWelcome();
            }
        });
        model.initObservers();

    }


    private void reset() {
        button1.setBackgroundColor(Color.LTGRAY);
        button2.setBackgroundColor(Color.LTGRAY);
        button3.setBackgroundColor(Color.LTGRAY);
        button4.setBackgroundColor(Color.LTGRAY);
        button5.setBackgroundColor(Color.LTGRAY);
        button6.setBackgroundColor(Color.LTGRAY);
    }

    private void resetdl() {
        easy.setBackgroundColor(Color.LTGRAY);
        normal.setBackgroundColor(Color.LTGRAY);
        hard.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.deleteObserver(this);
    }

    public void backToWelcome() {
        Intent intent = new Intent(this, welcomePage.class);
        startActivity(intent);
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
