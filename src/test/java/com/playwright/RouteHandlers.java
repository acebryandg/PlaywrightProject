package com.playwright;

import base.BaseClass;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Request;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.playwright.BrowserContextConfig.ViewPortSize;

public class RouteHandlers extends BaseClass{

	
	
	@Test
	public void imageBlockDemo() {
		
		//given the route that matches the pattern, abort it
		page.route("**/*.{png, jpg, jpeg, svg}", route -> route.abort());
		page.navigate("https://playwright.dev");
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("blockedImage.png")));
	}


}
