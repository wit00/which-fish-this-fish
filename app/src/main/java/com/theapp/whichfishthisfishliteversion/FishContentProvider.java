package com.theapp.whichfishthisfishliteversion;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

/** This class is the content provider for the application that lets me use the cursor loaders
 * Under the GPL V.2 license, see License.txt**/
public class FishContentProvider extends ContentProvider {
    private FishDatabaseHelper fishDatabaseHelper;
    private static final String AUTHORITY = "com.theapp.whichfishthisfishliteversion.fishProvider";
    private static UriMatcher uriMatcher;
    /*
    * Constants used by the Uri matcher to choose an action based on the pattern
    * of the incoming URI
    */
    private static final int FISH = 1;
    private static final int FISH_ID = 2;
    private static final int FISH_NAME = 3;
    private static final int DELETE = 4;
    private static final int INSERT_FISH = 5;
    private static final int INSERT_SUGGESTION = 6;
    /** A static block that adds uri types to the authority based on the incoming URI**/
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "fish", FISH);
        uriMatcher.addURI(AUTHORITY, "fish/#", FISH_ID);
        uriMatcher.addURI(AUTHORITY, "fish/name/*",FISH_NAME);
        uriMatcher.addURI(AUTHORITY,"delete",DELETE);
        uriMatcher.addURI(AUTHORITY,"insert/fish",INSERT_FISH);
        uriMatcher.addURI(AUTHORITY,"insert/suggestion",INSERT_SUGGESTION);
    }

    public FishContentProvider() {}

    @Override
    public boolean onCreate() {
        fishDatabaseHelper = new FishDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        Cursor fishCursor;
        SQLiteDatabase database = fishDatabaseHelper.getReadableDatabase();
        if (database == null) throw new AssertionError("the database is null in fish content provider");
        Context context = getContext();
        if (context == null) throw new AssertionError(" the context is null in fish content provider");
            switch (uriMatcher.match(uri)) {
                case FISH:
                    fishCursor = database.query(FishDatabaseContract.FishEntry.FISH_INFO_TABLE_NAME, projection, null, null, null, null, sortOrder);
                    break;
                case FISH_ID:
                    selection = FishDatabaseContract.FishEntry._ID + " = " + uri.getLastPathSegment();
                    fishCursor = database.query(FishDatabaseContract.FishEntry.FISH_INFO_TABLE_NAME, projection, selection, null, null, null, sortOrder);
                    break;
                case FISH_NAME:
                    String query = "%" + uri.getLastPathSegment() +"%";
                    String queryString = "SELECT * FROM " + FishDatabaseContract.FishEntry.FISH_INFO_TABLE_NAME+" WHERE "+FishDatabaseContract.FishEntry.FISH_NAME+" LIKE '"+query+"'"+
                            " OR " + FishDatabaseContract.FishEntry.FISH_AKA+" LIKE '"+query+"'"+" ORDER BY " + FishDatabaseContract.FishEntry.FISH_NAME + " COLLATE NOCASE ASC" ;
                    fishCursor = database.rawQuery(queryString, null);
                    break;
                default:
                    // If the URI doesn't match any of the known patterns, throw an exception.
                    throw new IllegalArgumentException("Unknown URI " + uri);
            }
        fishCursor.setNotificationUri(context.getContentResolver(),uri);
        return fishCursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if(uriMatcher.match(uri) == DELETE) {
            SQLiteDatabase database = fishDatabaseHelper.getWritableDatabase();
            if (database == null) throw new AssertionError();
            try {
                database.delete(FishDatabaseContract.FishEntry.FISH_INFO_TABLE_NAME,null,null);
                database.delete(FishDatabaseContract.FishEntry.SUGGESTIONS_TABLE_NAME,null,null); }
            catch (SQLiteException sqliteException) {
                Context context = getContext();
                if (context == null) throw new AssertionError();
                context.deleteDatabase(FishDatabaseContract.DATABASE_NAME);
            }
            return 1;
        }
        else {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = fishDatabaseHelper.getWritableDatabase();
        assert database != null;
        long thisInsert;
        switch (uriMatcher.match(uri)) {
            case INSERT_FISH:
                thisInsert = database.insert(FishDatabaseContract.FishEntry.FISH_INFO_TABLE_NAME, null, values);
                break;
            case INSERT_SUGGESTION:
                thisInsert = database.insert(FishDatabaseContract.FishEntry.SUGGESTIONS_TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(Uri.parse(ApplicationContract.FISH_LIST_URI),null);
        return Uri.withAppendedPath(uri, String.valueOf(thisInsert));
    }

    /** Unsupported Methods **/
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("update is not supported in this application");
    }


    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("getType is not supported in this application");
    }

}
