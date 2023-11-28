package com.bizfunction;

import java.io.File;
import org.apache.commons.io.FileUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.github.dockerjava.api.model.Driver;
import com.pageobjects.AmazonPage;
import com.utilities.DriverFactory;
import com.utilities.ReadConfig;
import com.utilities.TestDataReader;

import ch.qos.logback.core.util.FileUtil;

public class AmazonBizz {

	public WebDriver driver = DriverFactory.getDriver();
	ReadConfig ReadConfig = new ReadConfig();

	CommonFunction CommonFunction = new CommonFunction();
	private static final Logger logger = Logger.getLogger(AmazonBizz.class);
	TestDataReader testdatareader = new TestDataReader();
	AmazonPage amazonPage = new AmazonPage(driver);
	Actions action = new Actions(driver);

	public void launchApplication() throws InterruptedException {

		PropertyConfigurator.configure("log4j.properties");
		String env = ReadConfig.getPropValueUsingKey("Env") == null ? "" : ReadConfig.getPropValueUsingKey("Env");

		if (env.equalsIgnoreCase("test")) {
			String url = ReadConfig.getPropValueUsingKey("baseURLtest") == null ? ""
					: ReadConfig.getPropValueUsingKey("baseURLtest");
			driver.get(url);
			logger.info("Successfully navigated to dev environment");
		}
		if (env.equalsIgnoreCase("test2")) {
			String url = ReadConfig.getPropValueUsingKey("baseURLtest2") == null ? ""
					: ReadConfig.getPropValueUsingKey("baseURLtest2");
			driver.get(url);
		}
		if (!(env.equalsIgnoreCase("test") || env.equalsIgnoreCase("test2"))) {
			logger.info("You have entered " + env + " in config.properties which is invalid. please enter DEV or INT");
		}

	}

	public void mouseOverLanguageDD() throws InterruptedException {
		action.moveToElement(amazonPage.getLanguageDropdown()).build().perform();
	}

	public void EnterTextAndSearch(String TextInput) throws InterruptedException {

		CommonFunction.waitForElement(driver, amazonPage.getSearchInputBox()).sendKeys(TextInput);
		CommonFunction.waitForElement(driver, amazonPage.getSearchBtn()).click();

	}

	public void ValidatetextOfProducts(String TextInput) throws InterruptedException {
		List<WebElement> Products = amazonPage.getProductsNameList();
		TextInput = TextInput.toLowerCase();

		for (WebElement i : Products) {
			String productText = i.getText().trim().toLowerCase();
			CommonFunction.assertTrue(productText.contains(TextInput), "Product contain text as expected");

		}

	}

}
