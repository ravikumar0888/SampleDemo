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

public class MND_UBB_000_IHM_M11_1Test extends BasePage {
	static Logger logger = LogManager.getLogger(MND_UBB_000_IHM_M11_1Test.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "OnlyMandate");
	Mandates createmandate = new Mandates(mandate);

	@Description("Test to verify BBAN functionality of mandate  creation")
	@Test(description = "Test to verify BBAN functionality of mandate  creation",enabled= false)

	public void MND_UBB_000_IHM_M11_1() throws InterruptedException {
		LoginPage login = new LoginPage();
		MandatePage mp = new MandatePage();
		UserActions action = new UserActions();

		login.loginIntoSPS("mandateuser");
		logger.info("logged in as a mandate user");
		action.selectMenu("Mandates Management", "New Mandate");
		// mp.selectCreditorForMandateCreation(createmandate,"mandate");
		mp.validateAndFillBBAN(false, createmandate);

	}

}

//Todo file upload and data validation with PDf 