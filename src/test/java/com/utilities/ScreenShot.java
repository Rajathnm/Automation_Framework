package com.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

	WebDriver driver = DriverFactory.getDriver();
	private static final Logger logger = Logger.getLogger(ScreenShot.class);

	public static void captureScreenshot(WebDriver driver, String screenshotName) {
		try {

			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./screenshots/" + screenshotName + ".png"));
		} catch (Exception e) {
			logger.info(e.getStackTrace());
		}
	}

}
