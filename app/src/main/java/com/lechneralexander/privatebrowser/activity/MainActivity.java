package com.lechneralexander.privatebrowser.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.lechneralexander.privatebrowser.R;
import com.lechneralexander.privatebrowser.react.Action;
import com.lechneralexander.privatebrowser.react.Observable;
import com.lechneralexander.privatebrowser.react.Subscriber;

@SuppressWarnings("deprecation")
public class MainActivity extends IncognitoActivity {

    @Override
    public Observable<Void> updateCookiePreference() {
        return super.updateCookiePreference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

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
