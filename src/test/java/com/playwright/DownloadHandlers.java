package com.playwright;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;

import base.BaseClass;

public class DownloadHandlers extends BaseClass {

	// note on downloads
	// playwright creates a temporary directory for downloads, but closes it as soon
	// as the browser context is closed

	// example 1 - onDownload
	// page.onDownload(d -> d.saveAs()); //handle what to do with the download
	// page.click("text=Download"); //trigger download

	// example 2 - waitForDownload
	// Download d = page.waitForDownload(() -> { //trigger and return the download
	// page.click("text=Download");
	// })
	// d.saveAs() //handle what to do with the download

	
	
	
	// Create new context that accepts downloads
	// Override context creation from baseclass
	@BeforeEach
	@Override
	public void createContextAndPage() {
		context = browser.newContext(new NewContextOptions().setAcceptDownloads(true));
		page = context.newPage();
		page.setViewportSize(1920, 1080);

	}

	@Test
	public void downloadTestWithHandler() {

		page.navigate(baseURL);

		// handle what to do with the download
		page.onDownload(download -> {
			System.out.println(download.path());
			download.saveAs(Paths
					.get(new File("D:\\Upskilling\\Playwright\\Playwright_SampleProject\\downloads\\downloaded.zip")
							.toURI()));
		});

		// trigger the download
		page.click("text=Download ZIP");

	}
	
	@Test
	public void downloadTestWithWaitForEvent() {

		page.navigate(baseURL);

	
		
		//trigger and capture download
		Download d = page.waitForDownload(() -> {
			page.click("text=Download ZIP");
			
		});

		//handle what to do with the download
		System.out.println(d.path());
		d.saveAs(Paths
				.get(new File("D:\\Upskilling\\Playwright\\Playwright_SampleProject\\downloads\\downloaded.zip")
						.toURI()));

	}
}
