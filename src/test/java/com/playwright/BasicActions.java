package com.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.ClickOptions;
import com.microsoft.playwright.Page.NavigateOptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;

import base.BaseClass;

public class BasicActions extends BaseClass {

	@Test
	public void basicNavigation() {

		page.navigate(baseURL);

	}

	@Test
	public void navigationOptions() {

		page.navigate(baseURL, new NavigateOptions()
				// setters of the NavigateOptions class
				.setTimeout(35000).setWaitUntil(WaitUntilState.DOMCONTENTLOADED)); // enum yung WaitUntilState
		// .setWaitUntil(WaitUntilState.LOAD);
		// .setWaitUntil(WaitUntilState.NETWORKIDLE));

	}

	@Test
	public void basicClick() {

		page.navigate(baseURL);
		page.click("id=clap-image");

		Assertions.assertTrue(page.isVisible("text=We appreciate it!"));

	}

	@Test
	public void clickOptions() {

		page.navigate(baseURL);
		page.click("id=clap-image", new ClickOptions().setClickCount(2));
		Assertions.assertTrue(page.isVisible("text=We appreciate it!"));
		Assertions.assertTrue(page.isVisible("text=You can only clap once, but thanks for your enthusiasm."));

	}

	@Test
	public void fill() {

		page.navigate(baseURL);
		page.fill("id=exampleMessage", "TestMessage");

	}
	
	@Test
	public void fillOptions() {

		page.navigate(baseURL);
		page.fill("id=exampleMessage", "TestMessage",
				new Page.FillOptions().setForce(true)); //force playwright to send the event to the page

	}

	//SETFORCE - bypasses actionability (if element is visible, enabled, etc) checks

	@Test
	public void checkCheckBox() {

		page.navigate(baseURL);
		page.check("id=sendCopy");
		page.uncheck("id=sendCopy");
		page.check("id=sendCopy");
		page.click("id=submit-contact");
		
		Assertions.assertTrue(page.isVisible("text=We sent you a copy of your message "));

	}

	//playwright selects an option by their attribute value, not by text
	@Test
	public void selectOption() {

		page.navigate(baseURL);
		page.selectOption("id=contactReason", "Feedback");
		
		//Assertions.assertTrue(page.isVisible("text=We sent you a copy of your message "));

	}

	
	//fine-grained controls
	@Test
	public void pressOptions() {
		
		page.navigate(baseURL);
		page.fill("#exampleFormControlInput1", "myemail@inbox.con");
		
		Keyboard kb = page.keyboard();
		
		kb.press("Backspace");
		kb.press("m");
		
		page.focus("#contactReason");
		kb.press("ArrowDown");
		kb.press("ArrowDown");
	}
	
	
	
	@Test
	public void test() {
		
		page.navigate(baseURL);
		page.fill("#exampleFormControlInput1", "myemail@inbox.con");
		
		Keyboard kb = page.keyboard();
		
		kb.press("Backspace");
		kb.press("m");
		
		page.focus("#contactReason");
		kb.press("ArrowDown");
		kb.press("ArrowDown");
	}
	
	
}


