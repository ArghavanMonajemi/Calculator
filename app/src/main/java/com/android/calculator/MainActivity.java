package com.android.calculator;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private double finalResult = 0;
    private String operation, number = "";
    private final NumberFormat formatter = NumberFormat.getNumberInstance();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (savedInstanceState != null) {
            binding.result.setText(savedInstanceState.getString(getString(R.string.saved_result_key), ""));
            binding.calculation.setText(savedInstanceState.getString(getString(R.string.saved_history_key), ""));
            finalResult = Double.parseDouble(savedInstanceState.getString(getString(R.string.saved_result_key), "0"));
        }

        binding.clearButton.setOnClickListener(v -> {
            binding.calculation.setText("");
            binding.result.setText("");
            finalResult = 0;
            number = "";
        });
        binding.backButton.setOnClickListener(v -> {
            if (binding.result.getText().toString().isEmpty()||binding.calculation.getText().toString().contains("=")||number.isEmpty()||number.equals("0"))
                return;
            if (number.length()>1)
            number=number.substring(0,number.length()-1);
            else
                number="0";
            binding.result.setText(formatter.format(Double.parseDouble(number)));
        });
        binding.dotButton.setOnClickListener(v -> {
            if (!number.contains(".")) {
                if (number.isEmpty())
                    number = "0";
                number = number + ".";
                binding.calculation.setText(String.format("%s%s", binding.calculation.getText().toString(), number));
            }
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
        outState.putString(getString(R.string.saved_history_key), binding.calculation.getText().toString());
        outState.putString(getString(R.string.saved_result_key), binding.result.getText().toString());
    }

    @Override
    public void onClick(View v) {
        String input = ((MaterialButton) v).getText().toString();
        if (isNumber(input.charAt(0))) {
            number = number + input;
            binding.result.setText(formatter.format(Double.parseDouble(number)));
        } else {
            if (finalResult != 0 || input.equals("=")) {
                finalResult = calculate();
                binding.result.setText(formatter.format(finalResult));
            } else
                finalResult = Double.parseDouble(number);
            if (!input.equals("=")) {
                operation = input;
                binding.calculation.setText(String.format("%s%s", formatter.format(finalResult), operation));
            } else {
                operation = "";
                binding.calculation.setText(String.format("%s%s=", binding.calculation.getText().toString(), formatter.format(Double.parseDouble(number))));
            }
            number = "0";
        }
    }

    private boolean isNumber(char input) {
        return input > 47 && input < 58;
    }

    private double calculate() {
        double result;
        switch (operation) {
            case "+":
                result = finalResult + Double.parseDouble(number);
                break;
            case "-":
                result = finalResult - Double.parseDouble(number);
                break;
            case "/":
                result = finalResult / Double.parseDouble(number);
                break;
            case "×":
                result = finalResult * Double.parseDouble(number);
                break;
            case "%":
                result = finalResult % Double.parseDouble(number);
                break;
            default:
                result = finalResult;
                break;
        }
        return result;
    }
}