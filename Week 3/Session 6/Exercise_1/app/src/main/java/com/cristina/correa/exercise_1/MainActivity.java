package com.cristina.correa.exercise_1;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;

    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // We initiate the checkboxes and the texts
        editText1 = (EditText) findViewById(R.id.item1);
        editText2 = (EditText) findViewById(R.id.item2);
        editText3 = (EditText) findViewById(R.id.item3);
        editText4 = (EditText) findViewById(R.id.item4);
        editText5 = (EditText) findViewById(R.id.item5);

        checkBox1 = (CheckBox) findViewById(R.id.chekbox1);
        checkBox2 = (CheckBox) findViewById(R.id.chekbox2);
        checkBox3 = (CheckBox) findViewById(R.id.chekbox3);
        checkBox4 = (CheckBox) findViewById(R.id.chekbox4);
        checkBox5 = (CheckBox) findViewById(R.id.chekbox5);

        textView = (TextView) findViewById(R.id.textView);

        // We add the listeners
        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBox5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(allChecked() && allWritten()){
            // We can do -> Intent intent = new Intent(MainActivity.this, MainActivity.class); ... -> because all the activity looses it's data and is going to restart from 0
            textView.setText("CONGRATULATIONS!");
        } else {
            // if not all the checkbox are checked, we vanish the text
            textView.setText("");
        }
    }

    public boolean allChecked() {
        return checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked() && checkBox5.isChecked();
    }

    public boolean allWritten(){
        return !editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty() && !editText3.getText().toString().isEmpty() && !editText4.getText().toString().isEmpty() && !editText5.getText().toString().isEmpty();
    }
}