package com.shoon.android_xkcd_persistance;

import android.provider.BaseColumns;

public class XkcdDbContract {
    static class ComicEntry implements BaseColumns {
        public static final String TABLE_NAME                  = "Comics";
        public static final String COLUMN_NAME_NAME            = "name";
        public static final String COLUMN_NAME_ROTATION_PERIOD = "rotation_period";
        public static final String COLUMN_NAME_ORBITAL_PERIOD  = "orbital_period";
        public static final String COLUMN_NAME_DIAMETER        = "diameter";
        public static final String COLUMN_NAME_CLIMATE         = "climate";
        public static final String COLUMN_NAME_GRAVITY         = "gravity";
        public static final String COLUMN_NAME_TERRAIN         = "terrain";
        public static final String COLUMN_NAME_SURFACE_WATER   = "surface_water";
        public static final String COLUMN_NAME_POPULATION = "population";
        public static final String PRIMARY_KEY = "PRIMARY KEY";
        public static final String INTEGER = "INTEGER";
        public static final String TEXT = "TEXT";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + " ( " +
                _ID + " " + INTEGER + " " + PRIMARY_KEY + ", " +
                "iTimeStampRead" + " " + INTEGER + ", " +
                "iBool" + " " + INTEGER+");";


        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}