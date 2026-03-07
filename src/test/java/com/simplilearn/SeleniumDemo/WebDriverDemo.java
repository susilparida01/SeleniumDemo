package com.simplilearn.SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class WebDriverDemo {

	public static void main(String[] args) throws InterruptedException{
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		// Locate username field and enter value
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys("Admin");
		
		// Locate password field and enter value
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("admin123");
		
		// Locate login button and click
		WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
		loginBtn.click();
		Thread.sleep(2000);
		
		// Print the current page title
		System.out.println("Page Title after login: " + driver.getTitle());
		
		String actualRes = driver.findElement(By.tagName("span")).getText();
		System.out.println("Actual Result: " + actualRes);
		
		String expectedRes = "Dashboard";
		
		Assert.assertEquals(actualRes, expectedRes);
		
		// Close the browser
		driver.quit();

	}

}
