
        package com.example.labexam;
        import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
        import android.widget.Toast;


        import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerCurrency;
    EditText editAmount, editResult;
    Button btnConvert;
    List<String> currencies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCurrency = findViewById(R.id.spinnerCurrency);
        editAmount = findViewById(R.id.editAmount);
        editResult = findViewById(R.id.editResult);
        btnConvert = findViewById(R.id.btnConvert);

        // Populate spinner with currencies
        currencies.add("USD");
        currencies.add("EUR");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapter);

        // Set click listener for Convert button
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });

        // Set click listener for number buttons
        ButtonClickListener buttonClickListener = new ButtonClickListener();
        findViewById(R.id.btn0).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn1).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn2).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn3).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn4).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn5).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn6).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn7).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn8).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn9).setOnClickListener(buttonClickListener);
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAmount.setText("");
            }
        });
    }

    private void convertCurrency() {
        String selectedCurrency = spinnerCurrency.getSelectedItem().toString();
        double amount = Double.parseDouble(editAmount.getText().toString());

        // Perform conversion
        double result;
        if (selectedCurrency.equals("USD")) {
            result = amount * 83.37; // Sample conversion rate for USD to INR
        } else if (selectedCurrency.equals("EUR")) {
            result = amount * 89.43; // Sample conversion rate for EUR to INR
        } else {
            // Handle unsupported currency
            Toast.makeText(MainActivity.this, "Unsupported currency", Toast.LENGTH_SHORT).show();
            return;
        }

        // Set the result in the EditText
        editResult.setText(String.valueOf(result));
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String currentAmount = editAmount.getText().toString();
            String digit = button.getText().toString();
            editAmount.setText(currentAmount + digit);
        }
    }
}