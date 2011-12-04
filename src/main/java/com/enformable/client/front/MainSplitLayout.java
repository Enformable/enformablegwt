package com.enformable.client.front;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;

/**
 * Created by IntelliJ IDEA.
 * User: me
 * Date: 12/3/11
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainSplitLayout {
    interface MainSplitLayoutUiBinder extends UiBinder<DivElement, MainSplitLayout> {
    }

    private static MainSplitLayoutUiBinder ourUiBinder = GWT.create(MainSplitLayoutUiBinder.class);

    public MainSplitLayout() {
        DivElement rootElement = ourUiBinder.createAndBindUi(this);

    }
}