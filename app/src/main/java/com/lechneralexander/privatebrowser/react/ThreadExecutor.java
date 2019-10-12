package com.lechneralexander.privatebrowser.react;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

import java.util.concurrent.Executor;

class ThreadExecutor implements Executor {

    private final Handler mHandler;

    public ThreadExecutor(@NonNull Looper looper) {
        mHandler = new Handler(looper);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mHandler.post(command);
    }
}
