package com.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AmazonPage {

	WebDriver driver;

	public AmazonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@id='icp-nav-flyout']/span/span[2]/div")
	private WebElement LanguageDropdown;

	@FindBy(how = How.XPATH, using = "//input[@aria-label='Search Amazon.in']")
	private WebElement SearchInputBox;

	@FindBy(how = How.XPATH, using = "//input[@id='nav-search-submit-button']")
	private WebElement SearchBtn;
	
	@FindBys(value = {@FindBy(xpath="//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div/div/div/span/div/div/div/div[2]/div/div/div[1]/h2/a")})
	private List<WebElement> ProductsName;

	public WebElement getLanguageDropdown() {
		return LanguageDropdown;
	}

	public WebElement getSearchInputBox() {
		return SearchInputBox;
	}

	public WebElement getSearchBtn() {
		return SearchBtn;
	}
	

	public List<WebElement> getProductsNameList() {
		return ProductsName;
	}
}
