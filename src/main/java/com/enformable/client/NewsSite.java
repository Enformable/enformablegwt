package com.enformable.client;

import com.enformable.client.front.HeaderPanel;
import com.enformable.client.front.MainSplitLayout;
import com.enformable.client.front.MenuPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NewsSite implements EntryPoint {
    public void onModuleLoad() {
//        RootPanel rootPanel = RootPanel.get();
//        rootPanel.clear();
//        rootPanel.add(new FrontPage());

        //RootLayoutPanel.get().add(new FrontPage());
        DockLayoutPanel v = new DockLayoutPanel(Style.Unit.PX);
        v.addNorth(new HeaderPanel(), 30);
        v.addNorth(new MenuPanel(), 30);
        v.add(new MainSplitLayout());
        v.setSize("100%", "100%");

        RootLayoutPanel.get().add(v);
    }
}
