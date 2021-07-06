package sddTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.LoginPage;
import spspages.MandatePage;
import spspages.MandateSearchPage;
import spspages.SDDPage;
import spspages.TranscationManagement;
import testbase.BasePage;
import testdataobjects.Mandates;
import testdataobjects.MandatesSearch;
import testdataobjects.SDD;
import utilies.UserActions;

public class AccSDD009lastSDDIssuedTest extends BasePage {
	static Logger logger = LogManager.getLogger(AccSDD009lastSDDIssuedTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");
	MandatesSearch searchMandate = new MandatesSearch(mandate);

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	LinkedTreeMap<String, ?> mandate2 = JsonManager.getMapfromJson("testdata/mandates.json", "EditMandate");
	Mandates createmandate = new Mandates(mandate2);

	@Test
	public void accSDD009lastSDDIssued() throws InterruptedException {
		UserActions action = new UserActions();
		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();
		SDDPage sdd = new SDDPage();
		MandatePage mp = new MandatePage();
		TranscationManagement tr = new TranscationManagement();

		login.loginIntoSPS("default");
		action.selectMenu("Mandates Management", "New Mandate");

		mp.selectCreditorForMandateCreation(createmandate,"");
		mp.searchUMR(createmandate);
		mp.editMandateWithDebitorDetails(createmandate);

		action.selectMenu("Mandates Management", "Mandate Search");

		ms.mandateSearchWithActiveStatus(searchMandate);
		SDD updatedSdd =sdd.createSDD(createsdd);
		tr.sddCollectionAndPainFileGeneration();
		tr.searchTranscationAndValidateStatus(updatedSdd.getEndToEndId(), "Issued");

		action.selectMenu("Mandates Management", "Mandate Search");

		ms.issuedSDDSearch();

		// mp.searchUMR(createmandate);
		mp.validateLastSDDdetails(updatedSdd.getEndToEndId());

	}
}
