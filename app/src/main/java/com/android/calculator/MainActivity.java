package com.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private double number = 0, finalResult = 0;
    private String operation;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.clearButton.setOnClickListener(v -> {
            binding.history.setText("");
            binding.result.setText("");
            number = 0;
            finalResult = 0;
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
    public void onClick(View v) {
        String historyText = binding.history.getText().toString();
        String input = ((MaterialButton) v).getText().toString();
        binding.history.setText(String.format("%s%s", historyText, input));
        if (input.charAt(0) > 47 && input.charAt(0) < 58) {
            number = number * 10 + Integer.parseInt(input);
        } else {
            if (finalResult != 0 || input.equals("=")) {
                finalResult = calculate();
                binding.result.setText((finalResult == (int) finalResult ? String.valueOf((int) finalResult) : String.valueOf(finalResult)));
            } else
                finalResult = number;
            number = 0;
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
                result = finalResult + number;
                break;
            case "-":
                result = finalResult - number;
                break;
            case "/":
                result = finalResult / number;
                break;
            case "×":
                result = finalResult * number;
                break;
            case "%":
                result = finalResult % number;
                break;
            default:
                result = finalResult;
                break;
        }
        return result;
    }
}