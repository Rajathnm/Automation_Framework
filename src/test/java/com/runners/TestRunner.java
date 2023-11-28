package com.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resource/feature",
		 tags = "@Amazon",
		glue = { "com.steps", "com.utilities" }, plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","pretty",
				"html:target/cucumber-report/cucumber.html", "rerun:target/failedrerun.txt" })
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();

	}

}