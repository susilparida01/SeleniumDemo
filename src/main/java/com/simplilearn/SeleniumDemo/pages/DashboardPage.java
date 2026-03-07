package com.simplilearn.SeleniumDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage{
	
	//Dashboard label text
	@FindBy(xpath = "//h6[normalize-space()='Dashboard']")
	public WebElement dashboard_label;
	
	//User Account Menu text
	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	public WebElement user_name_text;
	
	//User Account Menu
	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
	public WebElement user_account_menu;
	
	
	//User Logout
	@FindBy(xpath="//li/a[normalize-space()='Logout']")       ////a[text()="Logout"]  ,  //a[normalize-space()='Logout']
	public WebElement logout;
	
	//Declaration of quick launch element in dashboad page
	@FindBy(xpath = "//button[@title='Assign Leave']")
	public WebElement assign_leave;
	
	@FindBy(xpath = "//button[@title='Leave List']")
	public WebElement leave_list;
	
	@FindBy(xpath = "//button[@title='Timesheets']")
	public WebElement timesheets;
	
	@FindBy(xpath = "//button[@title='Apply Leave']")
	public WebElement apply_leave;
	
	@FindBy(xpath = "//button[@title='My Leave']")
	public WebElement my_leave;
	
	@FindBy(xpath = "//button[@title='My Timesheet']")
	public WebElement my_timesheet;	
	
	// Important section
	public DashboardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	//Page action methods	
	public String getDashboardHeader() {
		waitForVisibility(dashboard_label);
		return getText(dashboard_label);
	}
	
	public void clickAssignLeave() {
		safeClick(assign_leave);
	}
	
	public void clickLeaveList() {
		safeClick(leave_list);
	}
	
	public void clickTimesheets() {
		safeClick(timesheets);
	}
	
	public void clickApplyLeave() {
		safeClick(apply_leave);
	}
	
	public void clickMyLeave() {
		safeClick(my_leave);
	}
	
	public void clickMyTimesheet() {
		safeClick(my_timesheet);
	}
	

}