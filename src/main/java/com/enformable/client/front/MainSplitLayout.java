package com.enformable.client.front;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: me
 * Date: 12/3/11
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainSplitLayout extends Composite {
    interface MainSplitLayoutUiBinder extends UiBinder<Widget, MainSplitLayout> {
    }

    private static MainSplitLayoutUiBinder uiBinder = GWT.create(MainSplitLayoutUiBinder.class);

    @UiField
    RichTextArea contentEdit;
    @UiField
    VerticalPanel contentEditWrapper;
    @UiField
    VerticalPanel indexWrapper;

    public MainSplitLayout() {
        initWidget(uiBinder.createAndBindUi(this));

        contentEditWrapper.insert(new RichTextToolbar(contentEdit), 0);


        for (int i = 0; i < 10; i++) {
            indexWrapper.add(new ItemCell("News Item", new Date()));
        }

    }


}