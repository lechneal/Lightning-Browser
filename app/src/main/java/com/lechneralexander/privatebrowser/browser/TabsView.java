package com.lechneralexander.privatebrowser.browser;

public interface TabsView {

    void tabAdded();

    void tabRemoved(int position);

    void tabChanged(int position);

}
