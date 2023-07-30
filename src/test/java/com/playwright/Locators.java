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

public class Locators extends BaseClass {
	
	//Locators - useful in interacting to a control multiple times
		//Captures logic to capture element
		//ties an element to a variable
		
	
	

	@Test
	public void locatorsExample() {
		page.navigate(baseURL);
		
		//locate all input elements with class form control
		Locator input = page.locator(".form-control");
		
		input.first().fill("first");
		input.last().fill("last");
		input.nth(1).fill("second");

	}


}
