package com.enformable.client.front;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;


/**
 * Created by IntelliJ IDEA.
 * User: me
 * Date: 12/3/11
 * Time: 11:24 PM
 * To change this template use File | Settings | File Templates.
 */

public class HeaderPanel extends DockLayoutPanel {
    public HeaderPanel() {
        super(Style.Unit.PCT);

        setStylePrimaryName("header");

        Label nameLabel = new Label("Anonymous");
        nameLabel.setStyleName("headerNameLabel");
        addWest(nameLabel, 50);
    }
}
