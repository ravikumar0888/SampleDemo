package pagesSpsSign;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.internal.LinkedTreeMap;

import testbase.BasePage;
import testdataobjects.BackOfficeInitSession;
import utilies.GenericFunctions;
import utilies.UserActions;;

public class SpsSignBackOfficePage extends BasePage {

	static Logger logger = LogManager.getLogger(SpsSignBackOfficePage.class.getName());

	UserActions action = new UserActions();
	protected WebDriver driver = getDriver();
	GenericFunctions gf = new GenericFunctions();

	By txtCreditorId = By.xpath(".//*[@id='creditorId']");
	By txtTranscationId = By.xpath(".//*[@id='transactionId']");
	By chbValidation = By.id("validation");

	By chbSkipSummaryPage = By.xpath(".//*[@id='skipSummaryPage']");
	By txtUIR = By.id("UIR");
	By ddAgreementScheme = By.xpath(".//*[@id='agreementScheme']");

	By txtIBAN = By.xpath(".//*[@id='IBAN']");
	By txtBIC = By.xpath(".//*[@id='BIC']");
	By txtDebitorBBAN = By.xpath(".//*[@id='dbtrBBAN']");
	By txtDebitorCountry = By.xpath(".//*[@id='dbtrCtry']");

	By txtSCICountry = By.xpath(".//*[@id='country']");
	By txtSCICheckdigit = By.xpath(".//*[@id='checkDigit']");
	By txtSCIBusinessCode = By.xpath(".//*[@id='businessCode']");
	By txtNationalIdentifer = By.xpath(".//*[@id='nationalIdentifier']");

	By txtSignerInfoLastname = By.xpath(".//*[@id='lastName']");
	By txtSignerInfoFirstname = By.xpath(".//*[@id='firstName']");
	By txtSingerInfoGender = By.xpath(".//*[@id='gender']");
	By txtSingerInfoEmail = By.xpath(".//*[@id='email']");
	By txtSingerInfoAddress = By.xpath(".//*[@id='address']");
	By txtPhoneNumber = By.xpath(".//*[@id='phone']");

	By btnSubmitSession = By.id("submitInitSession");
	By btnUseSession = By.xpath("//div[@id='homeContent']/a");

	By txtBuildingNumber = By.xpath(".//*[@id='bldngNm']");
	By txtStreetNumber = By.xpath(".//*[@id='strtNm']");
	By txtPostalCode = By.xpath(".//*[@id='pstCd']");
	By txtCity = By.xpath(".//*[@id='city']");
	By txtCountry = By.xpath(".//*[@id='ctry']");

	/* Mandate update page */
	By txtUMR = By.id("UMR");
	By txtLanguage = By.id("language");
	By txtUrlOK = By.id("urlOK");
	By txtUrlKO = By.id("urlKO");
	By txtUrlCancel = By.id("urlCancel");
	By txtCountryMandate = By.id("country");
	By txtCheckDigit = By.id("checkDigit");
	By txtbusinessCode = By.id("businessCode");
	By txtNationalIdentifier = By.id("nationalIdentifier");
	By btnSubmitInitSession = By.id("submitInitSession");
	By lblCD = By.id("td_stsCd");
	By lblURL = By.id("td_url");

	By btnEditorCreditor = By.name("editCreditor");

	By chkEmailrequiredStep1 = By.name("creditorEmailMandatoryStep1");

	By btnConfirm = By.name("confirm");

	By chkDisablereturnMandate = By.name("creditorDisableReturnMandateInputPageButton");

	public void activateOptionForEmailMandateStep1() {
		//	action.clickLink(btnEditorCreditor);
		action.clickLink(chkEmailrequiredStep1);
		action.clickLink(btnConfirm);
	}
	public void activateReturnMandate() {
		//	action.clickLink(btnEditorCreditor);
		action.clickLink(chkDisablereturnMandate);
		action.clickLink(btnConfirm);
	}
	public void deActivateOptionForEmailMandateStep1() {
		//	action.clickLink(btnEditorCreditor);
		action.clickLink(chkEmailrequiredStep1);
		action.clickLink(btnConfirm);
	}

	public void deActivateReturnMandate() {
		//	action.clickLink(btnEditorCreditor);
		action.clickLink(chkDisablereturnMandate);
		action.clickLink(btnConfirm);
	}

	public void fillInitSessionFormMandateUpdate(LinkedTreeMap<String, String> formData) {
		action.setValueInTextField(txtTranscationId, formData.get("TransactionId"));
		action.setValueInTextField(txtCreditorId, formData.get("CreditorId"));
		action.setValueInTextField(txtUMR, formData.get("UMR"));
		action.setValueInTextField(txtLanguage, formData.get("Language"));
		action.setValueInTextField(txtUrlOK, formData.get("UrlOK"));
		action.setValueInTextField(txtUrlKO, formData.get("UrlKO"));
		action.setValueInTextField(txtUrlCancel, formData.get("UrlCancel"));
		action.setValueInTextField(txtCountryMandate, formData.get("Country"));
		action.setValueInTextField(txtCheckDigit, formData.get("CheckDigit"));
		action.setValueInTextField(txtbusinessCode, formData.get("BusinessCode"));
		action.setValueInTextField(txtNationalIdentifier, formData.get("NationalIdentifier"));

		action.clickButton(btnSubmitInitSession);

	}

	public String getUrlMandateUpdate() {
		String URL = null;

		if (action.getText(lblCD).contains("OK")) {
			URL = action.getText(lblURL);
			assertFalse(URL == null);
		}

		return URL;
	}

	public String lDECusterSessionCreate(BackOfficeInitSession backofficeSession) {

		action.setValueInTextField(txtCreditorId, backofficeSession.getCreditorId());
		String transcationId = gf.randamGenerator();
		action.setValueInTextField(txtTranscationId, transcationId);
		action.clickLink(chbSkipSummaryPage);
		action.selectvaluesdropdown(ddAgreementScheme, backofficeSession.getDdAgreementScheme());
		//	action.setValueInTextField(ddAgreementScheme, backofficeSession.getDdAgreementScheme());
		String UIR = gf.randamGenerator();
		action.setValueInTextField(txtUIR, UIR);
		action.setValueInTextField(txtLanguage, backofficeSession.getLanguage());

		action.setValueInTextField(txtIBAN, backofficeSession.getIBAN());
		action.setValueInTextField(txtDebitorBBAN, backofficeSession.getDebitorBBAN());
		action.setValueInTextField(txtDebitorCountry, backofficeSession.getDebitorCountry());
		action.setValueInTextField(txtSCICountry, backofficeSession.getSCICountry());
		action.setValueInTextField(txtSCICheckdigit, backofficeSession.getSCICheckdigit());
		action.setValueInTextField(txtSCIBusinessCode, backofficeSession.getSCIBusinessCode());
		action.setValueInTextField(txtNationalIdentifer, backofficeSession.getNationalIdentifer());
		// Postal Address
		action.setValueInTextField(txtBuildingNumber, backofficeSession.getBuildingNumber());
		action.setValueInTextField(txtStreetNumber, backofficeSession.getStreetNumber());
		action.setValueInTextField(txtPostalCode, backofficeSession.getPostalCode());
		action.setValueInTextField(txtCity, backofficeSession.getCity());
		action.setValueInTextField(txtCountry, backofficeSession.getCountry());

		//Signer info
		action.setValueInTextField(txtSignerInfoLastname, backofficeSession.getSignerInfoLastname());
		action.setValueInTextField(txtSignerInfoFirstname, backofficeSession.getSignerInfoFirstname());
		//action.setValueInTextField(txtSingerInfoGender, backofficeSession.getSingerInfoGender());
		action.selectvaluesdropdown(txtSingerInfoGender, backofficeSession.getSingerInfoGender());
		//	action.setValueInTextField(txtSingerInfoAddress, backofficeSession.getSingerInfoAddress());
		action.setValueInTextField(txtPhoneNumber, backofficeSession.getPhoneNumber());
		action.setValueInTextField(txtSingerInfoEmail, backofficeSession.getSingerInfoEmail());

		action.clickButton(btnSubmitSession);
		action.clickButton(btnUseSession);
		return transcationId;
	}

	public void navigateToMandateUpdatePage() {
		action.clickLink(By.partialLinkText("Mandate Update Test"));
		logger.info("Clicked on Mandate Update Test Page");
		
		// Add popup code 
	}

	public String SG_CusterSessionCreate(BackOfficeInitSession backofficeSession) {

		action.setValueInTextField(txtCreditorId, backofficeSession.getCreditorId());
		String transcationId = gf.randamGenerator();
		action.setValueInTextField(txtTranscationId, transcationId);
		if(backofficeSession.isChbSkipSummaryPage()) {
			action.clickLink(chbSkipSummaryPage);
		}
		action.clickLink(chbValidation);

		action.waitForPageToLoad();
		action.selectvaluesdropdown(ddAgreementScheme, backofficeSession.getDdAgreementScheme());
		//		action.setValueInTextField(ddAgreementScheme, backofficeSession.getDdAgreementScheme());
		String UIR = gf.randamGenerator();
		action.setValueInTextField(txtUIR, UIR);
		//	for testing
		action.setValueInTextField(txtIBAN, backofficeSession.getIBAN());
		// Postal Address
		action.setValueInTextField(txtBuildingNumber, backofficeSession.getBuildingNumber());
		action.setValueInTextField(txtStreetNumber, backofficeSession.getStreetNumber());
		action.setValueInTextField(txtPostalCode, backofficeSession.getPostalCode());
		action.setValueInTextField(txtCity, backofficeSession.getCity());
		action.setValueInTextField(txtCountry, backofficeSession.getCountry());

		// Signer Info
		action.setValueInTextField(txtSignerInfoLastname, backofficeSession.getSignerInfoLastname());
		action.setValueInTextField(txtSignerInfoFirstname, backofficeSession.getSignerInfoFirstname());

		action.selectvaluesdropdown(txtSingerInfoGender, backofficeSession.getSingerInfoGender());
		//	action.setValueInTextField(txtSingerInfoGender, backofficeSession.getSingerInfoGender());
		action.setValueInTextField(txtSingerInfoAddress, backofficeSession.getSingerInfoAddress());

		action.setValueInTextField(txtPhoneNumber, backofficeSession.getPhoneNumber());
		action.setValueInTextField(txtSingerInfoEmail, backofficeSession.getSingerInfoEmail());

		action.clickButton(btnSubmitSession);
		action.clickButton(btnUseSession);
		return transcationId;
	}

	public String SG_CusterSessionCreateWithoutMandatoryFields(BackOfficeInitSession backofficeSession) {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement show = wait.until(ExpectedConditions.elementToBeClickable(txtCreditorId));

		action.setValueInTextField(txtCreditorId, backofficeSession.getCreditorId());

		String transcationId = gf.randamGenerator();
		action.setValueInTextField(txtTranscationId, transcationId);
		action.clickLink(chbSkipSummaryPage);
		action.selectvaluesdropdown(ddAgreementScheme, backofficeSession.getDdAgreementScheme());
		//	action.setValueInTextField(ddAgreementScheme, backofficeSession.getDdAgreementScheme());
		String UIR = gf.randamGenerator();
		action.setValueInTextField(txtUIR, UIR);

		// Postal Address
		action.setValueInTextField(txtBuildingNumber, backofficeSession.getBuildingNumber());
		action.setValueInTextField(txtStreetNumber, backofficeSession.getStreetNumber());
		action.setValueInTextField(txtPostalCode, backofficeSession.getPostalCode());
		action.setValueInTextField(txtCity, backofficeSession.getCity());
		action.setValueInTextField(txtCountry, backofficeSession.getCountry());

		// Signer Info
		action.setValueInTextField(txtSignerInfoLastname, backofficeSession.getSignerInfoLastname());
		action.setValueInTextField(txtSignerInfoFirstname, backofficeSession.getSignerInfoFirstname());
		action.selectvaluesdropdown(txtSingerInfoGender, backofficeSession.getSingerInfoGender());
		//	action.setValueInTextField(txtSingerInfoGender, backofficeSession.getSingerInfoGender());
		//	action.setValueInTextField(txtSingerInfoAddress, backofficeSession.getSingerInfoAddress());

		action.clickButton(btnSubmitSession);
		action.clickButton(btnUseSession);
		return transcationId;
	}

	public void verifyMandateUpdateDisabled() {

		assertTrue(action.getText(lblCD).contains("SIGN_ERR_001"), "MandateUpdate error SIGN_ERR_001 is not present");

	}
	public BackOfficeInitSession SMoney_CustomerSessionCreateWithoutPostal(BackOfficeInitSession backofficeSession) {

		action.setValueInTextField(txtCreditorId, backofficeSession.getCreditorId());
		String transcationId = gf.randamGenerator();
		action.setValueInTextField(txtTranscationId, transcationId);
		backofficeSession.setTranscationid(transcationId);
		action.clickLink(chbValidation);
		//action.clickLink(chbSkipSummaryPage);
		action.selectvaluesdropdown(ddAgreementScheme, backofficeSession.getDdAgreementScheme());
		//	action.setValueInTextField(ddAgreementScheme, backofficeSession.getDdAgreementScheme());
		String UIR = transcationId;
		action.setValueInTextField(txtUIR, UIR);
		action.setValueInTextField(txtIBAN, backofficeSession.getIBAN());

		// Signer Info
		action.setValueInTextField(txtSignerInfoLastname, backofficeSession.getSignerInfoLastname());
		action.setValueInTextField(txtSignerInfoFirstname, backofficeSession.getSignerInfoFirstname());
		action.selectvaluesdropdown(txtSingerInfoGender, backofficeSession.getSingerInfoGender());
		//	action.setValueInTextField(txtSingerInfoGender, backofficeSession.getSingerInfoGender());
		action.setValueInTextField(txtSingerInfoAddress, backofficeSession.getSingerInfoAddress());
		action.setValueInTextField(txtPhoneNumber, backofficeSession.getPhoneNumber());
		action.setValueInTextField(txtSingerInfoEmail, backofficeSession.getSingerInfoEmail());

		action.clickButton(btnSubmitSession);
		action.clickButton(btnUseSession);
		return backofficeSession;
	}

}
