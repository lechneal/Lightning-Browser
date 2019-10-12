package com.lechneralexander.privatebrowser.app;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

import com.squareup.otto.Bus;


import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final BrowserApp mApp;
    @NonNull private final Bus mBus;

    public AppModule(BrowserApp app) {
        this.mApp = app;
        this.mBus = new Bus();
    }

    @Provides
    public Application provideApplication() {
        return mApp;
    }

    @Provides
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @NonNull
    @Provides
    public Bus provideBus() {
        return mBus;
    }


}
