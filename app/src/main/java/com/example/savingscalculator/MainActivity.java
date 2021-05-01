package com.example.savingscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declare elements
    final int WEEKS_IN_ONE_YEAR = 52;
    TextView txtTitle;
    EditText edtYearlyIncome;
    TextView txtWeeklySavings;
    SeekBar seekBar;
    Button btnReset;
    TextView txtTotalSavings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup fonts
        Typeface crimsonFont = Typeface.createFromAsset(getAssets(), "Fonts/CrimsonText-Italic.ttf");
        Typeface oswaldFont = Typeface.createFromAsset(getAssets(), "Fonts/Oswald-Bold.ttf");
        Typeface timmanaFont = Typeface.createFromAsset(getAssets(), "Fonts/Timmana-Regular.ttf");

        // Initialize elements
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtWeeklySavings = (TextView) findViewById(R.id.txtWeeklySavings);
        txtTotalSavings = (TextView) findViewById(R.id.txtTotalSavings);
        edtYearlyIncome = (EditText) findViewById(R.id.edtYearlyIncome);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        btnReset = (Button) findViewById(R.id.btnReset);

        // Apply fonts
        txtTitle.setTypeface(oswaldFont);
        txtWeeklySavings.setTypeface(crimsonFont);
        btnReset.setTypeface(timmanaFont);

         // Setup seekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Display changes on txtWeeklySavings
                txtWeeklySavings.setText("Weekly savings:\n$" + progress);

                // Calculate and display total savings
                int totalSavingsPerYear = progress * WEEKS_IN_ONE_YEAR;
                txtTotalSavings.setText("$" + totalSavingsPerYear);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // setup edit text
        edtYearlyIncome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Calculate max amoutn allowed on seekBar
                String yearlyIncomeAsText = edtYearlyIncome.getText().toString();
                double yearlyIncomeAsNumber = 0;

                // Validate entry
                if(!yearlyIncomeAsText.isEmpty()){
                    yearlyIncomeAsNumber = Double.parseDouble(yearlyIncomeAsText);
                }

                double weeklyIncome = yearlyIncomeAsNumber / WEEKS_IN_ONE_YEAR;
                int maxSavingsAllowed = (int) (weeklyIncome / 2);

                // Setup max amount on seekBar
                seekBar.setMax(maxSavingsAllowed);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset edit text
                edtYearlyIncome.setText(null);
                edtYearlyIncome.dispatchDisplayHint(View.VISIBLE);

                // Reset sekkBar
                seekBar.setProgress(0);
                seekBar.setMax(100);

                // Reset total savings
                txtTotalSavings.setText("TOTAL");
            }
        });

    }
}