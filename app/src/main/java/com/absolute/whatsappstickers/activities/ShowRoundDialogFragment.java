package com.absolute.whatsappstickers.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.absolute.whatsappstickers.R;
import com.absolute.whatsappstickers.RoundedBottomSheet;


public class ShowRoundDialogFragment extends RoundedBottomSheet {


    TextView share, license, feedback, about, create;
    private CheckRefreshClickListener mCheckSharingListener;
    private CheckRefreshClickListener mCheckLicenseListener;
    private CheckRefreshClickListener mCheckFeedbackListener;
    private CheckRefreshClickListener mCheckAboutListener;
    private CheckRefreshClickListener mCheckCreateListener;

    public static ShowRoundDialogFragment newInstance() {
        return new ShowRoundDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mCheckLicenseListener = (CheckRefreshClickListener) context;
        mCheckFeedbackListener= (CheckRefreshClickListener) context;
        mCheckAboutListener = (CheckRefreshClickListener) context;
        mCheckSharingListener=(CheckRefreshClickListener)context;
        mCheckCreateListener=(CheckRefreshClickListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_bottom_sheet, container,
                false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        share = getView().findViewById(R.id.share);
        license = getView().findViewById(R.id.license);
        feedback = getView().findViewById(R.id.feedback);
        about = getView().findViewById(R.id.about);
        create=getView().findViewById(R.id.make);
        license.setOnClickListener(v -> mCheckLicenseListener.onLicenseClick());
        feedback.setOnClickListener(v -> mCheckFeedbackListener.onFeedbackClick());
        about.setOnClickListener(v -> mCheckAboutListener.onAboutClick());
        share.setOnClickListener(v -> mCheckSharingListener.OnShareClick());
        create.setOnClickListener(v -> mCheckCreateListener.onCreateClick());
        super.onViewCreated(view, savedInstanceState);

    }
}


interface CheckRefreshClickListener {
    void onLicenseClick();
    void OnShareClick();
    void onFeedbackClick();
    void onAboutClick();
    void onCreateClick();
}