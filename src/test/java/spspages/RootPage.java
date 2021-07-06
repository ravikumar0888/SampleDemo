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

public class RootPage extends BasePage {
	static Logger logger = LogManager.getLogger(RootPage.class.getName());
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();
	WebDriver driver = getDriver();

	/// Create ROOT
	By txtRootName = By.xpath(".//*[@id='label']");
	By txtContactNumber = By.xpath(".//*[@id='contractInfoCreat']");
	By txtPhone = By.xpath(".//*[@id='phoneNumberCreation']");
	By cbB2B = By.xpath(".//*[@id='schemes_0']");
	By cbCore = By.xpath(".//*[@id='schemes']");
	By cbOneOff = By.xpath(".//*[@id='sequences']");
	By cbRecurring = By.xpath(".//*[@id='sequences_0']");
	By cbRcure = By.xpath(".//*[@id='rcurOnly']");
	By txtStreetNumber = By.xpath(".//*[@id='addresseditor-addressNumber']");
	By txtSteetName = By.xpath(".//*[@id='addresseditor-addressStreet']");
	By txtPostalCode = By.xpath(".//*[@id='addresseditor-addressPostCode']");
	By ddCountry = By.xpath(".//*[@id='addresseditor-addressCountry']");
	By txtCity = By.xpath(".//*[@id='addresseditor-addressCity']");
	By btnSubmit = By.xpath(".//*[@id='divToHideContainer']/div[5]/input[1]");
	By msgSucess = By.xpath(".//*[@id='btbartop']/h3");

	// ADD SCI
	By sciList = By.xpath("//*[@id='sciList-avail']");
	

	By leftArrow = By.xpath("//*[@id='sciList-select']");
	By btnSubmitSCI = By.xpath("//*[@id='sciForm']/div[3]/input[1]");
	// Add business code here
	By addLink = By.id("addrowlink");

	By businessCode1 = By.xpath("(//label[contains(text(),'Business Code')]//following-sibling::input)[2]");

	By businessCode2 = By.xpath("(//label[contains(text(),'Business Code')]//following-sibling::input)[3]");
	By submitBusinessCode2 = By.xpath("//*[@id='businessCodeForm']/div[6]/input[1]");
	By submitBusinessCode1 = By.xpath("//*[@id='businessCodeForm']/div[5]/input[1]");
	// add account number
	By addBank = By.xpath("//*[@id='addrowlink_0']");
	By txtIBAN = By.xpath("//input[contains(@id,'iban')]");

	By txtLebal = By.xpath("//input[contains(@id,'desc')]");

	By txtBic = By.xpath("//input[contains(@id,'bic')]");
	By btnSubmitAccount = By.xpath("//*[@id='bankAccountForm']/div[4]/input[1]");
	// add email
	By txtEmail = By.xpath("//input[contains(@id,'email')]");
	By btnRootSubmit = By.xpath("//*[@id='emailAddressesForm']/div[4]/input[1]");
	By btnAddEmail = By.xpath("//*[@id='addrowlink_1']");

	By rootorg = By.xpath("//a[contains(text(),'zTestSUP')]");

	By btnDisplayParameter = By.xpath("//a[contains(text(),'Display parameters')]");
	By btnEditParameter = By.xpath("//a[contains(text(),'Edit parameters')]");
	By btnShowCollectionOption = By.xpath("//a[contains(text(),'Show collections options')]");

	By chkboxEmail = By.xpath(".//*[@id='sendDeliverySheetByEmail']");

	By btnSubmit1 = By.xpath(".//*[@id='generationDeliveryOptionForm']//div//input[@name ='deliveryOk']");
	By menuOrgMangment = By.xpath("//a[contains(text(),'Organizations Management')]");

	public Root addBankAccount(Root root) {
		action.clickLink(addBank);
		action.setValueInTextField(txtIBAN, root.getRootIBAN());
		action.setValueInTextField(txtLebal, root.getRootLabel());
		driver.findElement(txtBic).clear();
		action.setValueInTextField(txtBic, root.getRootBIC());
		action.clickLink(btnSubmitAccount);
		return root;
	}
	public Root addBusinessCode(Root root) {
		logger.info("bs value sis " + root.getRootBusinessCode1());
		action.clickLink(addLink);

		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(businessCode1));

		action.setValueInTextField(businessCode1, root.getRootBusinessCode1());
		action.clickLink(submitBusinessCode1);
		action.clickLink(addLink);

		//	driver.findElement(businessCode2).sendKeys(root.getRootBusinessCode2());
		WebDriverWait wait1 = new WebDriverWait(driver, 40);
		WebElement show1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(businessCode2));

		action.setValueInTextField(businessCode2, root.getRootBusinessCode2());
		action.clickLink(submitBusinessCode2);

		return root;
	}
	public void addSCI(String value) {
		driver.navigate().refresh();
		action.waitForElementVisible(driver, sciList);
	//	action.selectvaluefromMultipleDropdown(sciList, value);
		
		driver.findElement(sciList).sendKeys(value);
		
		action.clickLink(leftArrow);
		action.clickLink(btnSubmitSCI);
	}
	public String createRootName()
	{
		action.getCurrentDateTime();
		String rootName = "AutomationRoot_" + action.getCurrentDateTime();
		return rootName;
	}
	public Root createRoot(Root root) {
		/*action.getCurrentDateTime();
		String rootName = root.getRootName() + action.getCurrentDateTime();*/

		String rootName =createRootName();
		logger.info("New Root name is :" + rootName);

		action.setValueInTextField(txtRootName, rootName);
		action.setValueInTextField(txtContactNumber, root.getRootContractinformation());
		action.setValueInTextField(txtPhone, root.getRootPhoneNumber());
		action.clickLink(cbCore);
		action.clickLink(cbRecurring);

		action.setValueInTextField(txtStreetNumber, root.getRootStreetNumber());

		action.setValueInTextField(txtSteetName, root.getRootStreetName());
		action.setValueInTextField(txtPostalCode, root.getRootPostalCode());
		action.setValueInTextField(txtCity, root.getRootCity());
		action.selectvaluesdropdown(ddCountry, root.getRootCountry());
		action.clickLink(btnSubmit);

		return root;
	}

	public void selectMenu(String menu, String subMenu) {
		By lblMenu = By.xpath(".//*[@id='pageMenu']/ul/li/a[contains(text(),'" + menu + "')]");
		By lblsubMenu = By.xpath(".//*[@id='pageMenu']/ul/ul/li/a[contains(text(),'" + subMenu + "')]");

		if (!driver.findElements(lblMenu).isEmpty()) {
			driver.findElement(lblMenu).click();
		}

		if (!driver.findElements(lblsubMenu).isEmpty()) {
			if (driver.findElement(lblsubMenu).isEnabled()) {
			
				driver.findElement(lblsubMenu).click();

				logger.info("SubMenu is Present {}", lblsubMenu);

			} else {

				logger.info("SubMenu is not found on the webpage");

			}
		} else {

			logger.info("No need to select sub menu option");
		}
	}
	
	public Root emailSubmit(Root root) {
		action.clickLink(btnAddEmail);
		action.setValueInTextField(txtEmail, root.getRootEmail());
		action.clickLink(btnRootSubmit);
		logger.info("Root save sucessfully ...");

		//	
		return root;
	}

	public void sendDeliverySheetByEmail() {
		action.clickLink(menuOrgMangment);
		action.clickLink(rootorg);
		action.clickLink(btnDisplayParameter);
		action.clickLink(btnEditParameter);
		action.clickLink(btnShowCollectionOption);

		boolean isChecked = driver.findElement(chkboxEmail).isSelected();

		if (isChecked == false) {
			action.clickLink(chkboxEmail);
			action.clickLink(btnSubmit1);
		} else {
			action.clickLink(btnSubmit1);
		}

	}
	
	By organame= By.xpath("/html/body/div[6]/div[2]/div/div[2]/div[1]/div[1]/div/div/div[1]/div/div/div/span/a");                      //("//a[contains(text(),'zTestRaviM')]");
	By btnEditOptions = By.xpath("//*[@id=\"btbartop\"]/div/a[contains(text(),'Edit options')]");
	By btnNotificationtemplete = By.xpath("//*[@id=\"btbartop\"]/div[7]/a[contains(text(),'Notification Templates')]");
	By ddType = By.id("type");
	By ddChannel = By.id("channel");
	By ddlanguage = By.id("language");
	By txtSenderEmailId = By.id("senderEmailField");
	By txtSubjectField = By.id("subjectField");
	By btnValidate = By.id("Create");
	By txtContentdata = By.id("//*[@id=\'xEditingArea\']");
	
	public void selectorgandtemplete()
	{
		action.clickLink(organame);
		action.clickLink(btnNotificationtemplete);
	}
	
	public void createNotificationTemplete(String type)
	{
		
		
		action.selectvaluesdropdown(ddType,type);
		action.selectvaluesdropdown(ddChannel, "Electronic mail");
		action.selectvaluesdropdown(ddlanguage, "English");
		
		action.setValueInTextField(txtSenderEmailId, "ravikumar.murai@atos.net");
		action.setValueInTextField(txtSubjectField, "Automation Test");
		
	//	action.setValueInTextField(txtContentdata, "Test Content");
		
		action.clickLink(btnValidate);
		
	}
By btnModifyEntity =By.xpath("//*[@id=\'btbartop\']/div[4]/a[contains(text(),'Modify entity')]");
By txtSPSSignId = By.id("spsSignId");
By btnbackToOrg = By.xpath("//*[@id=\"btbartop\"]/div/a");
	public void addSignatureId()
	{
		action.clickLink(btnbackToOrg);
	//	action.clickLink(organame);
		action.clickLink(btnModifyEntity);
		action.clearTextField(txtSPSSignId);
		action.setValueInTextField(txtSPSSignId, "ztestDoS");   // checking 
		
		action.clickLink(btnSubmit);
	}
	
}
