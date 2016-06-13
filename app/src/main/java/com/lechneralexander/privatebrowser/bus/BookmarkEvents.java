package com.lechneralexander.privatebrowser.bus;

import com.lechneralexander.privatebrowser.database.HistoryItem;

public final class BookmarkEvents {

    private BookmarkEvents() {
        // No instances
    }

    /**
     * The user ask to delete the selected bookmark
     */
    public static class Deleted {
        public final HistoryItem item;

        public Deleted(final HistoryItem item) {
            this.item = item;
        }
    }

    /**
     * The user ask to add/del a bookmark to the currently displayed page
     */
    public static class ToggleBookmarkForCurrentPage {
    }

    /**
     * Sended by the {@link com.lechneralexander.privatebrowser.fragment.BookmarksFragment} when it wants to close
     * itself (generally in reply to a {@link com.lechneralexander.privatebrowser.bus.BrowserEvents.UserPressedBack}
     * event.
     */
    public static class CloseBookmarks {
    }

    /**
     * Sended when a bookmark is edited
     */
    public static class BookmarkChanged {

        public final HistoryItem oldBookmark;
        public final HistoryItem newBookmark;

        public BookmarkChanged(final HistoryItem oldItem, final HistoryItem newItem) {
            oldBookmark = oldItem;
            newBookmark = newItem;
        }
    }
}
