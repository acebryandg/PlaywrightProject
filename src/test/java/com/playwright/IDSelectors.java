package com.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class IDSelectors {

	String home = "file:///" + System.getProperty("user.dir") + "\\src\\web\\home.html";
	
	@Test
	public void idSelectors() {
		try(Playwright pw = Playwright.create()){
			BrowserType browserType = pw.chromium();
			Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
			Page page = browser.newPage();
			page.navigate(home);
			System.out.println(home);
			
			page.fill("id=surnameInput", "test surname");
			page.fill("data-test-id=surnameInput", "test surname 2");
			page.fill("m-id=surnameInput", "test surname 3"); // will result in error due to custom id
			
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
