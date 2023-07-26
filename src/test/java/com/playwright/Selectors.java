package com.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Selectors {

	String home = "file:///" + System.getProperty("user.dir") + "\\src\\web\\home.html";
	
	@Test
	public void textSelectors() {
		try(Playwright pw = Playwright.create()){
			BrowserType browserType = pw.chromium();
			Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
			Page page = browser.newPage();
			page.navigate(home);
			System.out.println(home);
			
			page.click("text=More Info");
			Assertions.assertEquals(page.title(), "Advantages");
		}
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = { //test will iterate over the three parameters
			"text=More Info", //case sensitive
			"text=more info", //case insensitive
			"'More Info'" // exact match
	})
	public void textSelectorsParametrizedTest(String stringSelector) {
		try(Playwright pw = Playwright.create()){
			BrowserType browserType = pw.chromium();
			Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
			Page page = browser.newPage();
			page.navigate(home);
			System.out.println(home);
			
			page.click(stringSelector);
			Assertions.assertEquals(page.title(), "Advantages");
		}
	}
}
