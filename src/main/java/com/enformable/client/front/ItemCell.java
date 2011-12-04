package com.enformable.client.front;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: me
 * Date: 12/3/11
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemCell extends FocusPanel implements ClickHandler {

    public boolean selected = false;

    public ItemCell(final String title, final Date when) {
        super();


        VerticalPanel vWrap = new VerticalPanel();
        vWrap.setStylePrimaryName("itemCell");
        {
            Label l = new Label(title);
            l.setStyleName("itemCellTitle");

            vWrap.add(l);
            vWrap.add(new Label(when.toString()));
        }
        add(vWrap);

        //addClickHandler(this);
        addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent mouseUpEvent) {
                selected = !selected;
                refresh();
            }
        });
        refresh();
    }

    protected void refresh() {
        this.setStylePrimaryName(selected ? "itemCellSelected" : "itemCellUnselected");
    }

    @Override
    public void onClick(ClickEvent clickEvent) {
        selected = !selected;
        refresh();
    }
}
