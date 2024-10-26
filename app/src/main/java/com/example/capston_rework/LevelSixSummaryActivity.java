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

import java.text.DecimalFormat;


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
                DecimalFormat formatter = new DecimalFormat("#,###");

                textView.setText("Estimate Total: ₱"+ formatter.format(total));
            });
        }).start();

       Button button = findViewById(R.id.button);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(carmodel.equals("Ford Expedition")){
                   Toast.makeText(LevelSixSummaryActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();
                   //Intent intent = new Intent(LevelSixSummaryActivity.this);
               }else if(carmodel.equals("Ford Ranger")){
                   Toast.makeText(LevelSixSummaryActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(LevelSixSummaryActivity.this, FordRanger.class);
                   startActivity(intent);
               }else if(carmodel.equals("Hilux Conquest")){
                   Toast.makeText(LevelSixSummaryActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();

               }else if(carmodel.equals("Toyota Fortuner")){
                   Toast.makeText(LevelSixSummaryActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();

               }else if(carmodel.equals("Toyata LandCruiser")){
                   Toast.makeText(LevelSixSummaryActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();
                   //Intent intent = new Intent(LevelSixSummaryActivity.this, LandCruiser.class);
                   //startActivity(intent);
               }else if(carmodel.equals("Ford Everest")){
                   Toast.makeText(LevelSixSummaryActivity.this, "Opening 3d model ", Toast.LENGTH_LONG).show();
               }

           }
       });



    }
};