package com.nadeem.app.radio;

import org.apache.wicket.protocol.http.WebApplication;

import com.nadeem.app.radio.HomePage;


public class WicketApplication extends WebApplication {    

	public WicketApplication() {

	}	

	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

}
