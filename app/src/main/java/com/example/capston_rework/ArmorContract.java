package com.example.capston_rework;

import android.provider.BaseColumns;

public final class ArmorContract {
    private ArmorContract() {}

    public static class ArmorEntry implements BaseColumns {
        public static final String TABLE_NAME = "car_armor";
        public static final String COLUMN_NAME_LEVEL = "level";
        public static final String COLUMN_NAME_MATERIALS = "materials"; // New column for detailed descriptions
        public static final String COLUMN_NAME_BALISTIC_ARMOR = "balistic_armor";
        public static final String COLUMN_NAME_ARMORED_GLASS = "armored_glass";
        public static final String COLUMN_NAME_RUN_FLAT = "run_flat";
        public static final String COLUMN_NAME_VAN_DOOR_LOCK = "van_door_lock";
        public static final String COLUMN_NAME_UNDER_CHASSIS = "under_chassis";
        public static final String COLUMN_NAME_DOOR_HINGES = "door_hinges";
        public static final String COLUMN_NAME_ELECTRICIAN_MECHANIC = "electrician_mechanic";
        public static final String COLUMN_NAME_ARMOR_MECHANIC = "armor_mechanic";
        public static final String COLUMN_NAME_EQUIPMENTS = "equipments";
        public static final String COLUMN_NAME_TAX = "tax";
    }

}
