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

public class MainActivity2 extends AppCompatActivity {

    ImageView iv_car_image;
    TextView tv_vehicle_name;
    EditText edt_budget;
    Button btn_submit;

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

        // Set input type for budget EditText to accept only numbers
        edt_budget.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        // Set imeOptions to actionDone for the EditText
        edt_budget.setImeOptions(EditorInfo.IME_ACTION_DONE);

        // Retrieve shared preferences
        SharedPreferences sharedPref = getSharedPreferences("AppData", MODE_PRIVATE);
        String carModelName = sharedPref.getString("CarModelName", "No Model Detected");
        String carImageStr = sharedPref.getString("CarImage", "");

        // Set the retrieved data
        tv_vehicle_name.setText(carModelName);
        if (!carImageStr.isEmpty()) {
            iv_car_image.setImageBitmap(stringToBitmap(carImageStr));
        }

        // Set OnEditorActionListener to handle the Done button press
        edt_budget.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                // Hide the keyboard
                hideKeyboard();
                // Clear focus from the EditText
                edt_budget.clearFocus();
                return true; // Return true to indicate the action was handled
            }
            return false;
        });

        // Submit button handling
        btn_submit.setOnClickListener(v -> {
            String budgetStr = edt_budget.getText().toString();
            if (budgetStr.isEmpty()) {
                Toast.makeText(MainActivity2.this, "Please enter a budget.", Toast.LENGTH_SHORT).show();
                return;
            }

            double budget = Double.parseDouble(budgetStr);

            if (budget < 70000) {
                Toast.makeText(MainActivity2.this, "Not enough budget.", Toast.LENGTH_LONG).show();
            } else if (budget <= 150000) {
                Toast.makeText(MainActivity2.this, "Budget: " + budget + " for " + carModelName, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity2.this, ActivityLevelFour.class);
                intent.putExtra("level", "Level 4");
                intent.putExtra("budget", budget);
                startActivity(intent);
            } else if (budget <= 2500000) {
                Toast.makeText(MainActivity2.this, "Budget: " + budget + " for Level 6 materials and costs.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity2.this, ActivityLevelSix.class);
                intent.putExtra("level", "Level 6");
                intent.putExtra("budget", budget);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity2.this, "Budget too high.", Toast.LENGTH_LONG).show();
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
