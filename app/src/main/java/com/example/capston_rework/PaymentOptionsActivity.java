package com.example.capston_rework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentOptionsActivity extends AppCompatActivity {

    ImageView carImageView; // ImageView to display the car image
    TextView carModelTextView; // TextView to display the car model name
    Button installmentButton, fullPaymentButton; // Buttons for payment options

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable Edge-to-Edge
        setContentView(R.layout.activity_payment_page);

        // Handling edge-to-edge window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the ImageView, TextView, and Buttons in the layout
        carImageView = findViewById(R.id.iv_car_image_level6);
        carModelTextView = findViewById(R.id.textView); // Assuming your TextView has the ID "textView"
        installmentButton = findViewById(R.id.installment_btn);
        fullPaymentButton = findViewById(R.id.fullpayment_btn);

        // Load the car image and model name from SharedPreferences and display them
        loadCarDetails();

        // Set onClickListeners for buttons
        installmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentOptionsActivity.this, "Installment option selected", Toast.LENGTH_SHORT).show();
                // Add logic for installment option here
                Intent intent = new Intent(PaymentOptionsActivity.this, BudgetInputActivity.class);
                startActivity(intent);
            }
        });

        fullPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentOptionsActivity.this, "Full Payment option selected", Toast.LENGTH_SHORT).show();
                // Add logic for full payment option here
                Intent intent = new Intent(PaymentOptionsActivity.this, ArmorLevelSelectionActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to load the car image and model name from SharedPreferences
    private void loadCarDetails() {
        SharedPreferences sharedPref = getSharedPreferences("AppData", MODE_PRIVATE);

        // Retrieve the car model name
        String carModelName = sharedPref.getString("CarModelName", "Unknown Model");
        carModelTextView.setText(carModelName); // Set the car model name in the TextView

        // Retrieve the car image as a Base64 string
        String carImageBase64 = sharedPref.getString("CarImage", null);

        if (carImageBase64 != null) {
            Bitmap carImageBitmap = stringToBitmap(carImageBase64);
            carImageView.setImageBitmap(carImageBitmap); // Set the decoded image to ImageView
        }
    }

    // Helper method to decode Base64 string to Bitmap
    private Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] decodedBytes = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
