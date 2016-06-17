/*
 * Copyright 2014 A.C.R. Development
 */
package com.lechneralexander.privatebrowser.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.webkit.WebView;

import com.lechneralexander.privatebrowser.R;
import com.lechneralexander.privatebrowser.app.BrowserApp;
import com.lechneralexander.privatebrowser.utils.Utils;
import com.lechneralexander.privatebrowser.utils.WebUtils;
import com.lechneralexander.privatebrowser.view.LightningView;

public class PrivacySettingsFragment extends LightningPreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    private static final String SETTINGS_LOCATION = "location";
    private static final String SETTINGS_THIRDPCOOKIES = "third_party";
    private static final String SETTINGS_SAVEPASSWORD = "password";
    private static final String SETTINGS_CACHEEXIT = "clear_cache_exit";
    private static final String SETTINGS_HISTORYEXIT = "clear_history_exit";
    private static final String SETTINGS_COOKIEEXIT = "clear_cookies_exit";
    private static final String SETTINGS_CLEARPRIVATEDATA = "clear_private_data";
    private static final String SETTINGS_CLEARCACHE = "clear_cache";
    private static final String SETTINGS_CLEARHISTORY = "clear_history";
    private static final String SETTINGS_CLEARCOOKIES = "clear_cookies";
    private static final String SETTINGS_CLEARWEBSTORAGE = "clear_webstorage";
    private static final String SETTINGS_WEBSTORAGEEXIT = "clear_webstorage_exit";
    private static final String SETTINGS_DONOTTRACK = "do_not_track";
    private static final String SETTINGS_IDENTIFYINGHEADERS = "remove_identifying_headers";
    private static final String SETTINGS_FINISH_ON_PAUSE = "finish_on_pause";

    private Activity mActivity;
    private Handler mMessageHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BrowserApp.getAppComponent().inject(this);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preference_privacy);

        mActivity = getActivity();

        initPrefs();
    }

    private void initPrefs() {
        Preference clearprivatedata = findPreference(SETTINGS_CLEARPRIVATEDATA);

        CheckBoxPreference cblocation = (CheckBoxPreference) findPreference(SETTINGS_LOCATION);
        CheckBoxPreference cbsavepasswords = (CheckBoxPreference) findPreference(SETTINGS_SAVEPASSWORD);
        CheckBoxPreference cbcacheexit = (CheckBoxPreference) findPreference(SETTINGS_CACHEEXIT);
        CheckBoxPreference cbFinishOnPause = (CheckBoxPreference) findPreference(SETTINGS_FINISH_ON_PAUSE);
        CheckBoxPreference cbDoNotTrack = (CheckBoxPreference) findPreference(SETTINGS_DONOTTRACK);
        CheckBoxPreference cbIdentifyingHeaders = (CheckBoxPreference) findPreference(SETTINGS_IDENTIFYINGHEADERS);

        clearprivatedata.setOnPreferenceClickListener(this);

        cblocation.setOnPreferenceChangeListener(this);
        cbsavepasswords.setOnPreferenceChangeListener(this);
        cbcacheexit.setOnPreferenceChangeListener(this);
        cbDoNotTrack.setOnPreferenceChangeListener(this);
        cbFinishOnPause.setOnPreferenceChangeListener(this);
        cbIdentifyingHeaders.setOnPreferenceChangeListener(this);

        cblocation.setChecked(mPreferenceManager.getLocationEnabled());
        cbsavepasswords.setChecked(mPreferenceManager.getSavePasswordsEnabled());
        cbcacheexit.setChecked(mPreferenceManager.getClearCacheExit());
        cbDoNotTrack.setChecked(mPreferenceManager.getDoNotTrackEnabled() && Utils.doesSupportHeaders());
        cbFinishOnPause.setChecked(mPreferenceManager.getFinishOnPause());
        cbIdentifyingHeaders.setChecked(mPreferenceManager.getRemoveIdentifyingHeadersEnabled() && Utils.doesSupportHeaders());

        cbDoNotTrack.setEnabled(Utils.doesSupportHeaders());
        cbIdentifyingHeaders.setEnabled(Utils.doesSupportHeaders());

        String identifyingHeadersSummary = LightningView.HEADER_REQUESTED_WITH + ", " + LightningView.HEADER_WAP_PROFILE;
        cbIdentifyingHeaders.setSummary(identifyingHeadersSummary);


        mMessageHandler = new MessageHandler(mActivity);
    }

    private static class MessageHandler extends Handler {

        final Activity mHandlerContext;

        public MessageHandler(Activity context) {
            this.mHandlerContext = context;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Utils.showSnackbar(mHandlerContext, R.string.message_clear_history);
                    break;
                case 2:
                    Utils.showSnackbar(mHandlerContext, R.string.message_cookies_cleared);
                    break;
            }
            super.handleMessage(msg);
        }
    }

    @Override
    public boolean onPreferenceClick(@NonNull Preference preference) {
        switch (preference.getKey()) {
            case SETTINGS_CLEARPRIVATEDATA:
                clearAllPrivateData();
                return true;
            case SETTINGS_CLEARCACHE:
                clearCache();
                return true;
            case SETTINGS_CLEARCOOKIES:
                clearCookiesDialog();
                return true;
            case SETTINGS_CLEARWEBSTORAGE:
                clearWebStorage();
                return true;
            default:
                return false;
        }
    }

    private void clearAllPrivateData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(getResources().getString(R.string.title_clear_all));
        builder.setMessage(getResources().getString(R.string.dialog_clear_all))
                .setPositiveButton(getResources().getString(R.string.action_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                WebUtils.clearCache(getActivity());
                                WebUtils.clearWebStorage();
                                BrowserApp.getTaskThread().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        clearCookies();
                                    }
                                });
                                Utils.trimCache(getActivity());
                                Utils.showSnackbar(getActivity(), R.string.message_clear_privatedata);
//                                BrowserApp.getIOThread().execute(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        clearHistory();
//                                    }
//                                });
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.action_no), null).show();
    }

    private void clearCookiesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(getResources().getString(R.string.title_clear_cookies));
        builder.setMessage(getResources().getString(R.string.dialog_cookies))
                .setPositiveButton(getResources().getString(R.string.action_yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                BrowserApp.getTaskThread().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        clearCookies();
                                    }
                                });
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.action_no), null).show();
    }

    private void clearCache() {
        WebView webView = new WebView(mActivity);
        webView.clearCache(true);
        webView.destroy();
        Utils.showSnackbar(mActivity, R.string.message_cache_cleared);
    }


    private void clearCookies() {
        WebUtils.clearCookies(getActivity());
        mMessageHandler.sendEmptyMessage(2);
    }

    private void clearWebStorage() {
        WebUtils.clearWebStorage();
        Utils.showSnackbar(getActivity(), R.string.message_web_storage_cleared);
    }

    @Override
    public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
        switch (preference.getKey()) {
            case SETTINGS_LOCATION:
                mPreferenceManager.setLocationEnabled((Boolean) newValue);
                return true;
            case SETTINGS_SAVEPASSWORD:
                mPreferenceManager.setSavePasswordsEnabled((Boolean) newValue);
                return true;
            case SETTINGS_CACHEEXIT:
                mPreferenceManager.setClearCacheExit((Boolean) newValue);
                return true;
            case SETTINGS_DONOTTRACK:
                mPreferenceManager.setDoNotTrackEnabled((Boolean) newValue);
                return true;
            case SETTINGS_IDENTIFYINGHEADERS:
                mPreferenceManager.setRemoveIdentifyingHeadersEnabled((Boolean) newValue);
                return true;
            case SETTINGS_FINISH_ON_PAUSE:
                mPreferenceManager.setFinishOnPause((Boolean) newValue);
                return true;
            default:
                return false;
        }
    }
}
