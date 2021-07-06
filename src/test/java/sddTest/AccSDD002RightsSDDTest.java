package sddTest;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.CommonSectionPage;
import spspages.LoginPage;
import spspages.MandatePage;
import spspages.MandateSearchPage;
import spspages.UsersSearch;
import testbase.BasePage;
import testdataobjects.MandatesSearch;
import utilies.UserActions;

public class AccSDD002RightsSDDTest extends BasePage {
	static Logger logger = LogManager.getLogger(AccSDD002RightsSDDTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");
	MandatesSearch searchMandate = new MandatesSearch(mandate);
	LinkedTreeMap<String, ?> testData = JsonManager.getMapfromJson("testdata/testdata.json", "acc_SDD_002_RightsSDDTest");

	@Test(enabled = false) // Disabled as we don't know create sdd button is disabled with which permission
	public void acc_SDD_002_RightsSDDTest() throws InterruptedException {
		CommonSectionPage CommonPage = new CommonSectionPage();
		UsersSearch userSearch = new UsersSearch();
		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();
		MandatePage mp = new MandatePage();
		UserActions action = new UserActions();

		userSearch.editGrouplistFromUser("remove", "atos", "Validation d'un SDD: Validation d'un SDD");

		login.loginIntoSPS("default");
		action.selectMenu("Mandates Management", "Mandate Search");

		ms.mandateSearchWithActiveStatus(searchMandate);
		assertFalse(mp.isCreateSDDButtonDisabled(), "SDD button is not disabled");
		CommonPage.logout();

		userSearch.editGrouplistFromUser("add", "atos", "Validation d'un SDD: Validation d'un SDD");

		login.loginIntoSPS("default");
		action.selectMenu("Mandates Management", "Mandate Search");
		ms.mandateSearchWithActiveStatus(searchMandate);
		assertTrue(mp.isCreateSDDButtonDisabled(), "SDD button is disabled");
		CommonPage.logout();

	}

}