package com.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import base.BaseClass;

public class ExtractingText extends BaseClass {
	
	//Locators - useful in interacting to a control multiple times
		//Captures logic to capture element
		//ties an element to a variable
		
	
	

	@Test
	public void getTextExample() {
		page.navigate(baseURL);
		
		System.out.println(page.textContent("#hero-banner"));
		System.out.println(page.innerText("#hero-banner"));
		System.out.println(page.innerHTML("#hero-banner"));
		System.out.println(page.getAttribute("img", "alt"));
	}


}
