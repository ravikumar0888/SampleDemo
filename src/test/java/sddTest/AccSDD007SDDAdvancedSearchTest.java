package sddTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.CommonSectionPage;
import spspages.LoginPage;
import spspages.MandateSearchPage;
import spspages.SDDPage;
import spspages.TranscationManagement;
import testbase.BasePage;
import testdataobjects.MandatesSearch;
import testdataobjects.SDD;
import utilies.UserActions;

public class AccSDD007SDDAdvancedSearchTest extends BasePage {
	static Logger logger = LogManager.getLogger(AccSDD007SDDAdvancedSearchTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");
	MandatesSearch searchMandate = new MandatesSearch(mandate);
	LinkedTreeMap<String, ?> testData = JsonManager.getMapfromJson("testdata/testdata.json", "acc_SDD_002_RightsSDDTest");

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	@Test
	public void acc_SDD_007_SDDAdvancedSearch() throws InterruptedException {
		CommonSectionPage CommonPage = new CommonSectionPage();
		UserActions action = new UserActions();
		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();
		SDDPage sdd = new SDDPage();
		TranscationManagement tm = new TranscationManagement();
		CommonSectionPage cs = new CommonSectionPage();

		login.loginIntoSPS("default");
		CommonPage.selectLanguage("English");

		cs.selectMandateMangement();
		ms.mandateSearchWithActiveStatus(searchMandate);
		createsdd.setEndToEndId(sdd.createSDD(createsdd).getEndToEndId());
		createsdd = sdd.enrichSddObject(createsdd);
		action.selectMenu("Transactions Management", "Search for Transactions");

		tm.advanceSearchTranscation(createsdd);
		tm.advanceSearchTranscation(createsdd.getInitiatorTransactionReference(), createsdd.getEndToEndId());
		tm.advanceSearchTranscation(createsdd.getIban());
		tm.advanceSearchTranscationWithDueDate(createsdd.getDueDate());
		CommonPage.logout();

	}

}