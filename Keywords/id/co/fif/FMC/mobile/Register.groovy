package id.co.fif.FMC.mobile

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.util.regex.Matcher as Matcher
import java.util.regex.Pattern as Pattern
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.android.AndroidDriver as AndroidDriver
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW



public class Register {

	@Keyword
	def addCustomer() {

		Mobile.tap(findTestObject('Object Repository/Register/01 buttonRegister'), 0)
	}

	@Keyword
	def inputCustomer(String namaLengkap, String nomorHP, String email, String kataSandi, String ulangiKataSandi, String referralID ) {

		Mobile.setText(findTestObject('Object Repository/Register/02 txtNamaLengkap'), namaLengkap, 0)

		Mobile.setText(findTestObject('Object Repository/Register/03 txtNomorHP'), nomorHP, 0)

		Mobile.setText(findTestObject('Object Repository/Register/04 txtEmail'), email, 0)

		Mobile.setText(findTestObject('Object Repository/Register/05 txtKataSandi'), kataSandi, 0)

		Mobile.setText(findTestObject('Object Repository/Register/06 txtUlangiKataSandi'), ulangiKataSandi, 0)

		Mobile.setText(findTestObject('Object Repository/Register/07 txtReferralID'), referralID, 0)

		Mobile.tap(findTestObject('Object Repository/Register/08 cbSetuju'), 0)

		Mobile.tap(findTestObject('Object Repository/Register/09 btnDaftar'), 0)

		boolean textNomorTerdaftar = Mobile.verifyElementVisible(findTestObject('Object Repository/Register/11 popupNoHPsudahterkaitdenganakunlainnya'),
				5, FailureHandling.OPTIONAL)

		if (textNomorTerdaftar) {
			println('screenshot')

			Mobile.takeScreenshot('', FailureHandling.STOP_ON_FAILURE)
		}
	}

	@Keyword
	def otp(String otp) {

		Mobile.setText(findTestObject('Object Repository/Register/10 txtOTP'), otp, 0)
	}

	@Keyword
	def suksesDaftar() {

		if(Mobile.verifyElementExist(findTestObject('Object Repository/Register Customer/1. button - Ya'), 3, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Object Repository/Register Customer/1. button - Ya'), 0)
		}

		if(Mobile.verifyElementExist(findTestObject('Object Repository/Register Customer/button - Lewati fix'), 3, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Object Repository/Register Customer/button - Lewati fix'), 0)
		}

		if(Mobile.verifyElementExist(findTestObject('Object Repository/Register Customer/button - Lewati fix2'), 3, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Object Repository/Register Customer/button - Lewati fix2'), 0)
		}

		Mobile.delay(3)
	}

	@Keyword
	def logout() {

		Mobile.tap(findTestObject('Object Repository/Register Customer/button - Menu fix'), 0)

		Mobile.tap(findTestObject('Object Repository/Register Customer/menu - Keluar fix'), 0)

		Mobile.tap(findTestObject('Object Repository/Register Customer/button - Ya fix'), 0)
	}
}