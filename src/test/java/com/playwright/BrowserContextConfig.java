package com.playwright;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ViewportSize;


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
				.setBaseURL(baseURL)
				.setStrictSelectors(true)
				.setViewportSize(ViewPortSize.IPHONE_X)
				.setJavaScriptEnabled(true));
		page = browserCtx.newPage();

	}
	

	@AfterEach
	public void cleanUp() {
		browser.close();
		pw.close();
	}
	
	static class ViewPortSize {
		public static final ViewportSize IPHONE_X = new ViewportSize(375, 812);
		public static final ViewportSize GALAXY_S5 = new ViewportSize(360, 640);
	}
	

	@Test
	public void setBaseUrl() {
		initialize();
		page.navigate("home.html");
		page.navigate("advantages.html");
		
	}
	
	
	@Test
	public void setStrictSelectors() {
		initialize();
		page.navigate("home.html");
		page.isVisible("text=Submit"); // results to error when more than 1 element is found
		
	}
	
	@Test
	public void setViewPortSize() {
		initialize();
		page.navigate("home.html");
		page.click("#submit-donation"); 
		
	}
	
	@Test
	public void setJavascriptEnabled() {
		initialize();
		page.navigate("home.html");
		page.click("#clap-image"); 
		
		Assertions.assertFalse(page.isVisible("#thank-you"));
		Assertions.assertFalse(page.isVisible("#enable-js-msg"));
		
	}
}
