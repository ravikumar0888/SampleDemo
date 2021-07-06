package mandateTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.LoginPage;
import spspages.MandatePage;
import testbase.BasePage;
import testdataobjects.Mandates;
import utilies.UserActions;

public class PlannedMandateModificationCheckGUILevel002Test extends BasePage {
	static Logger logger = LogManager.getLogger(PlannedMandateModificationCheckGUILevel002Test.class.getName());



	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "EditMandate");
	Mandates createmandate = new Mandates(mandate);

	@Test(description = "To verify planned mandate update")
	public void plannedMandateModificationCheckGUILevel002() throws InterruptedException {
		UserActions action = new UserActions();

		LoginPage login = new LoginPage();
  		MandatePage mp = new MandatePage();

		login.loginIntoSPS("default");
		mp.mandateCreationWithUMR(createmandate);
		mp.searchUMR(createmandate);

	

	}

}