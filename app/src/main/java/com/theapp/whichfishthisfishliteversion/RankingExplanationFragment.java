package com.theapp.whichfishthisfishliteversion;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

/**
 * A custom dialog to explain the different ranking pictures
 * Created by whitney on 4/30/14.
 * Under the GPL V.2 license, see License.txt
 */
public class RankingExplanationFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.rank_explanation, null))
            .setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //
                }
            });
        return builder.create();
    }
}
