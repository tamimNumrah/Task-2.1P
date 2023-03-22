package com.tamim.unitconverterapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner sourceSpinner=findViewById(R.id.sourceSpinner);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.length, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sourceSpinner.setAdapter(adapter);

        Spinner destinationSpinner=findViewById(R.id.destinationSpinner);
        ArrayAdapter<CharSequence>destinationAdapter=ArrayAdapter.createFromResource(this, R.array.length, android.R.layout.simple_spinner_item);
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        destinationSpinner.setAdapter(adapter);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_1:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_2:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radio_3:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }
}