package com.lechneralexander.privatebrowser.app;

import javax.inject.Singleton;

import com.lechneralexander.privatebrowser.activity.BrowserActivity;
import com.lechneralexander.privatebrowser.activity.TabsManager;
import com.lechneralexander.privatebrowser.activity.ThemableBrowserActivity;
import com.lechneralexander.privatebrowser.activity.ThemableSettingsActivity;
import com.lechneralexander.privatebrowser.browser.BrowserPresenter;
import com.lechneralexander.privatebrowser.constant.StartPage;
import com.lechneralexander.privatebrowser.dialog.LightningDialogBuilder;
import com.lechneralexander.privatebrowser.download.LightningDownloadListener;
import com.lechneralexander.privatebrowser.fragment.BookmarksFragment;
import com.lechneralexander.privatebrowser.fragment.DebugSettingsFragment;
import com.lechneralexander.privatebrowser.fragment.LightningPreferenceFragment;
import com.lechneralexander.privatebrowser.fragment.PrivacySettingsFragment;
import com.lechneralexander.privatebrowser.fragment.TabsFragment;
import com.lechneralexander.privatebrowser.search.SuggestionsAdapter;
import com.lechneralexander.privatebrowser.utils.AdBlock;
import com.lechneralexander.privatebrowser.view.LightningView;
import com.lechneralexander.privatebrowser.view.LightningWebClient;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(BrowserActivity activity);

    void inject(BookmarksFragment fragment);

    void inject(SuggestionsAdapter adapter);

    void inject(LightningDialogBuilder builder);

    void inject(TabsFragment fragment);

    void inject(LightningView lightningView);

    void inject(ThemableBrowserActivity activity);

    void inject(LightningPreferenceFragment fragment);

    void inject(BrowserApp app);

    void inject(LightningWebClient webClient);

    void inject(ThemableSettingsActivity activity);

    void inject(AdBlock adBlock);

    void inject(LightningDownloadListener listener);

    void inject(PrivacySettingsFragment fragment);

    void inject(StartPage startPage);

    void inject(BrowserPresenter presenter);

    void inject(TabsManager manager);

    void inject(DebugSettingsFragment fragment);

}
