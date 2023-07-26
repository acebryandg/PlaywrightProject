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
	
	public static String home = "file:///" + System.getProperty("user.dir") + "\\src\\web\\home.html";
	
	public static Playwright playwright;
	public static Browser browser;

	public BrowserContext context;
	public Page page;
	
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
		browser.close();
		playwright.close();
	}
	
}
