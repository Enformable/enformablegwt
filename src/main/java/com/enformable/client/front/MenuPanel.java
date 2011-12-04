package com.enformable.client.front;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;


/**
 * Created by IntelliJ IDEA.
 * User: me
 * Date: 12/3/11
 * Time: 11:24 PM
 * To change this template use File | Settings | File Templates.
 */

public class MenuPanel extends DockLayoutPanel {
    public MenuPanel() {
        super(Style.Unit.PCT);

        setStyleName("mainMenu");

        MenuBar main = new MenuBar(false);

        Command cmd = new Command() {
            public void execute() {
                Window.alert("You selected a menu item!");
            }
        };

        main.addItem("+", cmd);
        main.addItem("Channel", cmd);
        main.addItem("When", cmd);
        main.addItem("Where", cmd);
        main.addItem("What", cmd);
        main.addItem("Who", cmd);

        addWest(main, 50);

    }
}
