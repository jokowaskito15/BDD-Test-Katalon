package cobacoba

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.Robot
import java.awt.event.KeyEvent
import java.util.concurrent.ConcurrentHashMap.KeySetView
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.Keys as Keys
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

public class utilities {
	@Keyword
	def login() {
		TestData excel = findTestData('Data Files/TestSauceDemo')
		WebUI.openBrowser('')
		WebUI.navigateToUrl('https://www.saucedemo.com/')
		WebUI.maximizeWindow()

		for (int i = 1; i <= excel.getRowNumbers(); i++) {
			String username = excel.getValue('Username', i)
			String password = excel.getValue('Password', i)
			String no = excel.getValue('No', i)
			String scenario = excel.getValue('Scenario', i)
			
			WebUI.sendKeys(findTestObject('Object Repository/record/Page_Swag Labs/input_Swag Labs_user-name'), Keys.chord(Keys.CONTROL,'a'))
			WebUI.sendKeys(findTestObject('Object Repository/record/Page_Swag Labs/input_Swag Labs_user-name'), Keys.chord(Keys.BACK_SPACE))
			//	WebUI.clearText(findTestObject('Object Repository/record/Page_Swag Labs/input_Swag Labs_user-name'))
			WebUI.setText(findTestObject('Object Repository/record/Page_Swag Labs/input_Swag Labs_user-name'), username)
			WebUI.sendKeys(findTestObject('Object Repository/record/Page_Swag Labs/input_Swag Labs_password'), Keys.chord(Keys.CONTROL,'a'))
			WebUI.sendKeys(findTestObject('Object Repository/record/Page_Swag Labs/input_Swag Labs_password'), Keys.chord(Keys.BACK_SPACE))
			//	WebUI.clearText(findTestObject('Object Repository/record/Page_Swag Labs/input_Swag Labs_password'))
			WebUI.setText(findTestObject('Object Repository/record/Page_Swag Labs/input_Swag Labs_password'), password)
			WebUI.click(findTestObject('Object Repository/record/Page_Swag Labs/input_Swag Labs_login-button'))
			boolean usernamePasswordWrong = WebUI.verifyElementPresent(findTestObject('Object Repository/record/Page_Swag Labs/negatif/01 txtUsernamePasswordWrong'), 1, FailureHandling.OPTIONAL)
			boolean usernameRequired = WebUI.verifyElementPresent(findTestObject('Object Repository/record/Page_Swag Labs/negatif/02 txtUsernameRequired'), 1, FailureHandling.OPTIONAL)
			boolean passwordRequired = WebUI.verifyElementPresent(findTestObject('Object Repository/record/Page_Swag Labs/negatif/03 txtPasswordRequired'), 1, FailureHandling.OPTIONAL)
			if (usernamePasswordWrong || usernameRequired || passwordRequired) {
				def date = new Date().format('hhmmss')
				WebUI.takeFullPageScreenshot(RunConfiguration.getProjectDir() + '/Screenshot/Login/' +no+'. Screenshoot-' + scenario + ' '+date+'.jpg', FailureHandling.STOP_ON_FAILURE)
				continue
			} else {
				KeywordUtil.logInfo('Positif')
			}
			WebUI.delay(2)
			String url = WebUI.getUrl()
			println(url)
			WebUI.verifyMatch(url, 'https://www.saucedemo.com/inventory.html', false)
			def date = new Date().format('hhmmss')
			WebUI.takeFullPageScreenshot(RunConfiguration.getProjectDir() + '/Screenshot/Login/' +no+'. Screenshoot-' + scenario + ' '+date+'.jpg', FailureHandling.STOP_ON_FAILURE)
		}
		WebUI.closeBrowser()
	}
}
