package com.example.capston_rework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArmorLevelSelectionActivity extends AppCompatActivity {


    Button levelFour, levelSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_armor_level_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind views
        levelFour = findViewById(R.id.btn_levelfour);
        levelSix = findViewById(R.id.btn_levelsix);

        // Retrieve shared preferences
        SharedPreferences sharedPref = getSharedPreferences("AppData", MODE_PRIVATE);
        String carModelName = sharedPref.getString("CarModelName", "No Model Detected");
        String carImageStr = sharedPref.getString("CarImage", "");
        levelFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ArmorLevelSelectionActivity.this, "Level 4 option selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ArmorLevelSelectionActivity.this, LevelFourSummaryActivity.class);
                intent.putExtra("level", "Level 4");
                intent.putExtra("carmodel", carModelName);
                startActivity(intent);
            }
        });

        levelSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ArmorLevelSelectionActivity.this, "Level 6 option selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ArmorLevelSelectionActivity.this, LevelSixSummaryActivity.class);
                intent.putExtra("level", "Level 6");
                intent.putExtra("carmodel", carModelName);
                startActivity(intent);


            }
        });


    }
}