package com.theapp.whichfishthisfishliteversion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * Under the GPL V.2 license, see License.txt
 * Override the SQLiteOpenHelper class to handle the database more efficiently
 */
public class FishDatabaseHelper extends SQLiteOpenHelper {
    public FishDatabaseHelper(Context context) {
        super(context, FishDatabaseContract.DATABASE_NAME, null, FishDatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FishDatabaseContract.FishEntry.CREATE_FISH_INFO_TABLE);
        db.execSQL(FishDatabaseContract.FishEntry.CREATE_SUGGESTIONS_TABLE);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // discard the data and start over
        db.execSQL(FishDatabaseContract.FishEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
