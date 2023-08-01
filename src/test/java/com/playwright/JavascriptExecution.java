package com.playwright;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

public class JavascriptExecution extends BaseClass {

	//Object o = evaluate(expression)
	//Object o = evalOnSelector(selector, expression)
	//Object o = evalOnSelectorAll(selector, expression)
	
	@Test
	public void localStorage() {

		page.navigate(baseURL);
		Object obj = page.evaluate("()=> window.localStorage.getItem('clapped')");
		Assertions.assertNull(obj);
		
		page.click("#clap-image");
		String obj2 = (String) page.evaluate("()=> window.localStorage.getItem('clapped')");
		Assertions.assertTrue(Boolean.parseBoolean(obj2));
		
	}
	
	@Test
	public void evalOnSelector() {

		page.navigate(baseURL);
		page.evalOnSelector("#hero-banner", "e => e.remove()");
		assertFalse(page.isVisible("#hero-banner"));
		
	}
	
	@Test
	public void evalOnSelectorAll() {

		page.navigate(baseURL);
		Object obj = page.evalOnSelectorAll(".feature", "e => e.length");
		
		
		assertEquals(3, obj);
		
	}

	

	
	
}


