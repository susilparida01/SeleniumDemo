package com.simplilearn.SeleniumDemo.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	private final Properties prop = new Properties();
	
	public Properties initProp() {
		
		try (InputStream is = getClass().getClassLoader().getResourceAsStream("config/config.properties")){
			
			if (is != null) {
				prop.load(is);
			}else {
				throw new RuntimeException("config.properties not found in resources/config");
			}
			
			overrideIfPresent("baseUrl");
			overrideIfPresent("username");
			overrideIfPresent("password");
			overrideIfPresent("browser");
			overrideIfPresent("headless");
			
			return prop;
			
		}catch (Exception e) {
			throw new RuntimeException("Failed to load config.properties");
		}		
		
	}
	
	private void overrideIfPresent(String key) {
		
		String sys = System.getProperty(key);
		
		if (sys != null && !sys.isBlank()) {
			prop.setProperty(key, sys);
		}
	}

}
