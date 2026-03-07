package com.simplilearn.SeleniumDemo.utils;

import com.aventstack.extentreports.ExtentTest;

public final class Log {
	
	private Log() {}
	
	public static void step(String message) {
		ExtentTest t = ReportPortal.getTest();  // We need to delegate to the accessor
		if (t != null) t.info(message);
		else System.out.println("[STEP] " + message);
		
	}
	
	public static void pass(String message) {
		ExtentTest t = ReportPortal.getTest();
		if (t != null) t.pass(message);
		else System.out.println("[PASS] " + message);
		
	}
	
	public static void warn(String message) {
		ExtentTest t = ReportPortal.getTest();
		if (t != null) t.warning(message);
		else System.out.println("[WARN] " + message);
		
	}
	
	public static void fail(String message) {
		ExtentTest t = ReportPortal.getTest();
		if (t != null) t.fail(message);
		else System.out.println("[FAIL] " + message);
		
	}

}
