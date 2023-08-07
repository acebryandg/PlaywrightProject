package com.playwright;

import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing.StartOptions;
import com.microsoft.playwright.Tracing.StopOptions;
import com.microsoft.playwright.options.ViewportSize;

public class TraceViewerDemo {

	
	Playwright pw;
	Browser browser;
	BrowserContext ctx;
	Page page;
	
	@BeforeEach
	public void initialize() {
		pw = Playwright.create();
		browser = pw.chromium().launch(new LaunchOptions()
				.setHeadless(false));
		ctx = browser.newContext();
		page = ctx.newPage();
		
		ctx.tracing().start(new StartOptions()
				.setScreenshots(true)
				.setSnapshots(true));
	}
	
	@AfterEach
	public void cleanup() {
		ctx.tracing().stop(new StopOptions()
				.setPath(Paths.get("trace.zip")));
	}
	
	
	@Test
	public void traceViewerDemo() {
		page.navigate("https://playwright.dev/java/");
		page.click("text=Get Started");
		page.click("text=Guides");
		page.click("text=Trace Viewer");
		Assertions.assertTrue(page.isVisible("text=Recording a trace"));
		
		
	}
}
