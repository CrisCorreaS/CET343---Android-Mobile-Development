package com.cristina.correa.exercise_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_2 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Extract the info from Main Activity
        String messageValue = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Instantiate text view
        textView = (TextView) findViewById(R.id.textView1);
        textView.setText(messageValue);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}