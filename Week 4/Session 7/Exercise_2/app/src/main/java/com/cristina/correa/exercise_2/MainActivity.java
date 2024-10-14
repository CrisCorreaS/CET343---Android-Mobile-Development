package com.cristina.correa.exercise_2;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    // Array of the options for our alertDialog
    private String[] dialogOptions = {"Option A", "Option B", "Option C"};

    // We need to track the options chosen so we need to create an array of boleans with the same size of our options array
    private boolean[] selectedDialogOptions = new boolean[dialogOptions.length];

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        // We add the listeners
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.button1){
            /**
             * For a toast we need the context (the activity where we are), the message and the duration of the toast (it can be a number or Toast.LENGTH_SHORT or Toast.LENGTH_LONG)
             * We also add show(), because if not, the toast wouldn't be shown on the screen
             */
            Toast.makeText(this, "You have pressed a button.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.button2) {
            /**
             * For a custom toast, we need to create an XML file in layouts with the toast. In this case: custom_toast.xml
             * There we have to add the icon as a ImageView (the image has to be in res/drawable) and the styles as characteristics of the TextView
             */
            showCustomToast();
        } else if (id == R.id.button3) {
            // We have to call our function
            showDialogOptions();
        } else{
            showDialogInput();
        }
    }

    private void showCustomToast() {
        // We use the LayoutInflater to instanciate the xml layout of custom_toast.xml
        LayoutInflater layoutInflater = getLayoutInflater();
        View customToastView = layoutInflater.inflate(R.layout.custom_toast, null); // Here we say null because we usually write the parent (root) there, but as this is a MainClass, we don't need to do that

        // We create the toast
        Toast customToast = new Toast(getApplicationContext()); // getApplicationContext() gives us the contexts for the Toast
        customToast.setDuration(Toast.LENGTH_SHORT); // We set the duration of the toast

        customToast.setView(customToastView); // We set the customToastView as the content of the toast instead of the default toast layout
        customToast.show();
    }

    private void showDialogOptions() {
        // We build the dialog with AlertDialog.Builder and we add the context (this activity)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        /**
         * Now we have to build the structure of the dialog:
         *  - Title -> Gives the context of the dialog
         *  - Message -> Can be a message, a list of options, radio buttons, or even customizable like our custom toast
         *  - Buttons (optional) -> Can create an additional "action" that has to be positive, negative or neutral
         *
         * */

        // We create the tittle -> Choose Options
        builder.setTitle("Choose Options")
                /**
                 * We create the message -> we'll define the list of items in the dialog
                 *  - dialogOptions -> is the array of options we created
                 *  - selectedDialogOptions -> is the array that tracks our options
                 *  - DialogInterface.OnMultiChoiceClickListener() -> is the Interface that responds when a user clicks on an option
                 * */
                .setMultiChoiceItems(dialogOptions, selectedDialogOptions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    /**
                     * We create the onClick to react when the user clicks in an option
                     *   - dialog -> contains the list of options
                     *   - clickedOption -> is the index of the clicked option
                     *   - isChecked -> is a boolean that says whether the item is clicked or not
                     * */
                    public void onClick(DialogInterface dialog, int clickedOption, boolean isChecked) {
                        // We update the tracking array
                        selectedDialogOptions[clickedOption] = isChecked;
                    }
                })
                // Now we need to create the buttons: one for confirmation and other for negation
                /**
                 * In the confirmation button, we need to use setPositiveButton()
                 *   - We have the text of the button -> "Confirm"
                 *   - And also we add a onClick listener to be triggered when pressing it
                 * */
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int clickedOption) {
                        // We use the StringBuilder as a good practice instead of concatenate Strings
                        StringBuilder selectedItems = new StringBuilder("You selected: ");
                        boolean isAnyItemSelected = false;

                        // We loop through the options building a String that contains the clicked options
                        for (int i = 0; i < selectedDialogOptions.length; i++) {
                            if (selectedDialogOptions[i]) {
                                isAnyItemSelected = true;
                                selectedItems.append(dialogOptions[i]).append(", ");
                            }
                        }

                        String result = "";

                        // We add this to remove the last comma and space (", "), we add a final dot and we remove the last comma to add an "and" in order to improve the String
                        if (selectedItems.length() > 14) {
                            selectedItems.setLength(selectedItems.length() - 2);
                            selectedItems.append(".");

                            int lastComma = selectedItems.lastIndexOf(",");

                            result = selectedItems.toString();

                            if (lastComma != -1) {
                                selectedItems.replace(lastComma, lastComma + 1, " and");
                            }

                            result = selectedItems.toString();
                        } else{
                            result = "No options were selected.";
                        }

                        // We show the selected options with a toast
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                })
                /**
                 * In the negation button, we need to use setNegativeButton()
                 *   - We have the text of the button -> "Cancel"
                 *   - And also we add a onClick listener to be triggered when pressing it
                 * */
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // We close the dialog
                        dialog.dismiss();
                    }
                });

        // We create the dialog with all the information and we show it
        builder.create().show();
    }

    public void showDialogInput(){
        // We create a EditText where the user is going to type
        final EditText userInput = new EditText(MainActivity.this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Write something");

        // We create the message adding the EditText
        builder.setView(userInput);

        // We create the positive button
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int clickedOption) {
                // We get the user input from the EditText
                String userInputText = userInput.getText().toString();

                // We create the intent to go to another activity
                Intent intent = new Intent(MainActivity.this, Activity_2.class);
                intent.putExtra("user_text", userInputText);
                startActivity(intent);
            }
        });

        // We create the negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // We create the dialog with all the information and we show it
        builder.create().show();
    }

}