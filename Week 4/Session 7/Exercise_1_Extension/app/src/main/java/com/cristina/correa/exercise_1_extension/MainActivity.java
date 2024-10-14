package com.cristina.correa.exercise_1_extension;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText billAmount;
    EditText numberOfPeople;

    Spinner spinner;

    TextView tipAmount;
    TextView totalAmount;
    TextView totalAmountPerPerson;

    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // We initiate the elements
        billAmount = (EditText) findViewById(R.id.billAmount);
        numberOfPeople = (EditText) findViewById(R.id.totalPeople);

        spinner = (Spinner) findViewById(R.id.spinner);

        tipAmount = (TextView) findViewById(R.id.tip);
        totalAmount = (TextView) findViewById(R.id.total);
        totalAmountPerPerson = (TextView) findViewById(R.id.totalAmountPerPerson);

        button = (Button) findViewById(R.id.button);

        // We initialize this to the first value
        int tipPercentage = 10;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberChecker(billAmount) && numberChecker(numberOfPeople)) {
                    try {
                        float bill = Float.parseFloat(billAmount.getText().toString());

                        // We get the spinner value
                        String selectedItem = spinner.getSelectedItem().toString();
                        int tipPercentage = Integer.parseInt(selectedItem.replace("%", ""));

                        doCalculations(bill, tipPercentage);
                    } catch (NumberFormatException e) {
                        resetEverything();
                    }
                } else{
                    resetEverything();
                }
            }
        });
    }


    // We generalise this method
    public boolean numberChecker(EditText text) {
        return numberFormatChecker(text) && Integer.parseInt(text.getText().toString()) > 0;
    }

    // We generalise this method for the bill and the person
    public boolean numberFormatChecker(EditText text) {
        return !text.getText().toString().isEmpty() && text.getText().toString().matches("\\d+(\\.\\d+)?");
    }

    public float calculateTip(float bill, int percentage) {
        return bill * percentage / 100;
    }

    public float calculateTotal(float bill, float tip) {
        return bill + tip;
    }

    // We add a calculateBillPerPerson
    public float calculateBillPerPerson(float total) {
        if (numberChecker(numberOfPeople) ) {
            return total / Integer.parseInt(numberOfPeople.getText().toString());
        }
        return -1;
    }

    // We modify this method
    public void doCalculations(float bill, int tip) {
        float totalTip = calculateTip(bill, tip);
        float totalBill = calculateTotal(bill, totalTip);

        if(calculateBillPerPerson(totalBill) != -1) {
            float totalBillPerPerson = calculateBillPerPerson(totalBill);

            tipAmount.setText(Float.toString(totalTip));
            totalAmount.setText(Float.toString(totalBill));
            totalAmountPerPerson.setText(Float.toString(totalBillPerPerson));
        } else{
            resetEverything();
        }
    }

    public void resetEverything() {
        tipAmount.setText("");
        totalAmount.setText("");
        billAmount.setText("");

        numberOfPeople.setText("");
        totalAmountPerPerson.setText("");

        // We reset the spinner to the first value
        spinner.setSelection(0);
    }
}