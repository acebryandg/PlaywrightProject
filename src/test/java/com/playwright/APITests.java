package com.playwright;

import base.BaseClass;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Request;
import com.microsoft.playwright.Response;

public class APITests extends BaseClass{

	@Test
	public void responseAPIDemo() {
		Response r = page.navigate(baseURL);
		System.out.println(r.status()); //will be 0 coz the home page is a static file
		
		Response r2 = page.navigate("https://playwright.dev");
		System.out.println(r2.url());
		System.out.println(r2.status());
		System.out.println(r2.ok());
		System.out.println(r2.headers());
		System.out.println(r2.body());
		
		//converted byte array
		System.out.println("\nconverted byte array: ");
		System.out.println(new String(r2.body(), StandardCharsets.UTF_8));
		
		//convenience method
		System.out.println("\nPlaywright convenience method: ");
		System.out.println(r2.text());
		
	}
	
	@Test
	public void requestAPIDemo() {
		//access the request through the response
		Response response = page.navigate("https://playwright.dev");
		Request request = response.request();
		

		System.out.println("\n" + request.headers());
		System.out.println("\n" + request.postData()); //payload or request body
		System.out.println("\n" + request.method()); //used method
		
		
	}
}
