package com.android.calculator;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private double finalNumber = 0, finalResult = 0;
    private String operation, number;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (savedInstanceState!=null){
            binding.result.setText(savedInstanceState.getString(getString(R.string.saved_result_key),""));
            binding.history.setText(savedInstanceState.getString(getString(R.string.saved_history_key), ""));
            finalResult = Double.parseDouble(savedInstanceState.getString(getString(R.string.saved_result_key),"0"));
        }

        binding.clearButton.setOnClickListener(v -> {
            binding.history.setText("");
            binding.result.setText("");
            finalNumber = 0;
            finalResult = 0;
            number = "";
        });
        binding.backButton.setOnClickListener(v -> {
            if (number.length() > 1)
                number = number.substring(0, number.length() - 2);
            else number = "";
        });
        binding.equalButton.setOnClickListener(this);
        binding.plusButton.setOnClickListener(this);
        binding.minusButton.setOnClickListener(this);
        binding.divisionButton.setOnClickListener(this);
        binding.multiplicationButton.setOnClickListener(this);
        binding.remainButton.setOnClickListener(this);
        binding.zeroButton.setOnClickListener(this);
        binding.oneButton.setOnClickListener(this);
        binding.twoButton.setOnClickListener(this);
        binding.threeButton.setOnClickListener(this);
        binding.fourButton.setOnClickListener(this);
        binding.fiveButton.setOnClickListener(this);
        binding.sixButton.setOnClickListener(this);
        binding.sevenButton.setOnClickListener(this);
        binding.eightButton.setOnClickListener(this);
        binding.nineButton.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.saved_history_key), binding.history.getText().toString());
        outState.putString(getString(R.string.saved_result_key), binding.result.getText().toString());
    }

    @Override
    public void onClick(View v) {
        String historyText = binding.history.getText().toString();
        String input = ((MaterialButton) v).getText().toString();
        binding.history.setText(String.format("%s%s", historyText, input));
        if (input.charAt(0) > 47 && input.charAt(0) < 58) {
            finalNumber = finalNumber * 10 + Integer.parseInt(input);
        } else {
            if (finalResult != 0 || input.equals("=")) {
                finalResult = calculate();
                binding.result.setText((finalResult == (int) finalResult ? String.valueOf((int) finalResult) : String.valueOf(finalResult)));
            } else
                finalResult = finalNumber;
            finalNumber = 0;
            if (!input.equals("="))
                operation = input;
            else {
                operation = "";
                binding.history.setText(String.format("%s%s", binding.history.getText().toString(), (finalResult == (int) finalResult ? String.valueOf((int) finalResult) : String.valueOf(finalResult))));
            }
        }
    }

    private double calculate() {
        double result;
        switch (operation) {
            case "+":
                result = finalResult + finalNumber;
                break;
            case "-":
                result = finalResult - finalNumber;
                break;
            case "/":
                result = finalResult / finalNumber;
                break;
            case "×":
                result = finalResult * finalNumber;
                break;
            case "%":
                result = finalResult % finalNumber;
                break;
            default:
                result = finalResult;
                break;
        }
        return result;
    }
}