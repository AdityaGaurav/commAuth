package com.imaginea.base;

import org.openqa.selenium.WebDriver;

public class Driver {
	private String browserName;
	private String browserVersion;
	private int ipNumber;
	private WebDriver driver;

	public Driver(WebDriver driver, String browserName, String browserVersion, int ipNumber) {
		this.driver = driver;
		this.browserName = browserName;
		this.browserVersion = browserVersion;
		this.ipNumber = ipNumber;

	}

}
