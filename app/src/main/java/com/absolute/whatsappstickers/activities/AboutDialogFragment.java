package com.absolute.whatsappstickers.activities;

import android.app.Dialog;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import com.absolute.whatsappstickers.R;


public class AboutDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(R.layout.aboutlayout)
                .create();

        alertDialog.show();

        AppCompatButton btn=alertDialog.findViewById(R.id.btnRound);
        btn.setOnClickListener(v -> alertDialog.dismiss());
        return alertDialog;
    }


}
