package inputCustNo
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



class test {

	@Given("Users go into web (.*)")
	def users_go_into_web (String web) {
		WebUI.openBrowser(web)
		WebUI.maximizeWindow()
	}

	@When("Users login input username (.*) and password (.*)")
	def users_login_input_username_and_password (String username, String password) {
		WebUI.setText(findTestObject('Object Repository/txtUsername'), username)
		WebUI.setText(findTestObject('Object Repository/txtPassword'), password)
		WebUI.click(findTestObject('Object Repository/btnLogin'))
	}

	@When("Users login input invalid username (.*) and password (.*)")
	def users_login_input_invalid_username_and_password (String username, String password) {
		WebUI.setText(findTestObject('Object Repository/txtUsername'), username)
		WebUI.setText(findTestObject('Object Repository/txtPassword'), password)
		WebUI.click(findTestObject('Object Repository/btnLogin'))
	}

	@And("Users go to menu")
	def users_go_to_menu() {
		WebUI.waitForPageLoad(50)
		WebUI.click(findTestObject('Object Repository/menuMembership'))
		WebUI.click(findTestObject('Object Repository/submenubrowseMember'))
	}

	@And("Input CustNo (.*)")
	def input_CustNo (String CustNo) {
		WebUI.setText(findTestObject('Object Repository/txtSearch'), CustNo)
	}

	@And("User validate CustNo (.*) and TextCustNo")
	def user_validate_CustNo_and_TextCustNo (String CustNo) {
		String realCustNo = WebUI.getText(findTestObject('Object Repository/custMainNo'))
		if (CustNo == realCustNo) {
			KeywordUtil.markPassed('Passed, Data is '+CustNo+ ' vs '+realCustNo+' . Expected')
		} else {
			KeywordUtil.markFailedAndStop('Failed, Data is '+CustNo+ ' vs '+realCustNo+' . Not Expected')
		}
	}

	@Then("User Logout")
	def user_Logout () {
		WebUI.waitForPageLoad(50)
		WebUI.click(findTestObject('Object Repository/btnKeluar'))
		WebUI.closeBrowser()
	}

	@Then("User login succesfully (.*)")
	def user_login_succesfully (String url) {
		WebUI.delay(1)
		url= WebUI.getUrl()
		println(url)
		WebUI.verifyMatch(url, 'http://fgc-cms-middle-fgc-test.apps.ocpnp.fifgroup.co.id/', false)
	}

	@Then("User should not be able to login succesfully")
	def user_should_not_be_able_to_login_succesfully () {
		WebUI.verifyTextPresent('Please try again or contact admin , Error Code 401', false)
		WebUI.closeBrowser()
	}
}