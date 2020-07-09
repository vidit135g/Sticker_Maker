package com.absolute.whatsappstickers.backgroundRemover;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.absolute.whatsappstickers.R;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_magic_title), getString(R.string.intro_magic_description), R.drawable.intro_magic_wand, getResources().getColor(R.color.gyellow)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_manual_title), getString(R.string.intro_manual_description), R.drawable.intro_pencil, getResources().getColor(R.color.gyellow)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_zoom_title), getString(R.string.intro_zoom_description), R.drawable.intro_magnifier, getResources().getColor(R.color.gyellow)));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#f4b400"));
        setSeparatorColor(Color.parseColor("#f4b400"));


        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);

        setFadeAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}