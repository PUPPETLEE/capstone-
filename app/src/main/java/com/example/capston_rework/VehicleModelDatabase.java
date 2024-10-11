package com.example.capston_rework;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {VehicleArmorCost.class}, version = 1)
public abstract class VehicleModelDatabase extends RoomDatabase {
    private static VehicleModelDatabase vehicleModelDatabase;
    public static VehicleModelDatabase getVehicleModelDatabase(Context context){
        if(vehicleModelDatabase == null){
            vehicleModelDatabase = Room.databaseBuilder(context,
                    VehicleModelDatabase.class, "vehiclemodeldb").build();
        }

        return vehicleModelDatabase;
    }
    public abstract VehicleArmorCostDao vehicleArmorCostDao();
}
