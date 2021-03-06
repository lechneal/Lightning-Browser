package com.lechneralexander.privatebrowser.bus;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

public final class BrowserEvents {

    private BrowserEvents() {
        // No instances
    }

    /**
     * The {@link com.lechneralexander.privatebrowser.activity.BrowserActivity} signal a new bookmark was added
     * (mainly to the {@link com.lechneralexander.privatebrowser.fragment.BookmarksFragment}).
     */
    public static class BookmarkAdded {
        public final String title, url;

        public BookmarkAdded(final String title, final String url) {
            this.title = title;
            this.url = url;
        }
    }

    /**
     * Notify the current page has a new url. This is generally used to update the
     * {@link com.lechneralexander.privatebrowser.fragment.BookmarksFragment} interface.
     */
    public static class CurrentPageUrl {
        public final String url;

        public CurrentPageUrl(final String url) {
            this.url = url;
        }
    }

    /**
     * Notify the BookmarksFragment and TabsFragment that the user pressed the back button
     */
    public static class UserPressedBack {
    }

    /**
     *
     */

    /**
     * Notify the Browser to display a SnackBar in the main activity
     */
    public static class ShowSnackBarMessage {
        @Nullable public final String message;
        @StringRes
        public final int stringRes;

        public ShowSnackBarMessage(@Nullable final String message) {
            this.message = message;
            this.stringRes = -1;
        }

        public ShowSnackBarMessage(@StringRes final int stringRes) {
            this.message = null;
            this.stringRes = stringRes;
        }
    }

    public final static class OpenHistoryInCurrentTab {
    }

    /**
     * The user want to open the given url in the current tab
     */
    public final static class OpenUrlInCurrentTab {
        public final String url;

        public OpenUrlInCurrentTab(final String url) {
            this.url = url;
        }
    }

    /**
     * The user ask to open the given url as new tab
     */
    public final static class OpenUrlInNewTab {
        public final String url;

        public OpenUrlInNewTab(final String url) {
            this.url = url;
        }
    }

    /**
     * The user ask to open the given url as new background tab
     */
    public final static class OpenUrlInNewBackgroundTab {
        public final String url;

        public OpenUrlInNewBackgroundTab(final String url) {
            this.url = url;
        }
    }
}
