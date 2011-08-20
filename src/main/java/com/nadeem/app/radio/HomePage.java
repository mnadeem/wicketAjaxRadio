package com.nadeem.app.radio;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.value.ValueMap;

import com.nadeem.app.radio.component.AjaxRadioPanel;

public class HomePage extends WebPage {

	private static final long serialVersionUID 	= 1L;
	public static final String RADIO_PANEL_ID 	= "radioPanel";
	
	private FeedbackPanel feedbackPanel;

    public HomePage(final PageParameters parameters) {
    	feedbackPanel = createFeedbackPanel();

    	add(new AjaxRadioPanel<ValueMap>(RADIO_PANEL_ID, getPersons(), "name") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onRadioSelect(AjaxRequestTarget target, ValueMap newSelection) {
				info("You have selected " + newSelection);
				target.addComponent(feedbackPanel);				
			}
    		
    	}); 
    	add(feedbackPanel);
    }
    
    private FeedbackPanel createFeedbackPanel() {
		FeedbackPanel  feedbackPanel = new FeedbackPanel("feedbackPanel");
		feedbackPanel.setOutputMarkupPlaceholderTag(true);
		return feedbackPanel;
	}
    
    private List<ValueMap> getPersons() {
		List<ValueMap> persons = new ArrayList<ValueMap>();
		persons.add(newPerson("Person 1"));
		persons.add(newPerson("Person 2"));
		persons.add(newPerson("Person 3"));
		
		return persons;
	}

    private ValueMap newPerson(String personName) {
		ValueMap map = new ValueMap();
		map.put("name", personName);

		return map;
	}
}
