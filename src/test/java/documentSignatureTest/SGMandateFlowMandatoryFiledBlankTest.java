package documentSignatureTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import pagesSpsSign.SignHomePage;
import pagesSpsSign.SignStep1Page;
import pagesSpsSign.SignStep2Page;
import pagesSpsSign.SignStep3Page;
import pagesSpsSign.SpsSignBackOfficePage;
import testbase.BasePage;
import testdataobjects.BackOfficeInitSession;
import utilies.UserActions;

public class SGMandateFlowMandatoryFiledBlankTest extends BasePage {
	static Logger logger = LogManager.getLogger(SGMandateFlowMandatoryFiledBlankTest.class.getName());

	@Test
	public void SGMandateFlowMandatoryFiledBlank() {

		UserActions action = new UserActions();
		SpsSignBackOfficePage signback = new SpsSignBackOfficePage();
		SignStep1Page signStep1 = new SignStep1Page();
		SignStep2Page signStep2 = new SignStep2Page();
		SignStep3Page signStep3 = new SignStep3Page();

		SignHomePage shp = new SignHomePage();


		shp.signOldbackOfficeURLLaunch();

		LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/backOfficeinitSession.json", "sessionParametersForSG");
		BackOfficeInitSession backofficeSession = new BackOfficeInitSession(mandate);

		String tranId = signback.SG_CusterSessionCreateWithoutMandatoryFields(backofficeSession);

		signStep1.fillingIbanEmailAndPhone(backofficeSession); // first parameter for DS and second Mandate
		signStep2.dsStep2GetOTPFOREmail(backofficeSession, tranId);
		signStep3.documentSignatureStep3();
		action.waitForPageURL(getDriver(), "http://127.0.0.1/okURL");
		action.validateURL("http://127.0.0.1/okURL");

		//	signStep3.documentSignatureStep3();

	}
}
