package com.example.capston_rework;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

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
                textView.setText("Estimate Total: ₱" + total+"\nBudget: ₱"+budget+"\n\nTotal: ₱"+ramainingBalance+"\nChoice of payment\n80% of work: ₱"+halftOfRamaingBalance+"\n100% of work: ₱"+halftOfRamaingBalance);

            });
        }).start();
    }
}