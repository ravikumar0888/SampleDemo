package spspages;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import testbase.BasePage;
import testdataobjects.Mandates;
import utilies.GenericFunctions;
import utilies.UserActions;

public class MandatePage extends BasePage {
	static Logger logger = LogManager.getLogger(MandatePage.class.getName());
	WebDriver driver = getDriver();
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();
	CommonSectionPage cs = new CommonSectionPage();

	By sequenceType = By.id("mandateSequenceTypeField");
	By txtCreditorEntity = By.xpath(".//*[@id='organizationSelect']");
	By ddSci = By.xpath(".//*[@id='creditorSelectRequired']");
	By ddBusinessCode = By.xpath(".//*[@id='businessCodeSelectRequired']");
	By btnCreateMandate = By.name("create");
	By statusPending = By.xpath(".//*[@id='mandateInfoContainer']/div/div/div[1]/dl/dd[4]");
	By lblUmr = By.xpath(".//dt[.=\"UMR :\"]/following-sibling::dd[1]");
	By btnCreateSdd = By.xpath(".//*[@id='btbartop']/div[1]/a[contains(text(),'Create SDD')]");

	// Mandate Search
	By ddCreditorEntity = By.xpath(".//*[@id='organizationSelect']");
	By searchMandateDetails = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[11]/a[1]/img");
	By btnCreateSDD = By.xpath("/html/body/div[10]/div[2]/div/div[2]/div[1]/a");
	By menuMandateSearch = By.xpath(".//*[@id='pageMenu']/ul/ul/li/a[text()='Mandate Search']");
	By msgNoResult = By.xpath(".//*[@id='empty']");
	By menuMandateManagement = By.xpath(".//*[@id='pageMenu']/ul/li/a[text()='Mandates Management']");
	By menuNewMandate = By.xpath("//*[@id='pageMenu']/ul/ul/li[2]//a[text()='New Mandate']");
	// MandateSearchby UMR
	By txtUmr = By.xpath(".//*[@id='umr']");
	By btnSearch = By.name("search");
	By imgMandateDetails = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[11]/a[1]/img");
	By btnGeneratePDF = By.xpath(".//*[@id='btbartop']/div[7]/a");
	By btnEditMandate = By.xpath("//a[@class='lniconbt btEditOnly']");

	// debitor details
	By txtDebitorName = By.xpath(".//*[@id='debtorName']");
	By txtIban = By.xpath(".//*[@id='debtorIbanField']");

	By txtPostalCode = By.xpath(".//*[@id='debtorAddress-addressPostCode']");
	By txtCity = By.xpath(".//*[@id='debtorAddress-addressCity']");
	By txtStreetName = By.xpath(".//*[@id='debtorAddress-addressStreet']");
	By ddCountry = By.xpath(".//*[@id='debtorAddress-addressCountry']");

	By txtSignDate = By.xpath(".//*[@id='signatureDate']");

	By btnUpdate = By.xpath("//*[@id='createButtons']/input[1]");
	By btnPlannedUpdate = By.xpath("//*[@id='createButtons']/a/input");
	By txtPlanDateField = By.id("planDateField");
	By lblTomorrowDate = By.xpath("//*[@class='day today current']//following-sibling::td[1]");
	By btnSubmitDate = By.xpath("//*[@value='Submit']");
	By tabPlannedUpdate = By.id("plannedRevisionTab");
	By lblStatusHeader = By.xpath("//div[@id='revisionViewZone']//th[@class='status']");

	By lblActiveStatus = By.xpath(".//*[@id='mandateInfoContainer']/div/div/div[1]/dl/dd[4]");

	By addrow = By.xpath(".//*[@id='addrowlink']");

	By phyKey = By.xpath("//input[starts-with(@id,'physicalArchivingKey-')]");
	By fileupload = By.xpath("//input[starts-with(@id,'uploadFileField-')]");
	By phykeyStatus = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[3]");
	By errormsg = By.xpath(".//*[@id='ferror']/ul/li");

	By txtemail = By.xpath(".//*[@id='debtorEmailField']");

	By txtphoneNumber = By.xpath(".//*[@id='debtorPhoneField']");

	By txtuir = By.xpath(".//*[@id='uir']");
	By txtMandateCity = By.xpath(".//*[@id='signatureCityField']");

	By checkboxUMR = By.id("optionGenerateUMR");

	By ddModeOfDebitor = By.xpath("//select[@id='notificationModeSelector']");

	By lastSDD = By.xpath("//*[@id='mandateLastSDDInfoTrigger']");

	By lableEndtoEndid = By.xpath(".//*[@id='mandateLastSDDInfoContainer']/div/div/div[1]/dl/dd[1]/a");

	By labelamt = By.xpath(".//*[@id='mandateLastSDDInfoContainer']/div/div/div[1]/dl/dd[2]");

	By labelStatus = By.xpath(".//*[@id='mandateLastSDDInfoContainer']/div/div/div[1]/dl/dd[4]");
	By btnRevokeMandate = By.xpath("//div/a[contains(text(),'Revoke')]");
	By chkRevokeNoOption = By.id("cancelNo");
	By chkRevokeYesOption = By.id("cancelYes");
	By chkRevokeWhoDebtor = By.id("cancelByDebtor");
	By chkRevokeWhoCreditor = By.id("cancelByCreditor");
	By txtRevokeComment = By.id("cancelComment");
	By txtRevokeDateField = By.id("cancelDateField");
	By btnRevokeSubmit = By.name("submit");
	By btnRevokeCancel = By.xpath("//div[@id='cancelWindow']//input[@value='Cancel']");

	public Mandates createActiveMandateWithAttachement(Mandates mandate) {
		action.selectvaluesdropdownWithValue(txtCreditorEntity, mandate.getCreditorEntity());
		action.setValueInTextField(txtDebitorName, mandate.getDebtorName());
		action.setValueInTextField(txtIban, mandate.getDebtorIBAN());

		action.setValueInTextField(txtPostalCode, mandate.getPostalCode());
		action.setValueInTextField(txtCity, mandate.getCity());
		action.setValueInTextField(txtStreetName, mandate.getStreetName());
		action.selectvaluesdropdown(ddCountry, mandate.getCountry());

		String date = gf.currentDate();
		action.setValueInTextField(txtSignDate, date);
		action.clickLink(addrow);
		action.setValueInTextField(phyKey, mandate.getPhyKey());

		WebElement uploadElement = driver.findElement(fileupload);

		String absolutePath = gf.getResourceAsFile("fileupload/dummyAttachment.pdf");
		// for remotely uploading files
		((RemoteWebElement) uploadElement).setFileDetector(new LocalFileDetector());

		uploadElement.sendKeys(absolutePath);

		action.saveScreenshot();
		action.clickLink(btnCreateMandate);
		action.waitForElementVisible(driver, lblActiveStatus);
		String actualMessage = driver.findElement(lblActiveStatus).getText();
		String expectedValue = "Active";
		assertEquals(expectedValue, actualMessage);

		action.waitForElementVisible(driver, phykeyStatus);

		String actualPhyKey = driver.findElement(phykeyStatus).getText();
		String expectedPhyKey = mandate.getPhyKey();
		assertEquals(expectedPhyKey, actualPhyKey);
		return mandate;
	}

	@Step("User updates the Mandate with mandatory fields. ")
	public Mandates createMandate(Mandates mandate) {
		action.selectvaluesdropdown(txtCreditorEntity, mandate.getCreditorEntity());

		action.setValueInTextField(txtDebitorName, mandate.getDebtorName());
		action.setValueInTextField(txtIban, mandate.getDebtorIBAN());

		action.setValueInTextField(txtPostalCode, mandate.getPostalCode());
		action.setValueInTextField(txtCity, mandate.getCity());
		action.setValueInTextField(txtStreetName, mandate.getStreetName());
		action.selectvaluesdropdown(ddCountry, mandate.getCountry());

		String date = gf.currentDate();
		action.setValueInTextField(txtSignDate, date);
		action.saveScreenshot();
		action.clickLink(btnCreateMandate);
		return mandate;
	}

	@Step("User create the Mandate and sent to debitor ")
	public Mandates createMandateAndSenttoDebitor(Mandates mandate) {
		action.clickLink(menuNewMandate);
		action.selectvaluesdropdown(txtCreditorEntity, mandate.getCreditorEntity());
		action.setValueInTextField(txtDebitorName, mandate.getDebtorName());
		action.setValueInTextField(txtIban, mandate.getDebtorIBAN());
		action.setValueInTextField(txtemail, mandate.getEmail());
		action.setValueInTextField(txtPostalCode, mandate.getPostalCode());
		action.setValueInTextField(txtCity, mandate.getCity());
		action.setValueInTextField(txtStreetName, mandate.getStreetName());
		action.selectvaluesdropdown(ddCountry, mandate.getCountry());
		action.selectvaluesdropdown(ddModeOfDebitor, mandate.getModeOfDebitor());

		action.saveScreenshot();
		action.clickLink(btnCreateMandate);
		String status = driver.findElement(statusPending).getText();
		assertEquals(status, "Sent to debtor");

		return mandate;
	}

	@Step("User create mandate with Future date")
	public Mandates createMandatewithFutureDate(Mandates mandate) {

		action.clickLink(menuNewMandate);
		action.selectvaluesdropdownWithValue(txtCreditorEntity, mandate.getCreditorEntity());
		action.waitForElementClickable(driver, btnCreateMandate);
		action.saveScreenshot();

		action.setValueInTextField(txtSignDate, "10/10/2022");
		action.clickLink(btnCreateMandate);

		String status = driver.findElement(errormsg).getText();
		assertEquals(status, "The signature date cannot be in the future");
		action.clickLink(menuMandateSearch);
		return mandate;
	}

	@Step("User updates the Mandate with mandatory fields. ")
	public Mandates editMandateWithDebitorDetails(Mandates mandate) {
		action.saveScreenshot();
		action.clickLink(btnEditMandate);

		action.setValueInTextField(txtDebitorName, mandate.getDebtorName());
		action.setValueInTextField(txtIban, mandate.getDebtorIBAN());

		action.setValueInTextField(txtPostalCode, mandate.getPostalCode());
		action.setValueInTextField(txtCity, mandate.getCity());
		action.setValueInTextField(txtStreetName, mandate.getStreetName());
		action.selectvaluesdropdown(ddCountry, mandate.getCountry());

		String date = gf.currentDate();
		action.setValueInTextField(txtSignDate, date);
		action.saveScreenshot();
		action.clickLink(btnUpdate);
		action.waitForElementVisible(driver, lblActiveStatus);
		String actualMessage = driver.findElement(lblActiveStatus).getText();
		String expectedValue = "Active";
		assertEquals(expectedValue, actualMessage);

		return mandate;
	}

	@Step("User performs a planned mandate update.")
	public Mandates plannedMandateupdate(Mandates mandate) {
		action.saveScreenshot();
		action.clickLink(btnEditMandate);

		action.setValueInTextField(txtDebitorName, mandate.getDebtorName());
		action.setValueInTextField(txtIban, mandate.getDebtorIBAN());

		action.setValueInTextField(txtPostalCode, mandate.getPostalCode());
		action.setValueInTextField(txtCity, mandate.getCity());
		action.setValueInTextField(txtStreetName, mandate.getStreetName());
		action.selectvaluesdropdown(ddCountry, mandate.getCountry());

		String date = gf.currentDate();
		action.setValueInTextField(txtSignDate, date);
		action.saveScreenshot();
		action.clickLink(btnPlannedUpdate);

		// planned date dialog click on txt
		action.waitForElementVisible(driver, txtPlanDateField);
		action.clickTextBox(txtPlanDateField);
		action.clickLink(lblTomorrowDate);
		action.clickButton(btnSubmitDate);
		action.acceptAlert();

		action.waitForElementVisible(driver, tabPlannedUpdate);

		action.clickLink(tabPlannedUpdate);
		int position = driver
				.findElements(By.xpath("//div[@id='revisionViewZone']//th[@class='status']/preceding-sibling::td"))
				.size() + 1;
		assertEquals(driver.findElement(By.xpath("//div[@id='revisionViewZone']//tr[1]/td[" + position + "]")), "PLANNED");

		return mandate;
	}

	public void generatePDF() {

		action.clickLink(btnGeneratePDF);
		action.saveScreenshot1("fileDownload");
		// rename file
	}

	public String getUMR() {

		String umr = driver.findElement(lblUmr).getText();
		return umr;
	}

	public boolean isCreateSDDButtonDisabled() {
		return cs.isSPSButtonDisabled(btnCreateSdd);
	}

	@Step("User create mandate creation with UIR ")
	public Mandates mandateCreationWithUIR(Mandates mandate) {
		action.clickLink(menuNewMandate);
		action.selectvaluesdropdownWithValue(txtCreditorEntity, mandate.getCreditorEntity());
		action.clickLink(checkboxUMR);

		String UIR = gf.randamGenerator();
		action.setValueInTextField(txtuir, "UIR_" + UIR);
		action.waitForElementClickable(driver, btnCreateMandate);
		action.saveScreenshot();
		action.clickLink(btnCreateMandate);
		action.waitForElementVisible(driver, statusPending);
		String status = driver.findElement(statusPending).getText();
		assertEquals(status, "Pending");
		return mandate;
	}

	@Step("User create mandate creation with UMR ")
	public Mandates mandateCreationWithUMR(Mandates mandate) {
		action.clickLink(menuMandateManagement);
		action.clickLink(menuNewMandate);
		action.selectvaluesdropdownWithValue(txtCreditorEntity, mandate.getCreditorEntity());
		action.waitForElementClickable(driver, btnCreateMandate);
		action.saveScreenshot();
		action.clickLink(btnCreateMandate);
		action.waitForElementVisible(driver, statusPending);
		String status = driver.findElement(statusPending).getText();
		assertEquals(status, "Pending");
		// action.clickLink(menuMandateSearch);
		return mandate;
	}

	@Step("User updates the Mandate with new data values. ")
	public Mandates mandateUpdateWithNewData(Mandates mandate) {
		action.saveScreenshot();
		action.clickLink(btnEditMandate);

		action.clearTextField(txtDebitorName);
		action.setValueInTextField(txtDebitorName, mandate.getNewDebtorName());
		action.clearTextField(txtIban);
		action.setValueInTextField(txtIban, mandate.getNewDebtorIBAN());
		action.clearTextField(txtPostalCode);
		action.setValueInTextField(txtPostalCode, mandate.getNewPostalCode());
		action.clearTextField(txtCity);
		action.setValueInTextField(txtCity, mandate.getNewCity());
		action.clearTextField(txtStreetName);
		action.setValueInTextField(txtStreetName, mandate.getNewStreetName());

		action.saveScreenshot();
		action.clickLink(btnUpdate);
		action.waitForElementVisible(driver, lblActiveStatus);
		String actualMessage = driver.findElement(lblActiveStatus).getText();
		String expectedValue = "Active";
		assertEquals(expectedValue, actualMessage);

		return mandate;
	}

	@Step("User modified the UMR ")
	public Mandates modifiedUMR(Mandates mandate) {
		action.clickLink(btnEditMandate);
		String existingUMR = driver.findElement(txtUmr).getText();
		action.clearTextField(txtUmr);
		String UMR = gf.randamGenerator();
		String currentUMR = "UMR_" + UMR;
		action.setValueInTextField(txtUmr, currentUMR);

		mandate.setUmr(currentUMR);

		action.waitForElementClickable(driver, btnCreateMandate);
		action.saveScreenshot();
		action.clickLink(btnCreateMandate);

		/*
		 * action.waitForElementVisible(driver, statusPending); need to add validation
		 * for value String status = driver.findElement(statusPending).getText();
		 * assertEquals(status, "Pending"); //action.clickLink(menuMandateSearch);
		 */ return mandate;
	}

	public String reuseValdiation(By field, String getvalue, String errormessage) {
		action.clickLink(btnEditMandate); // edit btn xpath changed
		action.clearTextField(field);
		action.setValueInTextField(field, getvalue);
		action.clickLink(btnUpdate);
		String emailstatus = driver.findElement(errormsg).getText();
		assertEquals(emailstatus, errormessage);
		action.clearTextField(field);
		action.clickLink(btnUpdate);
		return "Sucess";
	}

	@Step("User searches invalid UMR.")
	public Mandates searchInvalidUMR(Mandates mandate) {
		action.clickLink(menuMandateManagement);
		action.waitForElementClickable(driver, txtUmr);
		action.setValueInTextField(txtUmr, mandate.getinvlaidUMR());
		action.clickLink(btnSearch);

		String status = driver.findElement(msgNoResult).getText();
		assertEquals(status, "No results to display");

		action.saveScreenshot();

		return mandate;
	}

	@Step("User searches mandate and generates PDF")
	public Mandates searchUMR(Mandates mandate) throws InterruptedException {
		
		searchUMR(mandate.getUmr(), mandate.getCreditorEntity());
		return mandate;
	}

	@Step("User searches mandate and generates PDF")
	public void searchUMR(String umr, String creditor) throws InterruptedException {

		Thread.sleep(3000);
	
		action.selectMenu("Mandates Management", "Mandate Search");

		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement show = wait.until(ExpectedConditions.elementToBeClickable(txtCreditorEntity));

		driver.findElement(txtCreditorEntity).sendKeys(creditor);

		action.clearTextField(txtUmr);
		action.waitForElementClickable(driver, txtUmr);
		action.setValueInTextField(txtUmr, umr);
		action.clickLink(btnSearch);
		action.waitForPageToLoad();
		action.saveScreenshot();
		action.clickLink(imgMandateDetails);
		action.waitForPageToLoad();

	}

	@Step("User selects creditor for mandate creation")
	public Mandates selectCreditorForMandateCreation(Mandates mandate, String value) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement show = wait.until(ExpectedConditions.elementToBeClickable(txtCreditorEntity));

		action.selectvaluesdropdownWithValue(txtCreditorEntity, mandate.getCreditorEntity());

		action.waitForPageToLoad();

		WebElement show1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("mandateSequenceTypeField")));

		/*
		 * Select select = new
		 * Select(driver.findElement(By.id("mandateSequenceTypeField")));
		 * select.selectByVisibleText(mandate.getSequenceType());
		 */
		String abc = "mandate";
		if (abc == value) {
			action.waitForPageToLoad();
			Select select3 = new Select(driver.findElement(By.xpath(".//*[@id='creditorSelectRequired']")));
			select3.selectByVisibleText(mandate.getSCI());

			Thread.sleep(3000);

			action.waitForPageToLoad();
			Select select0 = new Select(driver.findElement(By.xpath(".//*[@id='businessCodeSelectRequired']")));
			select0.selectByVisibleText(mandate.getBusinessCode());
			action.waitForPageToLoad();

		}

		/*
		 * WebDriverWait wait1 = new WebDriverWait(driver, 60); WebElement show1 =
		 * wait.until(ExpectedConditions.elementToBeClickable(txtCreditorEntity));
		 */
		action.waitForPageToLoad();

		action.waitForElementClickable(driver, btnCreateMandate);
		action.saveScreenshot();

		action.clickLink(btnCreateMandate);
		action.waitForElementVisible(driver, lblUmr);

		String umr1 = driver.findElement(lblUmr).getText();
		mandate.setUmr(umr1);

		action.waitForElementVisible(driver, statusPending);
		String status = driver.findElement(statusPending).getText();
		assertEquals(status, "Pending");
		return mandate;
	}

	By chkUseBBAN = By.id("optionBbanField");
	By lblErrorBBAN = By.id("bbanError");
	By btnConvertBBAN = By.id("convertBban");
	By selCountryBBAN = By.id("countryBbanField");
	By txtDebtorBBANfield = By.id("debtorBbanField");

	@Step("Validate BBAN field and enter BBAN")
	public void validateAndFillBBAN(boolean skipValidationFillDirectly, Mandates mandate) throws InterruptedException {
		// set skipValidationFillDirectly true to directly fill BBAN and skip
		// validations.
		// action.clickLink(btnEditMandate);
		// action.waitForPageToLoad();
		if (!skipValidationFillDirectly) { // do the BBAN field validations
			action.selectCheckboxIfUnchecked(chkUseBBAN);
			action.clickLink(btnConvertBBAN);
			Thread.sleep(1000);
			assertEquals(action.getText(lblErrorBBAN), "Incorrect data");
			action.selectUncheckAndCheck(chkUseBBAN);
			action.setValueInTextField(txtDebtorBBANfield, mandate.getDebtorIBAN().substring(4));
			action.clickLink(btnConvertBBAN);
			Thread.sleep(1000);
			assertEquals(action.getText(lblErrorBBAN), "Incorrect data");
			action.selectUncheckAndCheck(chkUseBBAN);
			action.selectByValue(selCountryBBAN, mandate.getDebtorIBAN().substring(0, 2));
			action.setValueInTextField(txtDebtorBBANfield, "Wrong value");
			action.clickLink(btnConvertBBAN);
			Thread.sleep(3000);
			assertEquals(action.getText(lblErrorBBAN), "Incorrect data");
		}
		action.setValueInTextField(txtDebtorBBANfield, mandate.getDebtorIBAN().substring(4));
		action.selectByValue(selCountryBBAN, mandate.getDebtorIBAN().substring(0, 2));
		action.clickButton(btnConvertBBAN);
		Thread.sleep(1000);
		assertEquals(action.getAttribute(txtIban, "value"), mandate.getDebtorIBAN());
	}

	public void selectMenuMandate() {
		action.clickLink(menuNewMandate);
	}

	public void selectMandateMangement() {
		action.clickLink(menuMandateManagement);
	}

	@Step("User modified Sent to debitor to Active mandate")
	public Mandates sentToDebitorTOActive(Mandates mandate) {
		action.clickLink(btnEditMandate);
		String date = gf.currentDate();
		action.setValueInTextField(txtSignDate, date);
		action.saveScreenshot();
		action.clickLink(btnUpdate);
		action.waitForElementVisible(driver, lblActiveStatus);
		String actualMessage = driver.findElement(lblActiveStatus).getText();
		String expectedValue = "Active";
		assertEquals(expectedValue, actualMessage);
		return mandate;
	}

	public void validateLastSDDdetails(String endit) throws InterruptedException {
		action.clickLink(lastSDD);
		String actualendid = driver.findElement(lableEndtoEndid).getText();

		// assertEquals(actualendid, endit); for testing comments

		Thread.sleep(1000);

		String actualamt = driver.findElement(labelamt).getText();
		assertEquals("EUR22.00", actualamt);
		Thread.sleep(1000);
		String actualstatus = driver.findElement(labelStatus).getText();
		assertEquals("Issued", actualstatus);

	}

	@Step("User is validating mandate creation form.")

	public Mandates validateMandatePage(Mandates mandate) {
		// Invalid debitornNAME

		action.clickLink(btnEditMandate); // edit btn xpath changed
		action.clearTextField(txtDebitorName);
		action.setValueInTextField(txtDebitorName, mandate.getInvalidDebtorName());
		action.clickLink(btnUpdate);
		String status = driver.findElement(errormsg).getText();
		assertEquals(status, "The debtor name must not exceed 70 characters.");
		action.clearTextField(txtDebitorName);
		action.setValueInTextField(txtDebitorName, mandate.getDebtorName());
		action.clickLink(btnUpdate);
		// validate email

		String invalidEmail = mandate.getInvalidemail();
		reuseValdiation(txtemail, invalidEmail, "Debtor email is invalid");

		String invalidPhone = mandate.getInvalidPhoneNumber();
		reuseValdiation(txtphoneNumber, invalidPhone, "The debtor's phone number is invalid");

		String invalidPostCode = mandate.getInvalidPostalCode();
		reuseValdiation(txtPostalCode, invalidPostCode,
				"You may provide at most 16 characters for the field \"Postal Code\".");
		// action.waitForElementClickable(driver, txtPostalCode);
		action.clickLink(btnEditMandate);
		action.setValueInTextField(txtPostalCode, mandate.getPostalCode());
		action.clickLink(btnUpdate);

		String invalidIBAN = mandate.getInvalidDebtorIBAN();
		// reuseValdiation(txtIban,invalidIBAN,"The bank account coordinates are invalid
		// AXABFRPPXXX/FR1234567890123456789012346");
		action.clickLink(btnEditMandate); // edit btn xpath changed
		action.clearTextField(txtIban);
		action.setValueInTextField(txtIban, invalidIBAN);
		action.clickLink(btnUpdate);
		String emailstatus = driver.findElement(errormsg).getText();
		assertEquals(emailstatus, "The bank account coordinates are invalid AXABFRPPXXX/FR1234567890123456789012346");
		action.clearTextField(txtIban);

		action.setValueInTextField(txtIban, mandate.getDebtorIBAN());
		String currectUMR = driver.findElement(txtUmr).getText();
		action.clickLink(btnUpdate);

		/*
		 * need to check String invalidUMR = mandate.getinvalidUMR(); // String
		 * currectUMR = driver.findElement(txtUmr).getText(); reuseValdiation(txtUmr,
		 * invalidUMR,"The UMR [MANDATE-REFERENCE000000000000000070123456789044444] is invalid. It doesn't respect the pattern [\\\\A([a-zA-Z[\\\\+][\\\\?][\\\\'][\\\\(][\\\\)][\\\\:][\\\\/][\\\\.][\\\\,][\\\\-][\\\\_][\\\\s]0-9]{1,35})\\\\z]."
		 * ); action.clearTextField(txtUmr);
		 * action.setValueInTextField(txtUmr,currectUMR); action.clickLink(btnUpdate);
		 */
		return mandate;
	}

	@Step("User selects menu with value {Submenu} ")
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

	By businessCode = By.xpath(".//*[@id='businessCodeSelectRequired']");
	By ddaddressType = By.xpath(".//*[@id='debtorAddress-addressType']");

	public Mandates createActiveMandateEventReport(Mandates mandate) {

		action.waitForPageToLoad();

		action.clickTextBox(txtDebitorName);

		action.setValueInTextField(txtDebitorName, mandate.getDebtorName());
		action.setValueInTextField(txtemail, mandate.getEmail());

		action.selectCheckboxIfUnchecked(checkboxUMR);
		action.setValueInTextField(txtMandateCity, mandate.getNewCity());
		action.setValueInTextField(txtStreetName, mandate.getStreetName());
		action.setValueInTextField(txtCity, mandate.getCity());

		action.setValueInTextField(txtphoneNumber, mandate.getPhonenumberEr());
		action.setValueInTextField(txtIban, mandate.getDebtorIBAN());
		action.setValueInTextField(txtPostalCode, mandate.getPostalCode());

		Select select = new Select(driver.findElement(By.xpath(".//*[@id='debtorAddress-addressCountry']")));
		select.selectByVisibleText("France");

		String date = gf.currentDate();
		action.setValueInTextField(txtSignDate, date);

		Select select1 = new Select(driver.findElement(By.xpath(".//*[@id='debtorAddress-addressType']")));
		select1.selectByVisibleText("Home address");

		Select select0 = new Select(driver.findElement(By.xpath(".//*[@id='businessCodeSelectRequired']")));
		select0.selectByVisibleText("TTT");

		action.saveScreenshot();
		action.clickLink(btnCreateMandate);
		action.waitForElementVisible(driver, lblActiveStatus);
		String actualMessage = driver.findElement(lblActiveStatus).getText();
		String expectedValue = "Active";
		assertEquals(expectedValue, actualMessage);

		return mandate;
	}

	////////// New code base for mandate module

	@Step("User updates the Mandate with mandatory fields. ")
	public Mandates editMandateWithSendForValidatation(Mandates mandate) {
		action.saveScreenshot();
		action.clickLink(btnEditMandate);

		action.setValueInTextField(txtDebitorName, mandate.getDebtorName());
		action.setValueInTextField(txtIban, mandate.getDebtorIBAN());

		String date = gf.currentDate();
		action.setValueInTextField(txtSignDate, date);
		action.saveScreenshot();
		action.clickLink(btnUpdate);
		action.waitForElementVisible(driver, lblActiveStatus);
		String actualMessage = driver.findElement(lblActiveStatus).getText();
		String expectedValue = "Waiting for validation";
		assertEquals(expectedValue, actualMessage);

		return mandate;
	}

	By btnvalidate = By.xpath("html/body/div[10]/div[2]/div/div[2]/div[1]/a");
	By rdYes = By.xpath(".//*[@id='activateYes']");
	By txtCurrentDate = By.xpath(".//*[@id='activateDateField']");
	By btnValdiateYes = By.xpath(".//*[@id='activateForm']/div[2]/input[1]");
	By statusUpdate = By.xpath("//*[@id='mandateInfoContainer']/div/div/div[1]/dl/dd[4]");
	By errorcode = By.xpath("//*[@id=\'ferror\']/ul/li");

	public void validateMandate() {
		action.clickLink(btnvalidate);
		action.clickLink(rdYes);
		String date = gf.currentDate();
		action.setValueInTextField(txtCurrentDate, date);
		action.clickLink(btnValdiateYes);

		action.waitForElementVisible(driver, statusUpdate);
		String actualMessage = driver.findElement(statusUpdate).getText();
		String expectedValue = "Active";
		assertEquals(expectedValue, actualMessage);
	}

	public void revokeMandate(boolean confirmMandateRevoke, String revocationRequestor, String Comment, String Date) {
		action.clickLink(btnRevokeMandate);

		if (confirmMandateRevoke) {
			action.clickLink(chkRevokeYesOption);

		} else {
			action.clickLink(chkRevokeNoOption);
		}
		if (revocationRequestor.equalsIgnoreCase("debitor")) {
			action.clickLink(chkRevokeWhoDebtor);

		} else {
			action.clickLink(chkRevokeWhoCreditor);
		}
		if (Comment != null && Comment != "") {

			action.setValueInTextField(txtRevokeComment, Comment);
		}
		if (Date != null && Date != "") {

			action.setValueInTextField(txtRevokeDateField, Date);
		}
		action.clickLink(btnRevokeSubmit);
		action.waitForPageToLoad();
		assertEquals(getMandateStatus(), "Revoked");
	}

	public String getMandateStatus() {

		return action.getText(statusUpdate);

	}

	public Mandates createActiveMandateWithUMR(Mandates mandate, String term, String status)
			throws InterruptedException {
		action.waitForPageToLoad();
		Select select0 = new Select(driver.findElement(By.xpath(".//*[@id='businessCodeSelectRequired']")));
		select0.selectByVisibleText("PQR");

		Thread.sleep(3000);
		action.waitForPageToLoad();
		Select select = new Select(driver.findElement(By.xpath(".//*[@id='creditorSelectRequired']")));
		select.selectByVisibleText(mandate.getSCI());

		Thread.sleep(3000);
		action.waitForPageToLoad();
		driver.findElement(txtDebitorName).click();

		action.setValueInTextField(txtDebitorName, mandate.getDebtorName());
		action.setValueInTextField(txtemail, mandate.getEmail());

		action.selectCheckboxIfUnchecked(checkboxUMR);
		action.clickLink(checkboxUMR);

		if (term.equals("First")) {
			String umrvalue = gf.randamGenerator();
			action.waitForPageToLoad();
			action.setValueInTextField(txtUmr, umrvalue);

			mandate.setUmr(umrvalue);
		} else {

			action.setValueInTextField(txtUmr, mandate.getUmr());

		}

		action.setValueInTextField(txtMandateCity, mandate.getNewCity());
		action.setValueInTextField(txtStreetName, mandate.getStreetName());
		action.setValueInTextField(txtCity, mandate.getCity());

		action.setValueInTextField(txtphoneNumber, mandate.getPhonenumberEr());
		action.setValueInTextField(txtIban, mandate.getDebtorIBAN());
		action.setValueInTextField(txtPostalCode, mandate.getPostalCode());

		Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='debtorAddress-addressCountry']")));
		select2.selectByVisibleText("France");

		String date = gf.currentDate();
		action.setValueInTextField(txtSignDate, date);

		Select select1 = new Select(driver.findElement(By.xpath(".//*[@id='debtorAddress-addressType']")));
		select1.selectByVisibleText("Home address");

		action.saveScreenshot();
		action.clickLink(btnCreateMandate);

		if (term.equals("SameUMR")) {
			String actualMessage = driver.findElement(errorcode).getText();
			String expMessage = "already exists for SCI";

			actualMessage.contains(expMessage);

		} else {
			action.waitForElementVisible(driver, lblActiveStatus);
			String actualMessage = driver.findElement(lblActiveStatus).getText();
			String expectedValue = status;
			assertEquals(expectedValue, actualMessage);
		}

		return mandate;
	}

	public Mandates getSCI(Mandates mandate) {
		mandate.getSCI();
		return mandate;
	}

	public Mandates updateIBAN(Mandates mandate) {
		action.clickLink(btnEditMandate);

		action.clearTextField(txtIban);

		action.setValueInTextField(txtIban, mandate.getUpdateIBAN());

		action.clickLink(txtCity);

		action.clickLink(btnUpdate);

		/*
		 * action.waitForElementVisible(driver, statusPending); need to add validation
		 * for value String status = driver.findElement(statusPending).getText();
		 * assertEquals(status, "Pending"); //action.clickLink(menuMandateSearch);
		 */ return mandate;
	}

	public Mandates editAndDoPlannedUpdate(Mandates mandate) {
		action.clickLink(btnEditMandate);

		action.clearTextField(txtIban);

		action.setValueInTextField(txtIban, mandate.getUpdateIBAN());

		action.clickLink(txtCity);

		action.clickLink(btnUpdate);

		/*
		 * action.waitForElementVisible(driver, statusPending); need to add validation
		 * for value String status = driver.findElement(statusPending).getText();
		 * assertEquals(status, "Pending"); //action.clickLink(menuMandateSearch);
		 */ return mandate;
	}
}
