package com.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Hooks {
	private WebDriver driver;

	@Before
	public void setUp() {
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@After
	public void tearDown() {
		 DriverFactory.quitDriver();

	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		String screenshotName = scenario.getName().replaceAll(" ", "_") + "_Step_" + getStepName(scenario);
		ScreenShot.captureScreenshot(driver, screenshotName);

		byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshotBytes, "image/png", screenshotName);

		// Delete the screenshot file after attaching it
		File screenshotFile = new File("./screenshots/" + screenshotName + ".png");
		screenshotFile.delete();
	}

	private String getStepName(Scenario scenario) {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		for (StackTraceElement stackTraceElement : stackTraceElements) {
			if (stackTraceElement.getClassName().startsWith("io.cucumber")) {
				return stackTraceElement.getMethodName();
			}
		}
		return "";
	}


}
