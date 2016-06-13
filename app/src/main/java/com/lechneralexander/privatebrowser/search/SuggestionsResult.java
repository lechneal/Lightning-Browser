package com.lechneralexander.privatebrowser.search;

import android.support.annotation.NonNull;

import java.util.List;

import com.lechneralexander.privatebrowser.database.HistoryItem;

interface SuggestionsResult {

    /**
     * Called when the search suggestions have
     * been retrieved from the server.
     *
     * @param searchResults the results, a valid
     *                      list of results. May
     *                      be empty.
     */
    void resultReceived(@NonNull List<HistoryItem> searchResults);

}
