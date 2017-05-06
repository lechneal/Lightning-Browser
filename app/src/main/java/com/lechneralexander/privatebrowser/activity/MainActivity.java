package com.lechneralexander.privatebrowser.activity;

import android.content.Intent;

@SuppressWarnings("deprecation")
public class MainActivity extends IncognitoActivity {

    @Override
    protected void onNewIntent(Intent intent) {
        if (isPanicTrigger(intent)) {
            panicClean();
        } else {
            handleNewIntent(intent);
            super.onNewIntent(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveOpenTabs();
    }




}
