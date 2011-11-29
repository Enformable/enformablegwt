package com.enformable.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NewsSite implements EntryPoint {


    public void onModuleLoad() {
        RootPanel rootPanel = RootPanel.get();
        rootPanel.clear();
        rootPanel.add(new Label() {{
            setText("special northrup style closure here.");
        }});
    }
}
