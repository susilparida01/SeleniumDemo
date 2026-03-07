package com.simplilearn.SeleniumDemo.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.simplilearn.SeleniumDemo.pages.DashboardPage;
import com.simplilearn.SeleniumDemo.pages.LoginPage;

public class Login_AdminUser extends BaseTest {
	
	@Test(priority = 1, description = "Valid Admin login should navigate to dashboard")
	public void testValidLogin() {
		LoginPage login = new LoginPage(driver);		
		DashboardPage dashboard = login.loginValidUser(driver, prop.getProperty("username"), prop.getProperty("password"));		
		AssertJUnit.assertTrue(dashboard.getDashboardHeader().contains("Dashboard"));
	}
	
	@Test(priority = 2, description = "Invalid login should display error message")
	public void testInvalidLogin(){
		
		LoginPage login = new LoginPage(driver);		
		String errorMessage = login.loginInvalidUser(driver, "Admin", "wrongadmin123");		
		Assert.assertEquals(errorMessage, "Invalid credentials", "Expected 'Invalid credentials' message, but got: " +errorMessage);		
	}
	
	

}
