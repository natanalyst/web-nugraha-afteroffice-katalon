package form
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys


import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class FormStep {

	TestObject firstName = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@id='firstname']")
	TestObject lastName = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@id='lastname']")
	TestObject userEmail = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@id='userEmail']")
	TestObject radioBtn = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@id='gender-radio-1']")
	TestObject userNumber = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@id='userNumber']")
	TestObject dateOfBirthInput = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@id='dateOfBirthInput']")
	TestObject subjectsInput= new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@type='text' and @aria-autocomplete='list']")
	TestObject hobbiesCheckbox = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@id='hobbies-checkbox-1']")
	TestObject uploadPicture = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@id='uploadPicture']")
	TestObject address = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//textarea[@id='currentAddress']")
	TestObject state = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//svg[contains(@class,'css-19bqh2r')]")
	TestObject city = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='city']//div[contains(text(),'Select City')]")
	TestObject statePick = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[contains(@class,'option') and text()='Haryana']")
	TestObject cityPick = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='react-select-4-option-0' and text()='Karnal']")
	TestObject submitBtn = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[@id='submit']")
	TestObject doneBtn = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[@id='closeLargeModal']")
	
	@Given("User masuk ke web")
	def enter() {
		WebUI.openBrowser("")
		WebUI.navigateToUrl(GlobalVariable.appURL)
	}

	@When("User melakukan pengisian form")
	def fillForm() {
		
		WebUI.setText(firstName, "John")
		WebUI.setText(lastName, "Doe")
		WebUI.setText(userEmail, "john.doe@example.com")
		WebUI.click(radioBtn)
		WebUI.setText(userNumber, "081234567890")
		WebUI.click(dateOfBirthInput)
		WebUI.sendKeys(dateOfBirthInput, Keys.chord(Keys.CONTROL, "a"))
		WebUI.sendKeys(dateOfBirthInput, "01 Jan 1995" + Keys.ENTER)
		WebUI.setText(subjectsInput, "Maths")
		WebUI.sendKeys(subjectsInput, Keys.chord(Keys.ENTER))
		WebUI.click(hobbiesCheckbox)
		String filePath = "/Users/entrustinv132/Downloads/bruno-figueiredo-r6WPOp_q_xM-unsplash.jpg"
		WebUI.uploadFile(uploadPicture, filePath)
		WebUI.setText(address, "Jl. Jalan No.123, Jakarta")
		WebUI.click(state)
		WebUI.click(statePick)
		WebUI.click(city)
		WebUI.click(cityPick)
		WebUI.click(submitBtn)
		WebUI.waitForElementVisible(doneBtn, 5)
		WebUI.click(doneBtn)
	}

	@Then("User sukses isi form")
	def verify() {
		if(WebUI.verifyTextPresent("Thanks for submitting the form", FailureHandling.OPTIONAL)) {
			KeywordUtil.markPassed("Fill Form Success")
		}
		
		else{
			KeywordUtil.markFailed("Failed")
		}
		
	}
}