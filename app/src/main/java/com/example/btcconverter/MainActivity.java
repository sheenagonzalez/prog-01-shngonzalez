package com.example.btcconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText usdField;
    EditText btcField;
    TextView usdView;
    TextView btcView;
    Double usdAmount;
    Double btcAmount;
    Double conversionRate = 0.00010814;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usdField = (EditText) findViewById(R.id.usdField);
        btcField = (EditText) findViewById(R.id.btcField);
        usdView = (TextView) findViewById(R.id.usdView);
        btcView = (TextView) findViewById(R.id.btcView);

        class BtcTextWatcher implements TextWatcher {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    convertBtcToUsd();
                } else {
                    usdView.setText("Conversion");
                }
            }
        }

        class UsdTextWatcher implements TextWatcher {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    convertUsdToBtc();
                } else {
                    btcView.setText("Conversion");
                }
            }
        }
        usdField.addTextChangedListener(new UsdTextWatcher());
        btcField.addTextChangedListener(new BtcTextWatcher());

    }

    void convertBtcToUsd() {
        btcAmount = Double.parseDouble(btcField.getText().toString());
        usdAmount = btcAmount / conversionRate;

        usdView.setText(String.valueOf(usdAmount));
    }

    void convertUsdToBtc() {
        usdAmount = Double.parseDouble(usdField.getText().toString());
        btcAmount = usdAmount * conversionRate;

        btcView.setText(String.valueOf(btcAmount));
    }

}