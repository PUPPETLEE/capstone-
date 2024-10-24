package com.example.capston_rework;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

@Entity
public class VehicleArmorCost {
    @PrimaryKey @NonNull
    String vehicleModel;
    @ColumnInfo(name = "priceOfVehicle4")
    double priceOfVehicle4;
    @ColumnInfo(name = "armorPlate4")
    double armorPlate4;
    @ColumnInfo(name = "armoredGlass4")
    double armoredGlass4;
    @ColumnInfo(name = "runFlat4")
    double runFlat4;
    @ColumnInfo(name = "vanDoorLock4")
    double vanDoorLock4;
    @ColumnInfo(name = "upholster4")
    double upholster4;
    @ColumnInfo(name = "bodyPainting4")
    double bodyPainting4;
    @ColumnInfo(name = "underChassis4")
    double underChassis4;
    @ColumnInfo(name = "doorHinges4")
    double doorHinges4;
    @ColumnInfo(name = "vanDoorHinges4")
    double vanDoorHinges4;
    @ColumnInfo(name = "electricianMechanic4")
    double electricianMechanic4;
    @ColumnInfo(name = "armorInstallation4")
    double armorInstallation4;
    @ColumnInfo(name = "equipment4")
    double equipment4;
    @ColumnInfo(name = "customTax4")
    double customTax4;
    @ColumnInfo(name = "priceOfVehicle6")
    double priceOfVehicle6;
    @ColumnInfo(name = "armorPlate6")
    double armorPlate6;
    @ColumnInfo(name = "armoredGlass6")
    double armoredGlass6;
    @ColumnInfo(name = "runFlat6")
    double runFlat6;
    @ColumnInfo(name = "vanDoorLock6")
    double vanDoorLock6;
    @ColumnInfo(name = "upholster6")
    double upholster6;
    @ColumnInfo(name = "bodyPainting6")
    double bodyPainting6;
    @ColumnInfo(name = "underChassis6")
    double underChassis6;
    @ColumnInfo(name = "doorHinges6")
    double doorHinges6;
    @ColumnInfo(name = "vanDoorHinges6")
    double vanDoorHinges6;
    @ColumnInfo(name = "electricianMechanic6")
    double electricianMechanic6;
    @ColumnInfo(name = "armorInstallation6")
    double armorInstallation6;
    @ColumnInfo(name = "equipment6")
    double equipment6;
    @ColumnInfo(name = "customTax6")
    double customTax6;

    public VehicleArmorCost(String vehicleModel, double priceOfVehicle4, double armorPlate4, double armoredGlass4, double runFlat4, double vanDoorLock4, double upholster4, double bodyPainting4, double underChassis4, double doorHinges4, double vanDoorHinges4, double electricianMechanic4, double armorInstallation4, double equipment4, double customTax4, double priceOfVehicle6, double armorPlate6, double armoredGlass6, double runFlat6, double vanDoorLock6, double upholster6, double bodyPainting6, double underChassis6, double doorHinges6, double vanDoorHinges6, double electricianMechanic6, double armorInstallation6, double equipment6, double customTax6) {
        this.vehicleModel = vehicleModel;
        this.priceOfVehicle4 = priceOfVehicle4;
        this.armorPlate4 = armorPlate4;
        this.armoredGlass4 = armoredGlass4;
        this.runFlat4 = runFlat4;
        this.vanDoorLock4 = vanDoorLock4;
        this.upholster4 = upholster4;
        this.bodyPainting4 = bodyPainting4;
        this.underChassis4 = underChassis4;
        this.doorHinges4 = doorHinges4;
        this.vanDoorHinges4 = vanDoorHinges4;
        this.electricianMechanic4 = electricianMechanic4;
        this.armorInstallation4 = armorInstallation4;
        this.equipment4 = equipment4;
        this.customTax4 = customTax4;
        this.priceOfVehicle6 = priceOfVehicle6;
        this.armorPlate6 = armorPlate6;
        this.armoredGlass6 = armoredGlass6;
        this.runFlat6 = runFlat6;
        this.vanDoorLock6 = vanDoorLock6;
        this.upholster6 = upholster6;
        this.bodyPainting6 = bodyPainting6;
        this.underChassis6 = underChassis6;
        this.doorHinges6 = doorHinges6;
        this.vanDoorHinges6 = vanDoorHinges6;
        this.electricianMechanic6 = electricianMechanic6;
        this.armorInstallation6 = armorInstallation6;
        this.equipment6 = equipment6;
        this.customTax6 = customTax6;
    }


    public String[] getLvl4Costing(){
        DecimalFormat formatter = new DecimalFormat("#,###");

        String[] costings = {
                "Vehicle Model: " + this.vehicleModel,
                "Armor Level: Level 4",
                "Price of Vehicle:  ₱" + formatter.format(this.priceOfVehicle4),
                "Armor Plate:  ₱" + formatter.format(this.armorPlate4),
                "Armored Glass:  ₱" + formatter.format(this.armoredGlass4),
                "Run Flat: " + formatter.format(this.runFlat4),
                "Van Door Lock:  ₱" + formatter.format(this.vanDoorLock4),
                "Upholster: ₱" + formatter.format(this.upholster4),
                "Body Painting:  ₱" + formatter.format(this.bodyPainting4),
                "Under Chassis:  ₱" + formatter.format(this.underChassis4),
                "Door Hinges:  ₱"+ formatter.format(this.doorHinges4),
                "Van Door Hinges:  ₱" + formatter.format(this.vanDoorHinges4),
                "Electrician Mechanic:  ₱" + formatter.format(this.electricianMechanic4),
                "Armor Installation:  ₱" + formatter.format(this.armorInstallation4),
                "Equipment:  ₱" + formatter.format(this.equipment4),
                "Custom Tax:  ₱" + formatter.format(this.customTax4)
        };

        return costings;
    }

    public String[] getLvl6Costing(){
        DecimalFormat formatter = new DecimalFormat("#,###");

        String[] costings = {
                "Vehicle Model: " + this.vehicleModel,
                "Armor Level: Level 6",
                "Price of Vehicle:  ₱" +formatter.format( this.priceOfVehicle6),
                "Armor Plate:  ₱" + formatter.format(this.armorPlate6),
                "Armored Glass:  ₱" + formatter.format(this.armoredGlass6),
                "Run Flat:  ₱" + formatter.format(this.runFlat6),
                "Van Door Lock:  ₱" + formatter.format(this.vanDoorLock6),
                "Upholster:  ₱" + formatter.format(this.upholster6),
                "Body Painting:  ₱" +formatter.format( this.bodyPainting6),
                "Under Chassis:  ₱" + formatter.format(this.underChassis6),
                "Door Hinges:  ₱" +formatter.format( this.doorHinges6),
                "Van Door Hinges:  ₱" + formatter.format(this.vanDoorHinges6),
                "Electrician Mechanic:  ₱" + formatter.format(this.electricianMechanic6),
                "Armor Installation:  ₱" + formatter.format(this.armorInstallation6),
                "Equipment:  ₱" + formatter.format(this.equipment6),
                "Custom Tax:  ₱" + formatter.format(this.customTax6)
        };

        return costings;
    }

    public double getLvl4Total(){
        double total = this.priceOfVehicle4 +
        this.armorPlate4 +
        this.armoredGlass4 +
        this.runFlat4 +
        this.vanDoorLock4 +
        this.upholster4 +
        this.bodyPainting4 +
        this.underChassis4 +
        this.doorHinges4 +
        this.vanDoorHinges4 +
        this.electricianMechanic4 +
        this.armorInstallation4 +
        this.equipment4;

        return total;
    }

    public double getLvl6Total(){
        double total = this.priceOfVehicle6 +
                this.armorPlate6 +
                this.armoredGlass6 +
                this.runFlat6 +
                this.vanDoorLock6 +
                this.upholster6 +
                this.bodyPainting6 +
                this.underChassis6 +
                this.doorHinges6 +
                this.vanDoorHinges6 +
                this.electricianMechanic6 +
                this.armorInstallation6 +
                this.equipment6;

        return total;
    }
}
