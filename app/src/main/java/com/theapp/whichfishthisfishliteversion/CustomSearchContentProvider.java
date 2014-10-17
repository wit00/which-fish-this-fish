package com.theapp.whichfishthisfishliteversion;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/** This class is my custom Content Provider class that lets the search have custom suggestions.
 * Under the GPL V.2 license, see License.txt**/
public class CustomSearchContentProvider extends ContentProvider {
    private FishDatabaseHelper fishDatabaseHelper;

    public CustomSearchContentProvider() {}

    @Override
    public boolean onCreate() {
        fishDatabaseHelper = new FishDatabaseHelper(getContext());
        return true;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteDatabase sqLiteDatabase = fishDatabaseHelper.getWritableDatabase();
        String queryString = "SELECT * FROM " + FishDatabaseContract.FishEntry.SUGGESTIONS_TABLE_NAME+" WHERE "+ SearchManager.SUGGEST_COLUMN_TEXT_1+ " LIKE '"+"%"+selectionArgs[0]+"%"+"'"+
                " OR " + SearchManager.SUGGEST_COLUMN_TEXT_2+" LIKE '"+"%"+selectionArgs[0]+"%"+"'" ;
        return sqLiteDatabase != null ? sqLiteDatabase.rawQuery(queryString, null) : null;
    }

    /** Unsupported Methods.
     * These methods have to be overridden to use the Content Provider, but I don't need them
     * with this simple application, so they are unsupported and throw an exception if used.**/
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("The delete method is not supported with this application");
    }

    @Override
    public String getType(Uri uri) {
        //throw new UnsupportedOperationException("The getType method is not supported with this application.");
        return "";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //throw new UnsupportedOperationException("The insert method is not supported with this application");
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        throw new UnsupportedOperationException("The update method is not supported with this application");
    }
}
