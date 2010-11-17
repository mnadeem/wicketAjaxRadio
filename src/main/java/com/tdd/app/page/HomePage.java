package com.tdd.app.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.tdd.app.panel.AjaxRadioPanel;

public class HomePage extends WebPage {

	private static final long serialVersionUID 	= 1L;
	public static final String RADIO_PANEL_ID 	= "radioPanel";

    public HomePage(final PageParameters parameters) {
    	add(new AjaxRadioPanel(RADIO_PANEL_ID));   
    }
}
