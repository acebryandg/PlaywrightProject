package com.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import base.BaseClass;

public class Selectors extends BaseClass{

	//String home = "file:///" + System.getProperty("user.dir") + "\\src\\web\\home.html";

	@Test
	public void textSelectors() {
			page.navigate(home);
			page.click("text=More Info");
			Assertions.assertEquals(page.title(), "Advantages");
		
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
			//page.fill("m-id=surnameInput", "test surname 3"); // will result in error due to custom id
			
		}
	}
	
	@Test
	public void ccSelectors() {
		try(Playwright pw = Playwright.create()){
			BrowserType browserType = pw.chromium();
			Browser browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
			Page page = browser.newPage();
			page.navigate(home);
			System.out.println(home);
			
			page.fill("input", "first input element");
			page.fill(".form-control", "first box with this class");
			page.fill("form #exampleFormControlInput1", "combined selector");
			page.fill(":nth-match(.form-control, 2)", "Hello strnager");
			
		}
	}
	
}
