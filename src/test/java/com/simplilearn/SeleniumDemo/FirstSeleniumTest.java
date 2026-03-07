package com.simplilearn.SeleniumDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTest {

	public static void main(String[] args) {
		
		// Initialize browser by creating driver object
		WebDriver driver = new ChromeDriver();
		
		//Open website
		driver.get("https://www.google.com/");
		
		// print title
		System.out.println("Page Title: " + driver.getTitle());
		
		
		// Close browser
		driver.quit();		

	}

}
