package com.playwright;

import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.NavigateOptions;

import base.BaseClass;

public class AuthenticationExample extends BaseClass {

	@BeforeEach
	@Override
	public void createContextAndPage() {

		System.out.println("Before Each block");
	}

	@Test
	public void auth() {
		context = browser.newContext();
		Page page = context.newPage();
		page.setViewportSize(1920, 1080);

		page.navigate("https://github.com/login");
		page.fill("#login_field", "acedeguzman");
		page.fill("#password", "Jom@lley007");
		page.click("'Sign in'");
		page.waitForURL("https://github.com/");
		Assertions.assertEquals(page.url(), "https://github.com/");

		page.click(".avatar.circle");
		// page.click("text=Signed in as acedeguzman");

		// save context state
		context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("state.json")));
	}

	@Test
	public void auth2() {

		// get context state
		context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("state.json")));

		Page page = context.newPage();
		page.navigate("https://github.com/login");

		// verify user is already logged in
		page.click(".avatar.circle");
	}
}
