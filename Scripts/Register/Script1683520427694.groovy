import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.util.regex.Matcher as Matcher
import java.util.regex.Pattern as Pattern
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.android.AndroidDriver as AndroidDriver
import io.appium.java_client.android.AndroidKeyCode as AndroidKeyCode
import com.kms.katalon.core.configuration.RunConfiguration

Mobile.startExistingApplication('id.co.fifgroup.fmc.dev', FailureHandling.STOP_ON_FAILURE)

TestData dataCustomer = findTestData('Data Files/Register')

for (int i = 1; i <= dataCustomer.getRowNumbers(); i++) {

	String namaLengkap = dataCustomer.getValue("Nama Lengkap" , i)
	String nomorHP = dataCustomer.getValue("Nomor HP" , i)
	String email = dataCustomer.getValue("Email" , i)
	String kataSandi = dataCustomer.getValue("Kata Sandi" , i)
	String ulangiKataSandi = dataCustomer.getValue("Ulangi Kata Sandi" , i)
	String referralId = dataCustomer.getValue("Referral ID" , i)
	String scenario = dataCustomer.getValue("Scenario" , i)

//	klik menu register
	
	Mobile.tap(findTestObject('Object Repository/Register/01 buttonRegister'), 0)
	
//	input data customer

	Mobile.setText(findTestObject('Object Repository/Register/02 txtNamaLengkap'), namaLengkap, 0)
	
	Mobile.setText(findTestObject('Object Repository/Register/03 txtNomorHP'), nomorHP, 0)
	
	Mobile.setText(findTestObject('Object Repository/Register/04 txtEmail'), email, 0)
	
	Mobile.setText(findTestObject('Object Repository/Register/05 txtKataSandi'), kataSandi, 0)
	
	Mobile.setText(findTestObject('Object Repository/Register/06 txtUlangiKataSandi'), ulangiKataSandi, 0)
	
	Mobile.setText(findTestObject('Object Repository/Register/07 txtReferralID'), referralId, 0)

	Mobile.tap(findTestObject('Object Repository/Register/08 cbSetuju'), 0)
	
	boolean redWordNama = Mobile.verifyElementVisible(findTestObject('Object Repository/Register/14 redWordingMasukkanNamaLengkapAnda'), 1, FailureHandling.OPTIONAL)
	
	boolean redWordNomor = Mobile.verifyElementVisible(findTestObject('Object Repository/Register/15 redWordingSilahkanmasukkannomorteleponyangvalid'), 1, FailureHandling.OPTIONAL)
	
	boolean redWordSandi = Mobile.verifyElementVisible(findTestObject('Object Repository/Register/16 redWordingMasukkankatasandianda'), 1, FailureHandling.OPTIONAL)
	
	boolean redWordKonfirmsSandi = Mobile.verifyElementVisible(findTestObject('Object Repository/Register/17 konfirmasikatasanditidakbolehkosong'), 1, FailureHandling.OPTIONAL)
	
	boolean redWordSandiSalah = Mobile.verifyElementVisible(findTestObject('Object Repository/Register/18 redWordingKatasandidankonfirmasikatasanditidaksesuai'), 1, FailureHandling.OPTIONAL)
	
	if (redWordNama  || redWordNomor  || redWordSandi  || redWordKonfirmsSandi  || redWordSandiSalah) {
		
		Mobile.delay(2)
		
		def date = new Date().format('hhmmss')
		
		Mobile.takeScreenshot(RunConfiguration.getProjectDir() + "/Screenshot/"  +scenario+' - screenshoot- '+date+'.jpg', FailureHandling.STOP_ON_FAILURE)
	
		Mobile.pressBack()
		
		continue
				
	}
	
	Mobile.tap(findTestObject('Object Repository/Register/09 btnDaftar'), 0)
			
	boolean textNomorTerdaftar = Mobile.verifyElementVisible(findTestObject('Object Repository/Register/11 popupNoHPsudahterkaitdenganakunlainnya'), 1, FailureHandling.OPTIONAL)
		
	boolean textEmailTerdaftar = Mobile.verifyElementVisible(findTestObject('Object Repository/Register/13 popupEmailIDtelahdigunakanditautkandenganakunlain'), 1, FailureHandling.OPTIONAL)
	
	if (textNomorTerdaftar  || textEmailTerdaftar) {
		Mobile.delay(2)
		
		def date = new Date().format('hhmmss')
		
		Mobile.takeScreenshot(RunConfiguration.getProjectDir() + "/Screenshot/"  +scenario+' - screenshoot- '+date+'.jpg', FailureHandling.STOP_ON_FAILURE)
		
		Mobile.tap(findTestObject('Object Repository/Register/12 btnYa'), 0)
		
		Mobile.pressBack()
		
		continue		
				
	}
	
	Mobile.delay(5)
	String otp = WebUI.callTestCase(findTestCase('Test Cases/Database Connection'), [('nomorHP') : nomorHP, ], FailureHandling.STOP_ON_FAILURE)

//	input otp
	Mobile.setText(findTestObject('Object Repository/Register/10 txtOTP'), otp, 0)

//	popup register berhasil
	
	if(Mobile.verifyElementExist(findTestObject('Object Repository/Register/12 btnYa'), 3, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Object Repository/Register/12 btnYa'), 0)
	} else {
		println{'ya tidak ada'}
	}
	
	if(Mobile.verifyElementExist(findTestObject('Object Repository/Register/19 btnLewati'), 3, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Object Repository/Register/19 btnLewati'), 0)
	} else {
		println{'lewati tidak ada'}
	}
	
	Mobile.delay(2)
	
//	logout
	
	Mobile.tap(findTestObject('Object Repository/Register/20 btnMenu'), 0)
	
	Mobile.tap(findTestObject('Object Repository/Register/22 btnMenuKeluar'), 0)
	
	Mobile.tap(findTestObject('Object Repository/Register/21 btnYaKonfirm'), 0)

}

