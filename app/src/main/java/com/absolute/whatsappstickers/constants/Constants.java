package com.absolute.whatsappstickers.constants;

import android.content.Context;

public class Constants {

    public static String STICKERS_DIRECTORY_PATH;
    public static String STICKERS_CREATED_DIRECTORY_PATH;
    public final static int STICKER_PACK_IDENTIFIER_LENGHT = 20;
    
    public static void init(Context context) {
        if (STICKERS_DIRECTORY_PATH == null) {
            STICKERS_DIRECTORY_PATH = context.getExternalFilesDir(null).getAbsolutePath() + "/stickerPacks/";
            STICKERS_CREATED_DIRECTORY_PATH = context.getExternalFilesDir(null).getAbsolutePath() + "/stickersCreated/";
        }
    }
}
