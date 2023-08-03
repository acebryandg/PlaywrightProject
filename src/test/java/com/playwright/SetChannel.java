package com.playwright;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.APIRequest.NewContextOptions;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SetChannel {
	
	String baseURL = "file:///" + System.getProperty("user.dir") + "\\src\\web\\home.html";
	Playwright pw;
	Browser browser;
	Page page;
	
	public void initialize(String channel) {
		pw = Playwright.create();
		browser = pw.chromium().launch(new LaunchOptions()
				.setChannel(channel)
				.setHeadless(false)
				.setSlowMo(2000)
				);
		
	}
	

	
	@ParameterizedTest
	@ValueSource(strings = {"chrome", "msedge"})
	public void setChannelTest(String channel) {
		initialize(channel);
		page.navigate(baseURL);
		
	}
}
