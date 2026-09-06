package com.absolute.whatsappstickers;

import android.app.Application;
import com.absolute.whatsappstickers.constants.Constants;

public class StickerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Constants.init(this);
    }
}
