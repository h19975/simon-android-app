package com.example.yuqinghe.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class welcomePage extends AppCompatActivity implements Observer {
    SimonModel model;
    TextView gamename;
    Button start;
    Button change;
    TextView instruction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        model = SimonModel.getInstance();
        model.addObserver(this);
        gamename = findViewById(R.id.view1_textView1);
        instruction = findViewById(R.id.view1_textView2);
        instruction.setText("follow the sequence played by computer, click each button in order. " +
                "Inside setting, you can change the difficult level.");
        gamename.setText("Simon Game");
        start = findViewById(R.id.view1_button1);
        start.setText("Start Game");
        change = findViewById(R.id.view1_button2);
        change.setText("Settings");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMain();
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSetting();
            }
        });

        model.initObservers();
    }


    public void launchMain () {
        Intent intent = new Intent(this, main.class);
        startActivity(intent);
    }

    public void launchSetting () {
        Intent intent = new Intent(this, setting.class);
        startActivity(intent);
    }

    @Override
    public void update (Observable o, Object arg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.deleteObserver(this);
    }

}
