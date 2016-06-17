package com.lechneralexander.privatebrowser.utils;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by alexander on 16.06.2016.
 */
public class ConfigUtils {


    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    public static int getDefaultUserAgent(Context context) {
        return isTablet(context) ? 2 : 1;
    }
}
