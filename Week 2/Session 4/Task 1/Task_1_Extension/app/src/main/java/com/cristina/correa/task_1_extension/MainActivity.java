package com.cristina.correa.task_1_extension;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_PASSWORD = "com.example.myfirstapp.PASSWORD";
    public static final String EXTRA_RADIOBUTTON = "com.example.myfirstapp.RADIO";
    public static final String EXTRA_CHECKBOX = "com.example.myfirstapp.CHECKBOX";
    public static final String EXTRA_SWITCH = "com.example.myfirstapp.SWITCH";
    public static final String EXTRA_SPINNER = "com.example.myfirstapp.SPINNER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        /** Get regular text */
        EditText editText1 = (EditText) findViewById(R.id.editText);
        String messageText = editText1.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, messageText);

        /** Get password text */
        EditText editText2 = (EditText) findViewById(R.id.editPassword);
        intent.putExtra(EXTRA_PASSWORD, editText2.getText().toString());

        /** Get radiogroup info */
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        String radioButtonValue = "";

        if(selectedRadioButtonId == R.id.radioButton1){
            radioButtonValue = "Option 1";
        } else{
            radioButtonValue = "Option 2";
        }
        intent.putExtra(EXTRA_RADIOBUTTON, radioButtonValue);

        /** Get checkbox info */
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        intent.putExtra(EXTRA_CHECKBOX, checkBox.isChecked());

        /** Get switch info */
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        intent.putExtra(EXTRA_SWITCH, switch1.isChecked());

        /** Get spinner info */
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        intent.putExtra(EXTRA_SPINNER, spinner.getSelectedItem().toString());

        startActivity(intent); // Here we need to start the new activity
    }
}