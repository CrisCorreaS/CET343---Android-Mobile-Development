package com.cristina.correa.exercise_2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText billAmount;

    RadioGroup radioGroup;

    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;

    TextView tipAmount;
    TextView totalAmount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // We initiate the checkboxes and the texts
        billAmount = (EditText) findViewById(R.id.billAmount);

        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);

        tipAmount = (TextView) findViewById(R.id.tip);
        totalAmount = (TextView) findViewById(R.id.total);

        // We add the listeners
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);

        billAmount.setOnKeyListener((v, keyCode, event) -> {
            // We have to check if a key is down
            if (event.getAction() == KeyEvent.ACTION_DOWN && billAmountTipped() && radioButtonSelected()) {
                try { // We need a try catch because the we need to check the text can be converted to numbers
                    float bill = Float.parseFloat(billAmount.getText().toString());
                    int id = v.getId();

                    if (id == R.id.radioButton1) {
                        doCalculations(bill, 10);
                    } else if (id == R.id.radioButton2) {
                        doCalculations(bill, 15);
                    } else {
                        doCalculations(bill, 20);
                    }
                } catch (NumberFormatException e) {
                    resetEverything();  // Reset everything
                }
            }
            return false;
        });
    }

    @Override
    public void onClick(View v) {
        if(billFormatChecker()){
            if(billAmountTipped() && radioButtonSelected()){
                try { // We also add the try catch here
                    float bill = Float.parseFloat(billAmount.getText().toString());
                    int id = v.getId();

                    if(id == R.id.radioButton1){
                        doCalculations(bill, 10);
                    } else if(id == R.id.radioButton2){
                        doCalculations(bill, 15);
                    } else {
                        doCalculations(bill, 20);
                    }
                } catch (NumberFormatException e) {
                    resetEverything();
                }
            }
        } else{
            resetEverything();
        }
    }

    public boolean billAmountTipped() {
        return billFormatChecker() && Integer.parseInt(billAmount.getText().toString()) > 0;
    }

    public boolean billFormatChecker(){
        // We're going to check that the bill is empty or is different than a number to avoid an exception
        return !billAmount.getText().toString().isEmpty() && billAmount.getText().toString().matches("\\d+(\\.\\d+)?");
    }

    public boolean radioButtonSelected() { // Be careful, isChecked() is not the same as isSelected()
        return radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked();
    }

    public float calculateTip(float bill, int percentage) {
        return bill * percentage / 100;
    }

    public float calculateTotal(float bill, float tip){
        return bill + tip;
    }

    @SuppressLint("SetTextI18n")
    public void doCalculations(float bill, int tip){
        float totalTip = calculateTip(bill, tip);
        float totalBill = calculateTotal(bill, totalTip);

        tipAmount.setText(Float.toString(totalTip));
        totalAmount.setText(Float.toString(totalBill));
    }

    public void resetEverything(){
        radioGroup = findViewById(R.id.radioGroup); // We need to check the radioGroup and then unchecked with clearCheck()
        radioGroup.clearCheck();

        tipAmount.setText("");
        totalAmount.setText("");
        billAmount.setText("");
    }
}