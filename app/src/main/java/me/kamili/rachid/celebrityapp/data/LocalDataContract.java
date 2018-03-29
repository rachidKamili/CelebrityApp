package me.kamili.rachid.celebrityapp.data;

import android.provider.BaseColumns;

/**
 * Created by Admin on 3/28/2018.
 */

public class LocalDataContract {

    public static final String NAME = "Celebrity.db";
    public static final int VERSION = 1;

    public static final String CREATE_CELEBRITY_TABLE
            = "CREATE TABLE " + Celebrity.TABLE + "(" +
            Celebrity.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Celebrity.FIRST_NAME + " TEXT, " +
            Celebrity.LAST_NAME + " TEXT, " +
            Celebrity.OCCUPATION + " TEXT, " +
            Celebrity.ISFAVORITE + " INTEGER DEFAULT 0)";

    public static final String GET_ALL = "SELECT * FROM " + Celebrity.TABLE;

    public static class Celebrity implements BaseColumns {

        public static final String TABLE = "Celebrity";
        public static final String KEY_ID = "keyid";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String OCCUPATION = "occupation";
        public static final String ISFAVORITE = "isFavorite";
    }

}
