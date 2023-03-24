package com.tamim.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner sourceSpinner;
    Spinner destinationSpinner;
    RadioButton length;
    RadioButton weight;
    RadioButton temp;
    EditText sourceNumber;
    TextView resultTextView;
    Button convertButton;
    CONVERT_TYPE type = CONVERT_TYPE.LENGTH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sourceSpinner = findViewById(R.id.sourceSpinner);
        destinationSpinner = findViewById(R.id.destinationSpinner);
        length = findViewById(R.id.radio_1);
        weight = findViewById(R.id.radio_2);
        temp = findViewById(R.id.radio_3);
        sourceNumber = findViewById(R.id.sourceNumber);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);
        refreshSelection();
    }
    public void onConvertButtonClicked(View view) {
        String sourceString = sourceNumber.getText().toString();
        if (sourceString.matches("")) {
            showToast("You did not enter a source unit");
            return;
        }
        double sourceValue = Double.valueOf(sourceString);
        String sourceUnit = sourceSpinner.getSelectedItem().toString();
        String destinationUnit = destinationSpinner.getSelectedItem().toString();
        Double result;
        switch (type) {
            case LENGTH:
                Double cmValue = convertToCM(sourceValue, sourceUnit);
                result = convertFromCM(cmValue, destinationUnit);
                resultTextView.setText("Result: " + result.toString() + " " + destinationUnit);
                break;
            case WEIGHT:
                Double kgValue = convertToKG(sourceValue, sourceUnit);
                result = convertFromKG(kgValue, destinationUnit);
                resultTextView.setText("Result: " + result.toString() + " " + destinationUnit);
                break;
            case TEMP:
                Double celsiusValue = convertToCelsius(sourceValue, sourceUnit);
                result = convertFromCelsius(celsiusValue, destinationUnit);
                resultTextView.setText("Result: " + result.toString() + " " + destinationUnit);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_1:
                if (checked)
                    type = CONVERT_TYPE.LENGTH;
                    break;
            case R.id.radio_2:
                if (checked)
                    type = CONVERT_TYPE.WEIGHT;
                    break;
            case R.id.radio_3:
                if (checked)
                    type = CONVERT_TYPE.TEMP;
                    break;
        }
        refreshSelection();
    }

    private void refreshSelection() {
        int contentArray;
        switch (type) {
            case LENGTH:
                contentArray = R.array.length;
                break;
            case WEIGHT:
                contentArray = R.array.weight;
                break;
            case TEMP:
                contentArray = R.array.temp;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, contentArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sourceSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence>destinationAdapter=ArrayAdapter.createFromResource(this, contentArray, android.R.layout.simple_spinner_item);
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        destinationSpinner.setAdapter(adapter);
        sourceNumber.setText(null);
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private double convertToCM(double value, String unit) {
       if  (unit.equals("Inch")) {
           return value * 2.54;
       } else if (unit.equals("Foot")) {
           return value * 30.48;
       } else if (unit.equals("Yard")) {
           return value * 91.44;
       } else if (unit.equals("Mile")) {
           return value * 100000 * 1.60934;
       } else if (unit.equals("Centimeter")) {
           return value;
       } else if (unit.equals("Kilometer")) {
           return value * 100000;
       }
        return 0;
    }
    private double convertFromCM(double value, String toUnit) {
        if  (toUnit.equals("Inch")) {
            return value / 2.54;
        } else if (toUnit.equals("Foot")) {
            return value / 30.48;
        } else if (toUnit.equals("Yard")) {
            return value / 91.44;
        } else if (toUnit.equals("Mile")) {
            return value / (100000 * 1.60934);
        } else if (toUnit.equals("Centimeter")) {
            return value;
        } else if (toUnit.equals("Kilometer")) {
            return value / 100000;
        }
        return 0;
    }
    private double convertToKG(double value, String unit) {
        if  (unit.equals("Pound")) {
            return value * 0.453592;
        } else if (unit.equals("KG")) {
            return value;
        } else if (unit.equals("Ounce")) {
            return value * 28.3495;
        } else if (unit.equals("Ton")) {
            return value * 907.185;
        }
        return 0;
    }
    private double convertFromKG(double value, String toUnit) {
        if  (toUnit.equals("Pound")) {
            return value / 0.453592;
        } else if (toUnit.equals("KG")) {
            return value;
        } else if (toUnit.equals("Ounce")) {
            return value / 28.3495;
        } else if (toUnit.equals("Ton")) {
            return value / 907.185;
        }
        return 0;
    }
    private double convertToCelsius(double value, String unit) {
        if  (unit.equals("Celsius")) {
            return value;
        } else if (unit.equals("Fahrenheit")) {
            return (value-32)/1.8;
        } else if (unit.equals("Kelvin")) {
            return value - 273.15;
        }
        return 0;
    }
    private double convertFromCelsius(double value, String toUnit) {
        if  (toUnit.equals("Celsius")) {
            return value;
        } else if (toUnit.equals("Fahrenheit")) {
            return (value*1.8)+32;
        } else if (toUnit.equals("Kelvin")) {
            return value + 273.15;
        }
        return 0;
    }
}