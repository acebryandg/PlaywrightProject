package base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;





public class BaseClass {
	
	public static String baseURL = "file:///" + System.getProperty("user.dir") + "\\src\\web\\home.html";
	
	protected static Playwright playwright;
	protected static Browser browser;

	protected BrowserContext context;
	protected Page page;
	
	@BeforeAll
	static void launchBrowser() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
	}
	
	@BeforeEach
	public void createContextAndPage() {
		context = browser.newContext();
		page = context.newPage();
	}
	
	@AfterEach
	public void closeContextAndPage() {
		context.close();
	}
	
	@AfterAll
	static void closeBrowser() {
		playwright.close();
	}
	
}
