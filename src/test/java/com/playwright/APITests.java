package com.playwright;

import base.BaseClass;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
	
	@Test
	public void monitorHttpTrafficDemo() {
		
		//create request and response handlers----------------
//		page.onRequest(request -> System.out.println(">> " + request.method() + " " + request.url()));
//		page.onResponse(response -> System.out.println(">> " + response.status()));
		
		//perform request
		//page.navigate("https://playwright.dev");
		
		//-----------------------------------------------
		
		//validate status code example---------------------------------
		List<Integer> responses = new ArrayList<>();
		page.onResponse(response -> responses.add(response.status()));
		page.navigate("https://playwright.dev");
		System.out.println(responses);
		
		//using for loop
//		for (Integer response : responses) {
//			Assert.assertTrue(response >= 200 && response <= 299);
//		}
		
		
		//using stream-----------------------------------------
		boolean foundMatch = responses.stream().anyMatch(i -> i < 200 || i >= 300);
		Assert.assertFalse(foundMatch);
		
		//----------------------------------------------------------------
		
	
		
	}
	
	@Test
	public void monitorHttpTrafficDemo2() {
		
		//validate status code example---------------------------------
		List<Boolean> statusCodeIsOK = new ArrayList<>();
		page.onResponse(response -> statusCodeIsOK.add(response.ok()));
		
		page.navigate("https://playwright.dev");
		System.out.println(statusCodeIsOK);
		
		//using for loop
//		for (Integer response : responses) {
//			Assert.assertTrue(response >= 200 && response <= 299);
//		}
		
		
		//using stream-----------------------------------------
		boolean foundMatch = statusCodeIsOK.stream().anyMatch(statusCodeOK -> statusCodeOK == false);
		Assert.assertFalse(foundMatch);
		
		//----------------------------------------------------------------
		
		
		
		
	}
}
