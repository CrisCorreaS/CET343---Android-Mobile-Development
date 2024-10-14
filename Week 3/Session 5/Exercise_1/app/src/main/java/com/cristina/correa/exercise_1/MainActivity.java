package com.cristina.correa.exercise_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Instantiate button to point to button
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);

        //Instantiate edit text
        editText = (EditText) findViewById(R.id.editText1);

        //Instantiate text view
        textView = (TextView) findViewById(R.id.textView1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.button1){
            String name = editText.getText().toString();
            textView.setText(name);
        }
    }

}