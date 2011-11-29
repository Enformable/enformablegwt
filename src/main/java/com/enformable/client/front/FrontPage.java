package com.enformable.client.front;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * eclipse designer requires
 * http://stackoverflow.com/questions/5518707/gwt-designer-doesnt-work-with-maven-gwt-project
 */
public class FrontPage extends Composite {

  static FrontPageUiBinder uiBinder = GWT
			.create(FrontPageUiBinder.class);

	interface FrontPageUiBinder extends UiBinder<Widget, FrontPage> {
	} 
	public   FrontPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
