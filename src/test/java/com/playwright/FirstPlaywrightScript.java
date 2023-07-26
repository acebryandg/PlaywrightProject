package com.playwright;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FirstPlaywrightScript {

	@Test
	public void firstPlaywrightScript(){
		try(Playwright playwright = Playwright.create()){
			BrowserType browserType = playwright.chromium();
			Browser browser = browserType.launch();
			Page page = browser.newPage();
			page.navigate("https://playwright.dev");
			System.out.println(page.title());
		}
	}
	
	@Test
	public void firstPlaywrightScript_Copy(){
		try(Playwright playwright = Playwright.create()){
			Page page = playwright.chromium().launch().newPage();
			page.navigate("https://playwright.dev");
			System.out.println(page.title());
		}
	}
	
	@Test
	public void browserSupport(){
		try(Playwright pw = Playwright.create()){
			
			
			List<BrowserType> browserTypes = new ArrayList<BrowserType>();
			browserTypes.add(pw.chromium());
			browserTypes.add(pw.firefox());
			browserTypes.add(pw.webkit());
				
				
			for (BrowserType type: browserTypes) {
				
				Page page = type.launch().newPage();
				page.navigate("https://whatsmybrowser.org");
				page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(type.name(), ".png")));
				
			}
		
		}
	}
}
