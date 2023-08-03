package com.playwright;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;


public class BrowserContextConfig {
	
	String baseURL = "file:///" + System.getProperty("user.dir") + "\\src\\web\\";
	Playwright pw;
	Browser browser;
	BrowserContext	browserCtx;
	Page page;
	
	@BeforeEach
	public void initialize() {
		pw = Playwright.create();
		browser = pw.chromium().launch(new LaunchOptions()
				.setHeadless(false)
				.setSlowMo(2000)
				);
		browserCtx = browser.newContext(new Browser.NewContextOptions()
				.setBaseURL(baseURL));
		page = browserCtx.newPage();

	}
	

	
	@Test
	public void setBaseUrl() {
		initialize();
		page.navigate("home.html");
		page.navigate("advantages.html");
		
	}
	
	@AfterEach
	public void cleanUp() {
		browser.close();
		pw.close();
	}
}
