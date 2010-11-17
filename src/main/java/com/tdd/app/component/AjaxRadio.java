package com.tdd.app.component;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;

public abstract class AjaxRadio<T> extends Radio<T> {

	private static final long serialVersionUID = 1L;
	public abstract void onAjaxEvent(AjaxRequestTarget target);

	public AjaxRadio(String id, IModel<T> model) {
		super(id, model);
		setOutputMarkupId(true);
		setupAjax();
	}
	
	 private void setupAjax() {

        add(new AjaxEventBehavior("onclick") {

             private static final long serialVersionUID = 1L;

             protected void onEvent(final AjaxRequestTarget target) {

                 RadioGroup<?> radioGroup = getEnclosingRadioGroup();
                 radioGroup.processInput();
                 onAjaxEvent(target);

             }
            
			protected final CharSequence getEventHandler() {

                 return generateCallbackScript(new AppendingStringBuffer("wicketAjaxPost('")
                                                     .append(getCallbackUrl())
                                                     .append("', wicketSerialize(Wicket.$('")
                                                     .append(AjaxRadio.this.getMarkupId())
                                                     .append("'))"));
             }
         });
     }

     private RadioGroup<?> getEnclosingRadioGroup() {

         RadioGroup<?> group = (RadioGroup<?>) findParent(RadioGroup.class);
         if (group == null) {
             throw new WicketRuntimeException("Radio component ["
                     + getPath()
                     + "] cannot find its parent RadioGroup. All Radio components must be a child of or below in the hierarchy of a RadioGroup component.");
         }
         return group;
     }
}
