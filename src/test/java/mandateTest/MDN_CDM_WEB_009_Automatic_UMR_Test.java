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

public class MDN_CDM_WEB_009_Automatic_UMR_Test extends BasePage {
	static Logger logger = LogManager.getLogger(MDN_CDM_WEB_009_Automatic_UMR_Test.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "OnlyMandate");
	Mandates createmandate = new Mandates(mandate);

	@Description("To verify mandate creation in active status")
	@Test(description = "Test to check Mandate creation flow",enabled= false)

	public void MDN_CDM_WEB_009_Automatic_UMR() throws InterruptedException {
		LoginPage login = new LoginPage();
		MandatePage mp = new MandatePage();
		UserActions action = new UserActions();

		login.loginIntoSPS("mandateuser");

		action.selectMenu("Mandates Management", "New Mandate");
		mp.selectCreditorForMandateCreation(createmandate, "mandate");

		mp.searchUMR(createmandate);
		mp.editMandateWithSendForValidatation(createmandate);

		login.logout();

		login.loginIntoSPS("default");
		mp.selectMandateMangement();

		mp.searchUMR(createmandate);
		mp.validateMandate();
		login.logout();
	}
}
