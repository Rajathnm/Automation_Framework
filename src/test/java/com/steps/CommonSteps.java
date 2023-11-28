package com.steps;

import java.util.List;
import java.util.Map;

import com.bizfunction.CommonFunction;
import com.utilities.TestDataReader;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps {

	TestDataReader testdataReader = new TestDataReader();
	CommonFunction commonfunction = new CommonFunction();

	@Then("user validates below text on the screen at first occurance")
	public void validateTextOnScreen_atFirstOccurance(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> fieldName : dataList) {
			String fieldname = fieldName.get("fieldName");
			commonfunction.validatefirstTextOnScreen(fieldname);

		}
	}

	@Then("user validates below exact text on the screen at first occurance")
	public void validateEaxctTextOnScreen(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> fieldName : dataList) {
			String fieldname = fieldName.get("fieldName");
			commonfunction.validateExactTextOnScreen(fieldname);

		}
	}

	@Then("user validates below text on the screen at third occurance")
	public void validateTextOnScreen_atThirdOccurance(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> fieldName : dataList) {
			String fieldname = fieldName.get("fieldName");
			commonfunction.validateThirdTextOnScreen(fieldname);

		}
	}

	@Then("user validates below text on the screen at second occurance")
	public void validateTextOnScreen_atSecondOccurance(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> fieldName : dataList) {
			String fieldname = fieldName.get("fieldName");
			commonfunction.validateSecondTextOnScreen(fieldname);
		}
	}

	@Then("user validates below text is not visible on the screen")
	public void validateNoSuchTextOnUI(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> fieldName : dataList) {
			String fieldname = fieldName.get("fieldName");
			commonfunction.validateNoSuchTextOnScreen(fieldname);
		}
	}

	@When("user enters below input into the respective input field")
	public void enterValueintoInput(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> data : dataList) {
			String InputField = data.get("InputField");
			String Value = data.get("Value");
			commonfunction.enterValueintoInputField(InputField, Value);
		}
	}

	@When("user clicks on below button on UI")
	public void ClickOnButton(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> data : dataList) {
			String ButtonName = data.get("ButtonName");
			commonfunction.DynamicalyClickOnButton(ButtonName);
		}
	}

	@When("user clicks on below button on UI present on second occurance")
	public void ClickOnSecondButton(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> data : dataList) {
			String ButtonName = data.get("ButtonName");
			commonfunction.DynamicalyClickOnSecondButton(ButtonName);
		}
	}

	@When("user selects below value from a given dropdown")
	public void SelectDropDownValue(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> data : dataList) {
			String DropDownName = data.get("DropDown");
			String DropDownValue = data.get("Value");
			commonfunction.SelectGivenDropDownValue(DropDownName, DropDownValue);

		}
	}

	@When("user scrolls to top of the screen")
	public void ScrollToTop() {
		commonfunction.ScrollToTopOfUI();
	}

	@And("user waits for page to load for given mili seconds")
	public void sleep(DataTable dataTable) throws InterruptedException {
		List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> data : dataList) {
			String sleepTime = data.get("WaitTime");
			commonfunction.SleepForTen(sleepTime);
		}

	}

}