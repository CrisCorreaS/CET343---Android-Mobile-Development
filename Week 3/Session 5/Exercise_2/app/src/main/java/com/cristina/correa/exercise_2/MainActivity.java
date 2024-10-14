package com.cristina.correa.exercise_2;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    public static final String EXTRA_MESSAGE = "com.cristina.correa.exercise_2.MESSAGE";

    // Make this variables global
    EditText editText;
    Button button;
    Button buttonSms;
    Button buttonEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Instantiate buttons
        button = (Button) findViewById(R.id.button1);
        buttonSms = (Button) findViewById(R.id.button2);
        buttonEmail = (Button) findViewById(R.id.button3);

        // Create a listener for every button
        button.setOnClickListener(this);
        buttonSms.setOnClickListener(this);
        buttonEmail.setOnClickListener(this);

        //Instantiate edit text
        editText = (EditText) findViewById(R.id.editText1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String message = editText.getText().toString();

        if(id == R.id.button1){
            // Send message to Activity 2
            Intent intent = new Intent(MainActivity.this, Activity_2.class);
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        } else if(id == R.id.button2) {
            // Send message in SMS
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
            smsIntent.setData(Uri.parse("sms:")); // We make the data be sent as a sms
            smsIntent.putExtra("sms_body", message);

            // Now we have to check if the smsIntent can be resolved, sometimes people don't allow apps to send SMS or the phones don't have the capacity to send SMS
            if (smsIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(smsIntent);
            } else { // We have to use Toast because it's like a pop-up message that gives info to the user
                Toast.makeText(this, "No SMS app found", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Email the message
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
    }

}