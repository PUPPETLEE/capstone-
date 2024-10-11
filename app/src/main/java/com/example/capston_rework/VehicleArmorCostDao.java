package com.example.capston_rework;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface VehicleArmorCostDao {
    @Query("SELECT * FROM vehiclearmorcost WHERE vehicleModel=:vehicleModel LIMIT 1")
    VehicleArmorCost findVehicleModel(String vehicleModel);
    @Insert
    void insert(VehicleArmorCost users);

    @Query("SELECT vehicleModel FROM vehiclearmorcost WHERE vehicleModel='Ford Ranger' LIMIT 1")
    String isDatabaseEmpty();

}
