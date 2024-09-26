package com.example.capston_rework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.capston_rework.ArmorContract;

public class ArmorDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Armor.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ArmorContract.ArmorEntry.TABLE_NAME + " (" +
                    ArmorContract.ArmorEntry._ID + " INTEGER PRIMARY KEY," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_LEVEL + " TEXT," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_MATERIALS + " TEXT,"+
                    ArmorContract.ArmorEntry.COLUMN_NAME_BALISTIC_ARMOR + " REAL," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_ARMORED_GLASS + " REAL," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_RUN_FLAT + " REAL," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_VAN_DOOR_LOCK + " REAL," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_UNDER_CHASSIS + " REAL," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_DOOR_HINGES + " REAL," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_ELECTRICIAN_MECHANIC + " REAL," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_ARMOR_MECHANIC + " REAL," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_EQUIPMENTS + " REAL," +
                    ArmorContract.ArmorEntry.COLUMN_NAME_TAX + " REAL)";

    public ArmorDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ArmorContract.ArmorEntry.TABLE_NAME);
        onCreate(db);
    }

/*
    public void insertData() {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_LEVEL, "Level 4");
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_MATERIALS,
                "Ballistic Armored Plate Level 4:\n" +
                        "This armor features a 4mm-thick plate made from bulletproof metal, weighing approximately 500 kilograms. Produced in Japan and Belgium, it is capable of withstanding handguns such as the Glock Model 38, Caliber .45 ACP, TAURUS TH40C .40, and 9mm handguns. Additionally, it offers heat resistance and bomb protection.\n\n" +
                        "Armored Tempered Glass Level 4:\n" +
                        "With a thickness of 1 inch, this durable tempered glass is manufactured in Dubai. Each mirror weighs 20 kilograms, while the combined weight of the front and back mirrors is 200 kilograms. This glass is designed to withstand impacts from smaller firearms such as the Glock Model 38, Caliber .45 ACP, TAURUS TH40C .40, and 9mm handguns.\n\n" +
                        "Estimated Shipping of Armored Plate and Glass:\n" +
                        "The shipping time for the armored plate and glass is estimated to be between 3 weeks and 1 month.\n\n" +
                        "Estimated Armoring Completion:\n" +
                        "The completion of the armoring process is estimated to take about 2 months.");
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_BALISTIC_ARMOR, 505000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_ARMORED_GLASS, 500000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_RUN_FLAT, 150000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_VAN_DOOR_LOCK, 35000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_UNDER_CHASSIS, 85000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_DOOR_HINGES, 150000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_ELECTRICIAN_MECHANIC, 35000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_ARMOR_MECHANIC, 200000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_EQUIPMENTS, 20000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_TAX, 10000.0);
        db.insert(ArmorContract.ArmorEntry.TABLE_NAME, null, values);
        db.close();

    }


 */


/*

    public void insertData() {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_LEVEL, "Level 6");
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_MATERIALS,
                            "Ballistic Armored Plate Level 6:\n"+
                            "This 6mm-thick metal plate is crafted from bulletproof material with a weight ranging between 700 to 800 kilograms. Manufactured in Japan and Belgium, it is designed to withstand handguns and is specifically effective against assault rifles such as the AK47, grenades, and other high-caliber firearms. The plate features heat resistance and bomb protection, offering security against the most powerful rifles.\n\n" +
                            "Armored Tempered Glass Level 6:\n" +
                            "With a thickness of 1.75 inches, this robust tempered glass is produced in Dubai. Each mirror weighs approximately 40 kilograms, while the front and rear mirrors collectively weigh around 300 kilograms. This glass can withstand the impact of high-caliber firearms and grenades.\n\n" +
                            "Estimated Shipping of Materials:\n" +
                            "The expected shipping duration for the materials is between 3 weeks and 1 month.\n\n" +
                            "Estimated Armored Finishing:\n" +
                            "The armoring process is estimated to take between 2 to 3 months.");
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_BALISTIC_ARMOR, 660000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_ARMORED_GLASS, 800000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_RUN_FLAT, 15000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_VAN_DOOR_LOCK, 35000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_UNDER_CHASSIS, 85000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_DOOR_HINGES, 15000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_ELECTRICIAN_MECHANIC, 35000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_ARMOR_MECHANIC, 200000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_EQUIPMENTS, 25000.0);
        values.put(ArmorContract.ArmorEntry.COLUMN_NAME_TAX, 10000.0);
        db.insert(ArmorContract.ArmorEntry.TABLE_NAME, null, values);
        db.close();

    }

 */




}
