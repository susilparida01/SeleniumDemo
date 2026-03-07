package com.simplilearn.SeleniumDemo.utils;

import com.aventstack.extentreports.ExtentTest;

public final class ReportPortal {
	private ReportPortal() {}
	
	private static final ThreadLocal<ExtentTest> testRef = new ThreadLocal<>();
	
	public static void setTest(ExtentTest t) { testRef.set(t);}
	public static ExtentTest getTest() { return testRef.get();}	
	public static void remove() {testRef.remove();}

}