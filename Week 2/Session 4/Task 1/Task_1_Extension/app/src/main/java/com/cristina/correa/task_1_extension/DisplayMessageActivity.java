package com.cristina.correa.task_1_extension;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Extract the info from Main Activity
        String messageValue = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String passwordValue = intent.getStringExtra(MainActivity.EXTRA_PASSWORD);
        String radioButtonValue = intent.getStringExtra(MainActivity.EXTRA_RADIOBUTTON);
        boolean checkboxValue = intent.getBooleanExtra(MainActivity.EXTRA_CHECKBOX, false); // We have to convert the String value to a boolean
        boolean switchValue = intent.getBooleanExtra(MainActivity.EXTRA_SWITCH, false);
        String spinnerValue = intent.getStringExtra(MainActivity.EXTRA_SPINNER);

        // Text we'll display in the textView element from displayMessageActivity.xml
        String displayText = "Message: " + messageValue +
                "\nPassword: " + passwordValue +
                "\nSelected RadioButton Option: " + radioButtonValue +
                "\nTerms accepted: " + checkboxValue +
                "\nLight mode On: " + switchValue +
                "\nSelected Spinner Option: " + spinnerValue;

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(displayText);
    }
}