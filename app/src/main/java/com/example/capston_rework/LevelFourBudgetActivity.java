package com.example.capston_rework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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


import java.text.DecimalFormat;

public class LevelFourBudgetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_level_four_budget);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listViewCosting);
        TextView textView = findViewById(R.id.total);

        textView.setTextColor(getResources().getColor(android.R.color.white)); // or #FFFFFF for white

        String carmodel = getIntent().getStringExtra("carmodel");
        double budget = getIntent().getDoubleExtra("budget", 0.0);  // Retrieve as double with default value



        VehicleModelDatabase database = VehicleModelDatabase.getVehicleModelDatabase(getApplicationContext());
        VehicleArmorCostDao vehicleArmorCostDao = database.vehicleArmorCostDao();

        new Thread(() -> {
            // Fetch vehicle data from the database
            VehicleArmorCost vehicleArmorCost = vehicleArmorCostDao.findVehicleModel(carmodel);
            double totalCost = vehicleArmorCost.getLvl4Total();
            String[] costingDetails = vehicleArmorCost.getLvl4Costing();

            // Use UI thread to update the UI elements
            runOnUiThread(() -> {
                // Calculate remaining balance and payment options
                double remainingBalance = totalCost - budget;
                double halfOfRemainingBalance = remainingBalance / 2;

                // Set up the ListView with a custom ArrayAdapter
                ArrayAdapter<String> costingAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView, costingDetails) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        // Inflate the custom list item layout
                        if (convertView == null) {
                            convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
                        }

                        // Get references to the column name and value TextViews
                        TextView columnName = convertView.findViewById(R.id.columnName);
                        TextView columnValue = convertView.findViewById(R.id.columnValue);

                        // Split the data into column name and value
                        String[] data = costingDetails[position].split(":"); // Example format: "ColumnName:Value"
                        columnName.setText(data[0].trim()); // Set column name
                        columnValue.setText(data[1].trim()); // Set value

                        return convertView;
                    }
                };

                listView.setAdapter(costingAdapter);

                // Format and display financial details
                DecimalFormat formatter = new DecimalFormat("#,###");
                textView.setText(
                        "Estimate Total: ₱" + formatter.format(totalCost) +
                                "\nBudget: ₱" + formatter.format(budget) +
                                "\n\nRemaining Balance: ₱" + formatter.format(remainingBalance) +
                                "\nChoice of Payment:" +
                                "\n80% of Work: ₱" + formatter.format(halfOfRemainingBalance) +
                                "\n100% of Work: ₱" + formatter.format(halfOfRemainingBalance)
                );
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