package com.steps;

import com.bizfunction.AmazonBizz;
import com.utilities.TestDataReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmazonSteps {

	TestDataReader testdatareader = new TestDataReader();
	AmazonBizz amazonBizz = new AmazonBizz();

	@Given("user launch amazon application")
	public void launchApplication() throws InterruptedException {
		amazonBizz.launchApplication();
	}

	@When("user mouseover the language dropdown")
	public void MouseOverLn() throws InterruptedException {
		amazonBizz.mouseOverLanguageDD();
	}

	@When("user enters text {string} into searchbox and clicks on search")
	public void Search(String text) throws InterruptedException {
		amazonBizz.EnterTextAndSearch(text);
	}

	@Then("validates all the items fetched have same text {string}")
	public void ValidateProd(String text) throws InterruptedException {
		amazonBizz.ValidatetextOfProducts(text);
	}

}