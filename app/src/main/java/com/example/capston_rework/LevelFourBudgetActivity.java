package com.example.capston_rework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class LevelFourBudgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_level_four_budget);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listViewCosting);
        TextView textView = findViewById(R.id.total);


        String carmodel = getIntent().getStringExtra("carmodel");
        double budget = getIntent().getDoubleExtra("budget", 0.0);  // Retrieve as double with default value



        VehicleModelDatabase database = VehicleModelDatabase.getVehicleModelDatabase(getApplicationContext());
        VehicleArmorCostDao vehicleArmorCostDao = database.vehicleArmorCostDao();

        new Thread(() -> {
            VehicleArmorCost vehicleArmorCost = vehicleArmorCostDao.findVehicleModel(carmodel);
            double total = vehicleArmorCost.getLvl4Total();
            String[] costingArr = vehicleArmorCost.getLvl4Costing();
            ArrayAdapter<String> costingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,costingArr);

            runOnUiThread(()->{
                Double ramainingBalance = total - budget;
                double halftOfRamaingBalance = ramainingBalance /2;

                listView.setAdapter(costingAdapter);
                DecimalFormat formatter = new DecimalFormat("#,###");
                textView.setText("Estimate Total: ₱" + formatter.format(total)+"\nBudget: ₱"+formatter.format(budget)+"\n\nTotal: ₱"+formatter.format(ramainingBalance)+"\nChoice of payment\n80% of work: ₱"+formatter.format(halftOfRamaingBalance)+"\n100% of work: ₱"+formatter.format(halftOfRamaingBalance));

            });
        }).start();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carmodel.equals("Ford Expedition")){
                    Toast.makeText(LevelFourBudgetActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(LevelSixSummaryActivity.this);
                }else if(carmodel.equals("Ford Ranger")){
                    Toast.makeText(LevelFourBudgetActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LevelFourBudgetActivity.this, FordRanger.class);
                    startActivity(intent);
                }else if(carmodel.equals("Hilux Conquest")){
                    Toast.makeText(LevelFourBudgetActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();

                }else if(carmodel.equals("Toyota Fortuner")){
                    Toast.makeText(LevelFourBudgetActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();

                }else if(carmodel.equals("Toyata LandCruiser")){
                    Toast.makeText(LevelFourBudgetActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(LevelFourBudgetActivity.this, LandCruiser.class);
                    //startActivity(intent);
                }else if(carmodel.equals("Ford Everest")){
                    Toast.makeText(LevelFourBudgetActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}