package com.example.capston_rework;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.Editable;
import android.text.TextWatcher;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class BudgetInputActivity extends AppCompatActivity {

    ImageView iv_car_image;
    TextView tv_vehicle_name;
    EditText edt_budget;
    Button btn_submit;
    private boolean isUpdating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind views
        iv_car_image = findViewById(R.id.iv_car_image);
        tv_vehicle_name = findViewById(R.id.tv_vehicle_name);
        edt_budget = findViewById(R.id.edt_budget);
        btn_submit = findViewById(R.id.btn_submit);

        // Set input type for budget EditText to accept only numbers and decimal points
        edt_budget.setInputType(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);

        // Add TextWatcher for formatting input with commas and peso sign
        edt_budget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (isUpdating) {
                    return;
                }

                isUpdating = true;

                // Remove commas and peso sign before formatting
                String originalString = s.toString().replaceAll("[₱,]", "");
                if (!originalString.isEmpty()) {
                    try {
                        // Format the number with commas and add peso sign
                        double parsed = Double.parseDouble(originalString);
                        String formattedString = "₱" + NumberFormat.getNumberInstance(Locale.US).format(parsed);

                        // Update the EditText with the formatted number
                        edt_budget.setText(formattedString);
                        edt_budget.setSelection(edt_budget.getText().length());
                    } catch (NumberFormatException e) {
                        e.printStackTrace(); // Handle number format exception
                    }
                }

                isUpdating = false;
            }
        });

        // Retrieve shared preferences
        SharedPreferences sharedPref = getSharedPreferences("AppData", MODE_PRIVATE);
        String carModelName = sharedPref.getString("CarModelName", "No Model Detected");
        String carImageStr = sharedPref.getString("CarImage", "");

        // Set the retrieved data
        tv_vehicle_name.setText(carModelName);
        if (!carImageStr.isEmpty()) {
            iv_car_image.setImageBitmap(stringToBitmap(carImageStr));
        }

        // Submit button handling
        btn_submit.setOnClickListener(v -> {
            String budgetStr = edt_budget.getText().toString().replaceAll("[₱,]", ""); // Remove commas and peso sign before parsing
            if (budgetStr.isEmpty()) {
                Toast.makeText(BudgetInputActivity.this, "Please enter a budget.", Toast.LENGTH_SHORT).show();
                return;
            }

            double budget = Double.parseDouble(budgetStr);

            if (budget < 1000000) {
                Toast.makeText(BudgetInputActivity.this, "Not enough budget.", Toast.LENGTH_LONG).show();
            } else if (budget <= 1500000) {
                Toast.makeText(BudgetInputActivity.this, "Budget: ₱" + budget + " for " + carModelName, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(BudgetInputActivity.this, LevelFourBudgetActivity.class);
                intent.putExtra("level", "Level 4");
                intent.putExtra("budget", budget);
                intent.putExtra("carmodel", carModelName);
                startActivity(intent);
            } else if (budget <= 3000000) {
                Toast.makeText(BudgetInputActivity.this, "Budget: ₱" + budget + " for Level 6 materials and costs.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(BudgetInputActivity.this, LevelSixBudgetActivity.class);
                intent.putExtra("level", "Level 6");
                intent.putExtra("budget", budget);
                intent.putExtra("carmodel", carModelName);
                startActivity(intent);
            } else if (budget >= 6000000) {
                Toast.makeText(BudgetInputActivity.this, "Budget too high.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Bitmap stringToBitmap(String encodedString) {
        byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
