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

public class LevelSixSummaryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_level_six_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listViewCosting);

        String carmodel = getIntent().getStringExtra("carmodel");
        TextView textView = findViewById(R.id.total);


        VehicleModelDatabase database = VehicleModelDatabase.getVehicleModelDatabase(getApplicationContext());
        VehicleArmorCostDao vehicleArmorCostDao = database.vehicleArmorCostDao();

        new Thread(() -> {
            VehicleArmorCost vehicleArmorCost = vehicleArmorCostDao.findVehicleModel(carmodel);
            double total = vehicleArmorCost.getLvl6Total();

            String[] costingArr = vehicleArmorCost.getLvl6Costing();
            ArrayAdapter<String> costingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,costingArr);

            runOnUiThread(()->{
                listView.setAdapter(costingAdapter);
                textView.setText("Estimate Total: ₱"+ total);
            });
        }).start();

    }
};