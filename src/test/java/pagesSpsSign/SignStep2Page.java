package pagesSpsSign;

import static org.testng.Assert.assertFalse;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import testbase.BasePage;
import testdataobjects.BackOfficeInitSession;
import testdataobjects.InitSession;
import utilies.DSConnection;
import utilies.UserActions;

public class SignStep2Page extends BasePage {
	static Logger logger = LogManager.getLogger(SignStep2Page.class.getName());

	// Step 2
	By checkTerminReuseMandate = By.name("documentLegalDisclaimerCheckbox");
	By chkAgreeTermsSMS = By.xpath("(//input[@id='agreetermsSms'])|(//input[@id='agreetermsEmail'])");
	By txtOTP = By.xpath("(//input[@id='emailConfirmationCode'])|(//input[@id='smsConfirmationCode'])");
	By txtOTP_DS_Mandate = By.xpath("(//input[@id='emailConfirmationCode'])|(//input[@id='smsConfirmationCode'])");
	By btnSignDocument = By.xpath("(//input[@id='submitSignEmailMandate'])|(//input[@id='submitSignSMSMandate'])");
	By btnSignMandateAndDocument = By
			.xpath("(//input[@id='submitSignEmailMandate'])|(//input[@id='submitSignSMSMandate'])");
	By btnDownloadDocument = By.id("dlpdf");
	By chkAgreeMandate = By.name("mandateLegalDisclaimerCheckbox");
	By chkAgreeDocumentSignature = By.name("documentLegalDisclaimerCheckbox");

	By btnResendOTP = By.xpath("(//input[@id='submitNewOTPSMS'])|(//input[@id='submitNewOTPEmail'])");

	// WebDriver driver = getDriver();
	UserActions action = new UserActions();
RestEndPoints res = new RestEndPoints();
	public void documentSignatureStep2(InitSession initSession) {

		action.selectCheckboxIfUnchecked(chkAgreeTermsSMS);


		action.setValueInTextField(txtOTP_DS_Mandate,
		res.getOTPForDS(initSession.getCreditorParameters().getCreditorId(),
				initSession.getSessionParameters().getTransactionId()));

		action.clickButton(btnSignMandateAndDocument);

	}

	public void documentSignatureStep2ForReuse(InitSession initSession) {

		action.waitForPageToLoad();
		action.selectCheckboxIfUnchecked(checkTerminReuseMandate);

		action.setValueInTextField(txtOTP,res.getOTPForDS(initSession.getCreditorParameters().getCreditorId(),// getEmailOTP(initSession.getCreditorParameters().getCreditorId(),
				initSession.getSessionParameters().getTransactionId()));

		action.clickButton(btnSignDocument);

	}

	public void dsStep2GetOTP(BackOfficeInitSession backofficeSession, String TranscationId) {
		step2FillOtp("SMS", backofficeSession.getCreditorId(), TranscationId);

	}

	By chkagreeTermEMAIl = By.name("agreetermsEmail");

	public void dsStep2GetOTPFOREmail(BackOfficeInitSession backofficeSession, String TranscationId) {
		step2FillOtp("EMAIL", backofficeSession.getCreditorId(), TranscationId);

	}

	public void step2FillOtp(String agreementScheme, String creditorId, String transcationId) {
		action.waitForPageToLoad();

		if (agreementScheme.equalsIgnoreCase("SMS")) {
			action.selectCheckboxIfUnchecked(chkAgreeTermsSMS);
		} else {
			action.selectCheckboxIfUnchecked(chkagreeTermEMAIl);
		}
		logger.info("creditor id  {}", creditorId);
		logger.info("transdcation {}", transcationId);

		action.setValueInTextField(txtOTP,res.getOTPForDS(creditorId, transcationId));				// getEmailOTP(creditorId, transcationId));

		action.clickButton(btnSignDocument);

	}

/*	public String getEmailOTP(String creditor, String transactionID) {
		DSConnection dsquery = new DSConnection();

		String otp = null;
		try {
			otp = dsquery.getOTP(creditor, transactionID);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (otp != null) {
			logger.info("OTP successfully retrieved");
		} else {
			logger.error("OTP value is null ");
			assertFalse(true);
		}
		return otp;

	}*/

	public void resendOTP(InitSession initSession) {
		action.selectCheckboxIfUnchecked(chkAgreeMandate);
		action.selectCheckboxIfUnchecked(chkAgreeDocumentSignature);

		String firstotp =res.getOTPForDS(initSession.getCreditorParameters().getCreditorId(),					// getEmailOTP(initSession.getCreditorParameters().getCreditorId(),
				initSession.getSessionParameters().getTransactionId());
		logger.info("First OTP is " + firstotp);

		action.clickLink(btnResendOTP);

		action.selectCheckboxIfUnchecked(chkAgreeMandate);
		action.selectCheckboxIfUnchecked(chkAgreeDocumentSignature);

		String Sectotp =res.getOTPForDS(initSession.getCreditorParameters().getCreditorId(),					// getEmailOTP(initSession.getCreditorParameters().getCreditorId(),
				initSession.getSessionParameters().getTransactionId());
		logger.info("Second OTP is " + Sectotp);

		action.setValueInTextField(txtOTP_DS_Mandate, Sectotp);

		action.clickButton(btnSignMandateAndDocument);

	}

	public void signSepaMandateAndDocumentSignature(InitSession initSession) {

		action.selectCheckboxIfUnchecked(chkAgreeMandate);
		action.selectCheckboxIfUnchecked(chkAgreeDocumentSignature);
		
		action.setValueInTextField(txtOTP,
				res.getOTPForDS(initSession.getCreditorParameters().getCreditorId(),
						initSession.getSessionParameters().getTransactionId()));

	/*	
		
		action.setValueInTextField(txtOTP, getEmailOTP(initSession.getCreditorParameters().getCreditorId(),
				initSession.getSessionParameters().getTransactionId()));*/
		action.clickButton(btnSignDocument);

	}

}
