package mandateTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import io.qameta.allure.Description;
import spspages.LoginPage;
import spspages.MandatePage;
import testbase.BasePage;
import testdataobjects.Mandates;
import utilies.UserActions;

public class MND_GSM_WEB_001_Mandate_RevokeTest extends BasePage {
	static Logger logger = LogManager.getLogger(MND_GSM_WEB_001_Mandate_RevokeTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "OnlyMandate");
	Mandates createmandate = new Mandates(mandate);

	@Description("Test to verify mandate revoke functionality")
	@Test(description = "Test to verify mandate revoke functionality",enabled= false)

	public void MND_GSM_WEB_001_Mandate_Revoke() throws InterruptedException {
		LoginPage login = new LoginPage();
		UserActions action = new UserActions();
		MandatePage mp = new MandatePage();

		login.loginIntoSPS("mandateuser");
		logger.info("logged in as a mandate user");
		action.selectMenu("Mandates Management", "New Mandate");
		mp.selectCreditorForMandateCreation(createmandate, "mandate");
		mp.searchUMR(createmandate);
		mp.editMandateWithSendForValidatation(createmandate);

		login.logout();
		login.loginIntoSPS("default");
		action.selectMenu("Mandates Management", "New Mandate");
		mp.searchUMR(createmandate);
		mp.validateMandate();
		mp.revokeMandate(true, "creditor", "revoking mandate", null);
	}

}
