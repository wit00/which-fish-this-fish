package com.theapp.whichfishthisfishliteversion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Under the GPL V.2 license, see License.txt
 * Custom adapter for designing the settings activity
 * Created by whitney on 3/31/14.
 */
public class FishListCustomAdapter extends ArrayAdapter {
    private final SettingsActivity.Row[] rows;

    public FishListCustomAdapter(Context context, SettingsActivity.Row[] rows) {
        super(context, android.R.layout.simple_list_item_1,rows);
        this.rows = rows;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(rows[position].header()) {
            view =  LayoutInflater.from(getContext()).inflate(R.layout.fragment_settings_list_header, parent, false);
            assert view != null;
            ((TextView) (view.findViewById(R.id.headerText))).setText(rows[position].title());
            return  view;
        }
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_settings_list_row, parent, false);
        assert view != null;
        ((TextView) (view.findViewById(R.id.fish_name))).setText(rows[position].title());
        ((TextView) view.findViewById(R.id.aka)).setText(rows[position].text());
        return view;
    }
}
