package com.cristina.correa.exercise_1_extension;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    Button resetButton;
    Button emailButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // We initiate the checkboxes, the texts and the button
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

        resetButton = (Button) findViewById(R.id.resetButton);
        emailButton = (Button) findViewById(R.id.emailButton);

        // We add the listeners
        checkBox1.setOnClickListener(this);
        checkBox2.setOnClickListener(this);
        checkBox3.setOnClickListener(this);
        checkBox4.setOnClickListener(this);
        checkBox5.setOnClickListener(this);

        resetButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);
    }

    @SuppressLint({"QueryPermissionsNeeded", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(allChecked() && allWritten()){
            // We can do -> Intent intent = new Intent(MainActivity.this, MainActivity.class); ... -> because all the activity looses it's data and is going to restart from 0
            textView.setText("CONGRATULATIONS!");

            if(id == R.id.emailButton){
                String message = "You have to do " + editText1.getText().toString() + ", " + editText2.getText().toString() + ", " + editText3.getText().toString() + ", " + editText4.getText().toString() + " and " + editText5.getText().toString();

                // Now the intent to send the mail
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822"); // With "message/rfc822" we indicate the media type -> it's an stardard format of an email message -> It includes the "From", "To", "Subject" and "Body" parts of the email, and we have to cover, at leas, some those fields
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"cristina@this.is.an.example"}); // Mail "From"
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is a message from my app"); // Mail subject
                emailIntent.putExtra(Intent.EXTRA_TEXT, message); // Mail body

                // We also have to check if the emailIntent can be resolved and if not use a Toast
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                } else {
                    Toast.makeText(this, "No Email app found", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // if not all the checkbox are checked, we vanish the text
            textView.setText("");
        }

        if(id == R.id.resetButton){
            resetTexts();
        }
    }

    public boolean allChecked() {
        return checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked() && checkBox5.isChecked();
    }

    public boolean allWritten(){
        return !editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty() && !editText3.getText().toString().isEmpty() && !editText4.getText().toString().isEmpty() && !editText5.getText().toString().isEmpty();
    }

    public void resetTexts(){
        EditText[] editTexts = {editText1, editText2, editText3, editText4, editText5};
        for (EditText item : editTexts) {
            item.setText("");
        }
    }
}