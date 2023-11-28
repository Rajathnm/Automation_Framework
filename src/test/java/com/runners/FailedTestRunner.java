package com.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "@target/failedrerun.txt" },

		glue = { "com.steps", "com.utilities" }, plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "pretty",
				"html:target/cucumber-report/FailedTestcucumber.html", "rerun:target/failedrerun.txt" }, monochrome = true)
public class FailedTestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();

	}

}