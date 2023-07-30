package com.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import base.BaseClass;

public class HandleDialogs extends BaseClass{

	
	//alert dialog - can be automatically handled by playwright
	@Test
	public void alertTest() {
		page.navigate(baseURL);
		page.fill("#donation", "50");
		 
		//lambda expression - "given a dialog, then dialog.accept"
//		page.onDialog(dialog -> {
//			//static wait to see the dialog
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			dialog.accept();
//			
//		});
		
		//even if i comment out the handler, playwright will automatically handle the dialogs
	
		
		
		page.click("#submit-donation");
		Assertions.assertTrue(page.isVisible("text=Thank you!"));
	
	}
	
	//confirm dialog box - cannot automatically handled by playwright
	@Test
	public void confirmTestAccept() {
		page.navigate(baseURL);
		page.fill("#donation", "200");
		 
		//lambda expression - "given a dialog, then dialog.accept"
		page.onDialog(dialog -> dialog.accept());
		
		page.click("#submit-donation");
		Assertions.assertTrue(page.isVisible("text=Thanks for confirming!"));
	
	}
	
	@Test
	public void confirmTestDismiss() {
		page.navigate(baseURL);
		page.fill("#donation", "200");
		 
		//lambda expression - "given a dialog, then dialog.accept"
		page.onDialog(dialog -> dialog.dismiss());
		
		page.click("#submit-donation");
		Assertions.assertTrue(page.isVisible("text=It's OK"));
	
	}
	
	//Prompt with text box
	@Test
	public void promptTest() {
		page.navigate(baseURL);
		page.fill("#donation", "2000");
		 
		//lambda expression - "given a dialog, then dialog.accept"
		page.onDialog(dialog -> dialog.accept("Yes"));
		
		page.click("#submit-donation");
		Assertions.assertTrue(page.isVisible("text=Thank you for your generosity"));
	
	}
	
}
