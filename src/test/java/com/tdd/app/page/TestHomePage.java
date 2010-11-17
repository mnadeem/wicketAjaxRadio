package com.tdd.app.page;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.tdd.app.panel.AjaxRadioPanel;

public class TestHomePage {
	private WicketTester tester;

	@Before
	public void setUp()	{
		tester = buildWicketTester();
	}

	@Test
	public void assertPageRendered() throws Exception {
		tester.assertRenderedPage(HomePage.class);
	}

	@Test
	public void ajaxRadioPanelIsOnPage() throws Exception {
		tester.assertComponent(HomePage.RADIO_PANEL_ID, AjaxRadioPanel.class);
	}

	private WicketTester buildWicketTester() {
		WicketTester tester		= new WicketTester();
		tester.startPage(HomePage.class);
			
		return tester;
	}
}
