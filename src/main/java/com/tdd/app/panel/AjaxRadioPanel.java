package com.tdd.app.panel;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import com.tdd.app.component.AjaxRadio;


public class AjaxRadioPanel extends Panel {

	private static final long serialVersionUID = 1L;
	private FeedbackPanel feedbackPanel;

	public AjaxRadioPanel(String id) {
		super(id);
		
		feedbackPanel 						= newFeedbackPanel();
		Form<ValueMap> form 				= new Form<ValueMap>("form");
		final RadioGroup<ValueMap> group 	= new RadioGroup<ValueMap>("group", new Model<ValueMap>(new ValueMap()));
	

		group.add(newPersonsView(group));
		form.add(group);
		add(form);
		add(feedbackPanel);
	}

	private Component newPersonsView(final RadioGroup<ValueMap> group) {
		return new ListView<ValueMap>("persons", getPersons()) {

			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<ValueMap> item) {
				item.add(newAjaxRadioCell(group, item));
				item.add(new Label("name", new PropertyModel<ValueMap>(item.getModel(), "name")));
			}		
		};		
	}

	private AjaxRadio<ValueMap> newAjaxRadioCell(final RadioGroup<ValueMap> group, ListItem<ValueMap> item) {
		return new AjaxRadio<ValueMap>("radio", item.getModel()) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onAjaxEvent(AjaxRequestTarget target) {
				info("You have selected " + group.getModelObject());
				target.add(feedbackPanel);
			}			
		};
	}

	private List<ValueMap> getPersons() {
		List<ValueMap> persons = new ArrayList<ValueMap>();
		persons.add(newValueMap("Person 1"));
		persons.add(newValueMap("Person 2"));
		
		return persons;
	}
	private ValueMap newValueMap(String personName) {
		ValueMap map = new ValueMap();
		map.put("name", personName);
		
		return map;
	}
	private FeedbackPanel newFeedbackPanel() {
		FeedbackPanel  feedbackPanel = new FeedbackPanel("feedbackPanel");
		feedbackPanel.setOutputMarkupPlaceholderTag(true);
		return feedbackPanel;
	}	
}
