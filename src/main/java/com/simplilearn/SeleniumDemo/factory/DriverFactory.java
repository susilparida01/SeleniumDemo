package com.simplilearn.SeleniumDemo.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public WebDriver initDriver(String browser, boolean headless) {
		
		if (browser == null) browser = "chrome";
		
		switch (browser.toLowerCase()) {
		
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			if (headless) co.addArguments("--headless=new");
			co.addArguments("--start-maximized");
			tlDriver.set(new ChromeDriver(co));
			break;
		
		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions eo = new EdgeOptions();
			if (headless) eo.addArguments("--headless=new");
			eo.addArguments("--start-maximized");
			tlDriver.set(new EdgeDriver(eo));
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fo = new FirefoxOptions();
			if (headless) fo.addArguments("--headless=new");
			fo.addArguments("--start-maximized");
			tlDriver.set(new FirefoxDriver(fo));
			break;
			
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		
		}
		
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public void quitDriver() {
		if (getDriver() != null) {
			
			getDriver().quit();
			tlDriver.remove();
		}
	}

}
