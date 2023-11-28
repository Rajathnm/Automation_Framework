package com.bizfunction;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.utilities.DriverFactory;
import com.utilities.TestDataReader;
import com.utilities.ThreadConstant;

public class CommonFunction {

	public WebDriver driver = DriverFactory.getDriver();
	WebElement element;
	WebElement ex;
	private static final Logger logger = Logger.getLogger(CommonFunction.class);
	TestDataReader testdatareader = new TestDataReader();
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public WebElement getElement() {
		return this.element;
	}

	public void explicitWait(String element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
	}

	public WebElement waitForElement(WebDriver driver, WebElement element) throws InterruptedException {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(25))
				.pollingEvery(Duration.ofMillis(1000)).ignoring(ElementClickInterceptedException.class)
				.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElemenToBetClickable(WebDriver driver, WebElement element) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(1000)).ignoring(ElementClickInterceptedException.class)
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void WaitForLazyLoaderToDisabled() throws InterruptedException {

		Thread.sleep(1000);
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofMillis(500));

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='hidden spinner center']")));
		} catch (Exception E) {

			logger.info("Had a exception while trying waiting for lazy loader to disappear");

		}
	}

	public void validatefirstTextOnScreen(String fieldname) throws InterruptedException {

		WebElement elemen = waitForElement(driver,
				driver.findElement(By.xpath("(//*[contains(text(),\"" + fieldname + "\")])")));
		waitForElemenToBetClickable(driver, elemen);
		try {
			Assert.assertTrue(waitForElement(driver, elemen).isDisplayed(), fieldname + " is displayed on page");
			logger.info(fieldname + " is visible on screen");
		} catch (Exception E) {
			Thread.sleep(2000);
			Assert.assertTrue(waitForElement(driver, elemen).isDisplayed(), fieldname + " is displayed on page");
			logger.info(fieldname + " is visible on screen");
		}
	}

	public void validateThirdTextOnScreen(String fieldname) throws InterruptedException {

		WebElement elemen = driver.findElement(By.xpath("(//*[contains(text(),\"" + fieldname + "\")])[3]"));
		try {
			Assert.assertTrue(waitForElement(driver, elemen).isDisplayed(), fieldname + " is displayed on page");
			logger.info(fieldname + " is visible on screen");
		} catch (Exception EE) {
			Thread.sleep(ThreadConstant.VERY_LONG_WAIT);
			Assert.assertTrue(waitForElement(driver, elemen).isDisplayed(), fieldname + " is displayed on page");
			logger.info(fieldname + " is visible on screen");

		}
	}

	public void validateSecondTextOnScreen(String fieldname) throws InterruptedException {
		WebElement elemen = driver.findElement(By.xpath("(//*[contains(text(),\"" + fieldname + "\")])[2]"));
		Assert.assertTrue(waitForElement(driver, elemen).isDisplayed(), fieldname + " is displayed on page");
		logger.info(fieldname + " is visible on screen");
	}

	public void validateNoSuchTextOnScreen(String fieldname) throws InterruptedException {

		try {
			WebElement elemen = driver.findElement(By.xpath("(//*[contains(text(),\"" + fieldname + "\")])[2]"));
			Assert.assertTrue(false,
					elemen + " is visible on screen but you are validating this element should not be visible");

		} catch (Exception ex) {

			logger.info(fieldname + " is not visible on screen as expected hence we have exception " + ex.getMessage());

		}

	}

	public void validateNoExactTextOnScreen(String fieldname) throws InterruptedException {

		try {
			WebElement elemen = driver.findElement(By.xpath("//*[text()='" + fieldname + "']"));
			Assert.assertTrue(false,
					elemen + " is visible on screen but you are validating this element should not be visible");

		} catch (Exception ex) {

			logger.info(fieldname + " is not visible on screen as expected hence we have exception " + ex.getMessage());

		}

	}

	public void validateExactTextOnScreen(String fieldname) throws InterruptedException {
		WebElement element = waitForElement(driver, driver.findElement(By.xpath("//*[text()='" + fieldname + "']")));
		try {

			Assert.assertTrue(element.isDisplayed(), "Text '" + element.getText() + "' is visible on screen");
			logger.info("Text '" + element.getText() + "' is visible on screen");
		} catch (Exception ex) {
			Thread.sleep(3000);
			Assert.assertTrue(element.isDisplayed(), "Text '" + element.getText() + "' is visible on screen");
			logger.info("Text '" + element.getText() + "' is visible on screen");

		}

	}

	public void assertTrue(boolean condition, String Message) {
		Assert.assertTrue(condition);
		logger.info(Message);
	}

	public void assertFalse(boolean condition, String Message) {
		Assert.assertFalse(condition);
		logger.info(Message);
	}

	public void enterValueintoInputField(String fieldName, String Value) throws InterruptedException {

		String InputText = testdatareader.getPropValueUsingKey(Value);
		Thread.sleep(1500);

		WebElement InputField = driver.findElement(By.xpath(
				"//*[contains(text(),\"" + fieldName + "\")]"));

		// *[contains(text(),\"" + fieldName+ "\")]/input

		if (InputText != null) {
			waitForElement(driver, InputField).clear();
			waitForElement(driver, InputField).sendKeys(InputText);
			logger.info("Entered " + InputText + " into " + fieldName);
		} else {
			waitForElement(driver, InputField).clear();
			waitForElement(driver, InputField).sendKeys(Value);
			logger.info("Entered " + Value + " into " + fieldName);

		}

	}

	public void DynamicalyClickOnButton(String ButtonName) throws InterruptedException {

		WebElement elem = driver.findElement(By.xpath("(//*[contains(text(),\"" + ButtonName + "\")])[1]"));
		waitForElement(driver, elem).click();
		logger.info("click on button " + ButtonName);

	}

	public void DynamicalyClickOnThirdButton(String ButtonName) throws InterruptedException {

		WebElement elem = driver.findElement(By.xpath("(//*[contains(text(),\"" + ButtonName + "\")])[3]"));
		waitForElement(driver, elem).click();
		logger.info("click on button " + ButtonName);

	}

	public void DynamicalyClickOnSecondButton(String ButtonName) throws InterruptedException {

		try {
			WebElement elem = driver.findElement(By.xpath("(//*[contains(text(),\"" + ButtonName + "\")])[2]"));
			waitForElement(driver, elem).click();
			logger.info("click on button " + ButtonName);
		} catch (Exception E) {
			Thread.sleep(10000);
			WebElement elem = driver.findElement(By.xpath("(//*[contains(text(),\"" + ButtonName + "\")])[2]"));
			elem.click();

			logger.info("click on button " + ButtonName);
		}

	}

	public void SelectGivenDropDownValue(String DropDown, String Value) throws InterruptedException {
		Value = Value.toLowerCase();
		WebElement elem = driver.findElement(By.xpath("(//label[contains(text(),\"" + DropDown + "\")])[1]/../select"));
		waitForElemenToBetClickable(driver, elem).click();
		List<WebElement> dropDownValues = driver
				.findElements(By.xpath("(//label[contains(text(),\"" + DropDown + "\")])[1]/../select/option"));

		for (WebElement we : dropDownValues) {
			if (we.getText().toLowerCase().trim().contains(Value)) {
				waitForElemenToBetClickable(driver, we).click();
				logger.info(Value + " selected for the dropdown " + DropDown);
			}

		}

	}

	
	
	public void ScrollToTopOfUI() {
		js.executeScript("window.scrollTo(0, 0);");

	}

	public void SleepForTen(String sleepTime) throws InterruptedException {
		long SleepTime = Long.parseLong(sleepTime);
		Thread.sleep(SleepTime);

	}

	public void SelectDateGiven(String DateField, String Date) throws InterruptedException {
		WebElement DynamicInputField = driver.findElement(By.xpath(
				"//*[contains(text(),\"" + DateField + "\")]/../../p-calendar/span/input | //*[contains(text(),\""
						+ DateField + "\")]/../p-calendar/span/input "));
		DynamicInputField.click();
		Thread.sleep(1000);
		if (Date.equalsIgnoreCase("CurrentDate")) {

			List<WebElement> DateList = driver.findElements(By.xpath("//*[contains(text(),\"" + DateField
					+ "\")]/../../p-calendar/span/div/div/div[2]/table/tbody/tr/td |//*[contains(text(),\"" + DateField
					+ "\")]/../p-calendar/span/div/div/div[2]/table/tbody/tr/td "));
			for (WebElement i : DateList) {

				if (i.getAttribute("class").contains("today")) {
					i.click();
					break;
				}
			}

		} else {
			DynamicInputField.sendKeys(Date);
		}

	}

}