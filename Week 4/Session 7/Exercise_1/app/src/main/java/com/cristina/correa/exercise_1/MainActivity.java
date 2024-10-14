
package com.cristina.correa.exercise_1;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText billAmount;

    Spinner spinner;

    TextView tipAmount;
    TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // We initiate the checkboxes and the texts
        billAmount = (EditText) findViewById(R.id.billAmount);

        spinner = (Spinner) findViewById(R.id.spinner);

        tipAmount = (TextView) findViewById(R.id.tip);
        totalAmount = (TextView) findViewById(R.id.total);

        // We initialize this to the first value
        int tipPercentage = 10;

        // Careful, here we don't need a onClick listener, we need a setOnItemSelected listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            /**
             * Here in the onItemSelected we need to explain many things:
             *      - AdapterView -> is a container that groups elements which can be selected
             *      - <?> -> we make it generic (we can use any type of element: spinner, textView...)
             *      - parent -> it's the reference of the spinner where we select an element. It has the data of the component
             *
             *          - AdapterView<?> parent -> contains the data of the spinner
             *
             *      - view -> is the reference to the spinner, represents the visual option that was selected
             *
             *      - position -> it's the index position of the selected element in the Adapter
             *
             *      - id -> identification of the selected element. In a spinner it's the same as the position
             * */
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (billAmountTipped()) {
                    try {
                        float bill = Float.parseFloat(billAmount.getText().toString());

                        // We obtain the selected item (the percentage)
                        String selectedItem = parent.getItemAtPosition(position).toString();

                        // We get the number without the "%" and we parse it to an int
                        int tipPercentage = Integer.parseInt(selectedItem.replace("%", ""));

                        doCalculations(bill, tipPercentage);
                    } catch (NumberFormatException e) {
                        resetEverything();
                    }
                } else{
                    resetEverything();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // We don't do anything here, but we need to override this method, it's in the interface signature
            }
        });

        billAmount.setOnKeyListener((v, keyCode, event) -> {
            // We have to check if a key is down and if the billAmount is okay
            if (event.getAction() == KeyEvent.ACTION_DOWN && billAmountTipped()) {
                try {
                    float bill = Float.parseFloat(billAmount.getText().toString());

                    doCalculations(bill, tipPercentage);
                } catch (NumberFormatException e) {
                    resetEverything();
                }
            }
            return false;
        });
    }


    public boolean billAmountTipped() {
        return billFormatChecker() && Integer.parseInt(billAmount.getText().toString()) > 0;
    }

    public boolean billFormatChecker() {
        // We're going to check that the bill is empty or is different than a number to avoid an exception
        return !billAmount.getText().toString().isEmpty() && billAmount.getText().toString().matches("\\d+(\\.\\d+)?");
    }

    public float calculateTip(float bill, int percentage) {
        return bill * percentage / 100;
    }

    public float calculateTotal(float bill, float tip) {
        return bill + tip;
    }

    public void doCalculations(float bill, int tip) {
        float totalTip = calculateTip(bill, tip);
        float totalBill = calculateTotal(bill, totalTip);

        tipAmount.setText(Float.toString(totalTip));
        totalAmount.setText(Float.toString(totalBill));
    }

    public void resetEverything() {
        tipAmount.setText("");
        totalAmount.setText("");
        billAmount.setText("");

        // We reset the spinner to the first value
        spinner.setSelection(0);
    }
}