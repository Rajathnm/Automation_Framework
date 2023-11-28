package com.utilities;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	static ReadConfig readconfig = new ReadConfig();
	private static final Logger logger = Logger.getLogger(DriverFactory.class);

	public static WebDriver getDriver() {

		if (driverThreadLocal.get() == null) {
			String BrowserName = readconfig.getPropValueUsingKey("Browser") == null ? ""
					: readconfig.getPropValueUsingKey("Browser");
			BrowserName = BrowserName.toLowerCase().trim();
			
			ChromeOptions chromeOptions = new ChromeOptions();
			
			DesiredCapabilities Chromecapabilities = new DesiredCapabilities();
			Chromecapabilities.setCapability("acceptSslCerts", true);
			Chromecapabilities.setCapability("acceptInsecureCerts", true);

			FirefoxOptions FirefoxOptions = new FirefoxOptions();
			DesiredCapabilities Firefoxcapabilities = new DesiredCapabilities();
			Firefoxcapabilities.setCapability("acceptSslCerts", true);
			Firefoxcapabilities.setCapability("acceptInsecureCerts", true);

			if (BrowserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();

				if (readconfig.getPropValueUsingKey("headless").equalsIgnoreCase("true")) {
					 chromeOptions.addArguments("--headless");
					chromeOptions.addArguments("--disable-gpu");
					chromeOptions.addArguments("--window-size=1920x1080");
					chromeOptions.addArguments("--no-sandbox"); 
				}
				chromeOptions.merge(Chromecapabilities);
				driverThreadLocal.set(new ChromeDriver(chromeOptions));
			}

			if (BrowserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();

				FirefoxOptions firefoxOptions = new FirefoxOptions();
				if (readconfig.getPropValueUsingKey("headless").equalsIgnoreCase("true")) {
					firefoxOptions.addArguments("--headless");
					firefoxOptions.addArguments("--disable-gpu");
					firefoxOptions.addArguments("--window-size=1920x1080");					
				}
				FirefoxOptions.merge(Firefoxcapabilities);
				driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
			}
			if (!(BrowserName.equalsIgnoreCase("chrome") || BrowserName.equalsIgnoreCase("Firefox"))) {
				logger.error(
						"Looks like browser name is incorrect, Please check and make necessary change in config.properties");
				throw new IllegalArgumentException("Unsupported browser: " + BrowserName);
			}

		}

		return driverThreadLocal.get();
	}

	public static void quitDriver() {
		if (driverThreadLocal.get() != null) {
			driverThreadLocal.get().quit();
			driverThreadLocal.remove();
		}
	}
}
