package com.theapp.whichfishthisfishliteversion;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


/**
 * Under the GPL V.2 license, see License.txt
 * Custom adapter for the lists of fish with ranking icon
 * Created by whitney on 4/14/14.
 */
public class FishListCustomSimpleCursorAdapter extends SimpleCursorAdapter {
    private int layout;
    private LayoutInflater inflater;

    public FishListCustomSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c,from,to, 0);
        this.layout= R.layout.fragment_fishlist_list_row;
        this.inflater= LayoutInflater.from(context);
    }

    @Override
    public View newView (Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(layout, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        ((TextView) view.findViewById(R.id.fish_name)).setText(cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NAME)));
        ((TextView) view.findViewById(R.id.aka)).setText(cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_AKA)));
        switch (cursor.getInt(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_RANK))) {
            case 0:
                ((ImageView) view.findViewById(R.id.fish)).setImageResource(R.drawable.sad_fish);
                (view.findViewById(R.id.fish)).setContentDescription(context.getString(R.string.use_rarely));
                break;
            case 1:
                ((ImageView) view.findViewById(R.id.fish)).setImageResource(R.drawable.ok_fish);
                (view.findViewById(R.id.fish)).setContentDescription(context.getString(R.string.more_information));
                break;
            case 2:
                ((ImageView) view.findViewById(R.id.fish)).setImageResource(R.drawable.happy_fish);
                (view.findViewById(R.id.fish)).setContentDescription(context.getString(R.string.good_choice));
                break;
        }
    }

}
