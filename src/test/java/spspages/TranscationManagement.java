package spspages;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import testbase.BasePage;
import testdataobjects.SDD;
import utilies.GenericFunctions;
import utilies.UserActions;

public class TranscationManagement extends BasePage {
	static Logger logger = LogManager.getLogger(BasePage.class.getName());
	UserActions action = new UserActions();
	WebDriver driver = getDriver();

	// login

	By btnSDDCollectionGeneration = By.xpath(".//*[@id='btbartop']/div/a[contains(text(),'SDD Collections generation')]");
	By btnPain008FileGeneration = By.xpath("//*[@id='btbartop']/div/a[contains(text(),'Generation of pain.008 files')]");

	By msgSDDGeneration = By.xpath("//*[@id='btbartop']/ul/li[1]/h3");
	By subMenuCollection = By.xpath(".//*[@id='pageMenu']/ul/ul/li[6]/a");

	By subMenuCollectionsFile = By.xpath(".//*[@id='pageMenu']/ul/ul/li[4]/a");

	By txtType = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[5]");
	By txtNoOfCollection = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[6]");
	By txtNoOfPayment = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr[1]/td[7]");
	By chkAdvanceSearch = By.id("advancedSearchMode");

	By btnCollectionSearch = By.xpath(".//*[@id='searchButtons']/input[1]");

	//Home > Creditors > Transactions : Search
	By btnSearchTranscation = By.xpath("//*[@id='searchButtons']/input[1]");

	By btnReset = By.name("reset");

	By status = By.xpath("//*[@id='resultList']/div[2]/table/tbody/tr/td[6]");

	By menuSearchTranscation = By.xpath(".//*[@id='pageMenu']/ul/ul/li[1]/a");

	By txtEndToEndId = By.id("endToEndIdField");
	By txtFileName = By.id("creationFileName");
	By txtInitiatorFileReference = By.id("initiatorFileRef");
	By txtDebtorExternalID = By.id("debtorExtId");
	By txtInitiatorTransactionReference = By.id("initiatorTxRefField");
	By txtInitiatorCollectionReference = By.id("initiatorCollRefField");
	By txtMinAmount = By.id("minimumAmount");
	By txtMaxAmount = By.id("maximumAmount");
	By txtUMR = By.id("umr");
	By txtUIR = By.id("uir");
	By txtIBAN = By.id("debtorIbanField");
	By cbSDD = By.xpath("//*[@id='enableSddSearchCheckbox'] ");
	By cbFixPeriod = By.id("selectRange");
	By cbVariablePeriod = By.id("defineRange");

	By cbSCTSearch = By.id("enableSctSearchCheckbox");

	By selScheme = By.id("typeMandateSelect");
	By selSettlementMode = By.id("settlementModeSelect");
	By selStatus = By.id("statusSelect");
	By selSequenceType = By.id("sddSequenceTypeSelect");

	By selInitiatorAccount = By.id("bankAccounts");
	By selCreationType = By.id("creationTypeSelect");
	By dtStartCreationDateField = By.id("startCreationDateField");
	By dtEndCreationDateField = By.id("endCreationDateField");
	By dtTransactionBeginingDueDateField = By.id("transactionBeginingDueDateField");
	By dtTransactionEndingDueDateField = By.id("transactionEndingDueDateField");
	By dtTransactionBeginingValueDateField = By.id("transactionBeginingValueDateField");
	By dtTransactionEndingValueDateField = By.id("transactionEndingValueDateField");
	By lblResultEndToEndId = By.xpath("//div[@id='resultList']//td[@class='endToEndId'][1]");
	By lblResultDebtorIBAN = By.xpath("//div[@id='resultList']//td[@class='debtorIban'][1]");
	By lblResultDueDate = By.xpath("//div[@id='resultList']//td[@class='dueDate'][1]");
	By ddStatus = By.xpath(".//*[@id='statusSelect']");
	By menuTranscationManagement = By.xpath(".//*[@id='pageMenu']/ul/li/a[contains(text(),'Transactions Management')]");
	By orgDD = By.xpath(".//*[@id='organizationSelect']");
	By sddCollection = By.xpath(".//*[@id='pageMenu']/ul/ul/li/a[contains(text(),'Search for Collections')]");

	By txtpaymentRef = By.xpath(".//*[@id='paymentRef']");

	By btnSearchCollection = By.xpath(".//*[@id='searchButtons']/input[1]");

	By iconCollectionDetails = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[13]/a[1]/img");

	By btnCancelCollection = By.xpath(".//*[@id='cancelLink']");

	By stautsCollection = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[3]");

	By btnvalidate = By.xpath(".//*[@id='cancelPopupForm']/div/input[@name='submit']");
	@Step("User search the transaction in advance search mode and validate the status.")
	public void advanceSearchTranscation(SDD sdd) {
		driver.findElement(btnReset).click();
		if (!driver.findElement(chkAdvanceSearch).isSelected()) {
			driver.findElement(chkAdvanceSearch).click();
		}

		action.setValueInTextField(txtEndToEndId, sdd.getEndToEndId());

		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement show = wait.until(ExpectedConditions.elementToBeClickable(txtInitiatorTransactionReference));

		action.setValueInTextField(txtInitiatorTransactionReference, sdd.getInitiatorTransactionReference());
		action.setValueInTextField(txtInitiatorCollectionReference, sdd.getInitiatorCollectionReference());
		action.setValueInTextField(txtIBAN, sdd.getIban());
		action.setValueInTextField(txtUMR, sdd.getUmr());
		action.setValueInTextField(txtUIR, sdd.getUir());

		if (driver.findElement(cbSDD).isSelected() == false) {
			action.clickLink(cbSDD);
		} else {
			logger.info("SDD checkboc already selected...");
		}

		action.clickLink(btnSearchTranscation);

		//		driver.navigate().refresh();
		action.waitForElementVisible(driver, status);
		action.saveScreenshot();
		String actualMessage = driver.findElement(lblResultEndToEndId).getText();
		assertEquals(actualMessage, sdd.getEndToEndId());
	}
	@Step("User search the transaction in advance search mode with debtor IBAN and validate the status.")
	public void advanceSearchTranscation(String debtorIBAN) {
		driver.findElement(btnReset).click();
		if (!driver.findElement(chkAdvanceSearch).isSelected()) {
			driver.findElement(chkAdvanceSearch).click();
		}

		action.setValueInTextField(txtIBAN, debtorIBAN);

		if (driver.findElement(cbSDD).isSelected() == false) {
			action.clickLink(cbSDD);
		} else {
			logger.info("SDD checkbox already selected...");
		}

		action.clickLink(btnSearchTranscation);

		//		driver.navigate().refresh();
		action.waitForElementVisible(driver, status);
		action.saveScreenshot();
		String actualMessage = driver.findElement(lblResultDebtorIBAN).getText();
		assertEquals(actualMessage, debtorIBAN);
	}

	@Step("User search the transaction in advance search mode with Initiator transaction reference and validate the status.")
	public void advanceSearchTranscation(String initTranRef, String endToEndId) {
		driver.findElement(btnReset).click();
		if (!driver.findElement(chkAdvanceSearch).isSelected()) {
			driver.findElement(chkAdvanceSearch).click();
		}

		action.setValueInTextField(txtInitiatorTransactionReference, initTranRef);

		if (driver.findElement(cbSDD).isSelected() == false) {
			action.clickLink(cbSDD);
		} else {
			logger.info("SDD checkbox already selected...");
		}

		action.clickLink(btnSearchTranscation);

		//		driver.navigate().refresh();
		action.waitForElementVisible(driver, status);
		action.saveScreenshot();
		String actualMessage = driver.findElement(lblResultEndToEndId).getText();
		assertEquals(actualMessage, endToEndId);
	}

	@Step("User search the transaction in advance search mode with debtor IBAN and validate the status.")
	public void advanceSearchTranscationWithDueDate(String dueDate) {
		GenericFunctions gf = new GenericFunctions();
		driver.findElement(btnReset).click();
		if (!driver.findElement(chkAdvanceSearch).isSelected()) {
			driver.findElement(chkAdvanceSearch).click();
		}

		String formattedDueDate = gf.dateDisplayFormatToInputFormat(dueDate);

		driver.findElement(dtTransactionBeginingDueDateField).sendKeys(formattedDueDate);
		driver.findElement(dtTransactionEndingDueDateField).sendKeys(formattedDueDate);

		if (driver.findElement(cbSDD).isSelected() == false) {
			action.clickLink(cbSDD);
		} else {
			logger.info("SDD checkbox already selected...");
		}

		action.clickLink(btnSearchTranscation);

		//		driver.navigate().refresh();
		action.waitForElementVisible(driver, status);
		action.saveScreenshot();
		String actualMessage = driver.findElement(lblResultDueDate).getText();
		assertEquals(actualMessage, dueDate);
	}
	@Step("User generate SDD Collections and Pain 008 file")
	public void sddCollectionAndPainFileGeneration() throws InterruptedException {
		// action.clickLink(subMenuCollection);

		action.selectMenu("Transactions Management", "Collection Generation");

		action.waitForElementClickable(driver, btnSDDCollectionGeneration);
		action.clickLink(btnSDDCollectionGeneration);
		action.saveScreenshot();
		//	action.waitForElementVisible(driver, msgSDDGeneration);

		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(msgSDDGeneration));

		String actualMessage = driver.findElement(msgSDDGeneration).getText();
		String expectedValue = "Collection generation started successfully";
		assertEquals(actualMessage, expectedValue);

		Thread.sleep(10000); // application needed 10 sec time 
		action.saveScreenshot();
		action.clickLink(btnPain008FileGeneration);

		WebDriverWait wait2 = new WebDriverWait(driver, 40);
		WebElement msggeneration = wait2.until(ExpectedConditions.visibilityOfElementLocated(msgSDDGeneration));

		String actualMessagePain = driver.findElement(msgSDDGeneration).getText();
		String expectedValuePain = "Pain.008 file generation was initiated";
		assertEquals(actualMessagePain, expectedValuePain);
		Thread.sleep(10000); // application needed 10 sec time 
	}
	@Step("User generate SDD Collections")
	public void sddCollectionGeneration() throws InterruptedException {
		action.clickLink(subMenuCollection);

		action.saveScreenshot();
		action.clickLink(btnSDDCollectionGeneration);

		logger.info("User generating SDD Collection....");
		action.saveScreenshot();

		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(msgSDDGeneration));

		String actualMessage = driver.findElement(msgSDDGeneration).getText();
		String expectedValue = "Collection generation started successfully";
		assertEquals(actualMessage, expectedValue);
		Thread.sleep(10000); // as per application 10 sec wait is needed.
	}
	@Step("User generate SDD pain 008")
	public void sddGenerate008File() throws InterruptedException {
		action.clickLink(subMenuCollection);
		action.saveScreenshot();
		action.clickLink(btnPain008FileGeneration);
		logger.info("User generating Pain 008 file generation....");
		action.saveScreenshot();

		WebDriverWait wait = new WebDriverWait(driver, 40);
		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(msgSDDGeneration));

		String actualMessage = driver.findElement(msgSDDGeneration).getText();
		String expectedValue = "Collection generation started successfully";
		assertEquals(actualMessage, expectedValue);
		Thread.sleep(10000); // application needed 10 sec time 
	}
	public void searchCollectionFile(String endid) throws InterruptedException {

		action.clickLink(sddCollection);
		action.setValueInTextField(txtpaymentRef, endid);

		Thread.sleep(8000);
		action.clickLink(btnSearchCollection);

		WebDriverWait wait = new WebDriverWait(driver, 90);

		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(iconCollectionDetails));

		//	Thread.sleep(5000);

		action.clickLink(iconCollectionDetails);
		String actualMessage = driver.findElement(stautsCollection).getText();
		String expectedValue = "Generated";
		assertEquals(actualMessage, expectedValue);

		action.clickLink(btnCancelCollection);

		action.clickLink(btnvalidate);

	}
	@Step("User search the transaction and validate the status.")

	public void searchTranscationAndValidateStatus(String id, String expected) throws InterruptedException {

		action.selectMenu("Transactions Management", "Search for Transactions");

		action.clearTextField(txtEndToEndId);
		action.setValueInTextField(txtEndToEndId, id);
		action.saveScreenshot();

		boolean chkSDD = driver.findElement(cbSDD).isSelected();

		if (chkSDD == false) {
			action.clickLink(cbSDD);
		} else {
			logger.info("SDD check box already selected...");
		}

		action.clickLink(btnSearchTranscation);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(status));

		action.saveScreenshot();
		String actualMessage = driver.findElement(status).getText();
		assertEquals(actualMessage, expected);
	}
	
	
	
	By seqenceType  = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[4]");

	public void searchTranscationAndValidateSequenceType(String id, String expected) throws InterruptedException {

		action.selectMenu("Transactions Management", "Search for Transactions");

		action.clearTextField(txtEndToEndId);
		action.setValueInTextField(txtEndToEndId, id);
		action.saveScreenshot();

		boolean chkSDD = driver.findElement(cbSDD).isSelected();

		if (chkSDD == false) {
			action.clickLink(cbSDD);
		} else {
			logger.info("SDD check box already selected...");
		}

		action.clickLink(btnSearchTranscation);

		WebDriverWait wait = new WebDriverWait(driver, 90);
		WebElement show = wait.until(ExpectedConditions.visibilityOfElementLocated(status));

		action.saveScreenshot();
		String actualMessage = driver.findElement(seqenceType).getText();
		assertEquals(actualMessage, expected);
	}
	
	
	
	@Step("User search the transaction using status.")
	public void searchTranscationUsingStatus(String status1, String transstatus) {

		action.selectMenu("Transactions Management", "Search for Transactions");

		// action.clickLink(menuTranscationManagement);
		action.selectvaluesdropdown(orgDD, "ztestDoS");
		action.selectvaluesdropdown(ddStatus, status1);
		action.saveScreenshot();

		boolean chkSDD = driver.findElement(cbSDD).isSelected();

		if (chkSDD == false) {
			action.clickLink(cbSDD);
		} else {
			logger.info("enable sdd checkbox is already selected");
		}

		action.clickLink(btnSearchTranscation);

		driver.navigate().refresh();
		action.waitForPageToLoad();
		action.waitForElementVisible(driver, status);
		action.saveScreenshot();
		String actualMessage = driver.findElement(status).getText();
		String expectedValue = transstatus;
		assertEquals(actualMessage, expectedValue);
	}

	@Step("User validate type, collections and payments.")
	public void validateTypeCollectionsPayments() throws InterruptedException {
		action.clickLink(subMenuCollectionsFile);

		Thread.sleep(5000);
		action.clickLink(btnCollectionSearch);
		action.saveScreenshot();
		action.waitForElementVisible(driver, txtType);
		String actualMessagePain = driver.findElement(txtType).getText();
		String expectedValuePain = "Issued";
		assertEquals(actualMessagePain, expectedValuePain);

		String actualMessage = driver.findElement(txtNoOfCollection).getText();
		String expectedValue = "1";
		assertEquals(actualMessage, expectedValue);

		String actualMessage1 = driver.findElement(txtNoOfPayment).getText();
		String expectedValue1 = "1";
		assertEquals(expectedValue1, actualMessage1);
	}
	
	By cbSCT = By.xpath(".//*[@id='enableSctSearchCheckbox']");
	
	@Step("User search the transaction and validate the status.")
	public void searchSCTTranscation(String id) throws InterruptedException {

		action.selectMenu("Transactions Management", "Search for Transactions");

		action.clearTextField(txtEndToEndId);
		action.setValueInTextField(txtEndToEndId, id);
		action.saveScreenshot();

		boolean chkSDD = driver.findElement(cbSCT).isSelected();

		if (chkSDD == false) {
			action.clickLink(cbSCT);
		} else {
			logger.info("SCT check box already selected...");
		}

		action.clickLink(btnSearchTranscation);

			}
By txtCollectionSearchID = By.xpath(".//*[@id='paymentRef']");
By imgDonwloadFile = By.xpath(".//*[@id='resultList']/div[2]/table/tbody/tr/td[10]/a[2]/img");
	
	public void searchCollectionFilePain008(String endid)
	{
		action.selectMenu("Transactions Management", "Search for Collection Files");
		action.setValueInTextField(txtCollectionSearchID, endid);
		action.clickLink(btnSearchTranscation);
		action.clickLink(imgDonwloadFile);
	}

	public void validatePain008File(String xmldata, String UMR)
	{
		//String endtoend =sdd.getEndToEndId();
		xmldata.contains(UMR);
		
	
		
	}
	
}
