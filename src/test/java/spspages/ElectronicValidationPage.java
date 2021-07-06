package spspages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.BasePage;
import testdataobjects.Root;
import utilies.GenericFunctions;
import utilies.UserActions;

public class ElectronicValidationPage extends BasePage {
	static Logger logger = LogManager.getLogger(ElectronicValidationPage.class.getName());
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();
	WebDriver driver = getDriver();

	By ddBusinessCode = By.id("businessCodeSelect");
	By rdMR= By.id("mr");
	By txtDebitorName = By.id("debtorLastName");
	By txtDebitorFirstname = By.id("debtorFirstName");
	By rdEmailOTPMethod = By.id("email");
	By txtEmail = By.id("debtorEmailField");
	By txtDebitorPhone =By.id("debtorPhoneField");
	By txtstreetNumber =By.id("debtorAddress-addressNumber");
	By txtPostalCode = By.id("debtorAddress-addressPostCode");
	By ddCountry = By.id("debtorAddress-addressCountry");
	By txtSocialReasoan = By.id("socialReason");
	By txtSritNumber = By.id("siretNo");
	By txtSteetName = By.id("debtorAddress-addressStreet");
	By txtCity = By.id("debtorAddress-addressCity");
	By chkAutomaticUMR = By.id("optionGenerateUMR");
	By btnCreate = By.name("create");
	public void createElectronicrequest()
	{
		
		action.clickLink(rdMR);
		action.setValueInTextField(txtDebitorName, "John");
		action.setValueInTextField(txtDebitorFirstname, "Smith");
		action.clickLink(rdEmailOTPMethod);
		action.setValueInTextField(txtEmail, "ravikumar.murai@atos.net");
		
		action.setValueInTextField(txtDebitorPhone, "+61212345678");
	
		
		
	
	 action.selectvaluesdropdown(ddCountry, "France");
		action.setValueInTextField(txtSocialReasoan, "Test reason");
		action.setValueInTextField(txtSritNumber, "14");
		action.setValueInTextField(txtSteetName, "Mokai Road");
		action.setValueInTextField(txtCity, "Pune");
		action.clickLink(chkAutomaticUMR);
		
		action.selectvaluesdropdown(ddBusinessCode, "ABC");
		
		action.clickLink(btnCreate);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
