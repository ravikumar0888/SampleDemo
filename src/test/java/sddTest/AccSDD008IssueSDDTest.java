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

public class AccSDD008IssueSDDTest extends BasePage {
	static Logger logger = LogManager.getLogger(AccSDD008IssueSDDTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");
	MandatesSearch searchMandate = new MandatesSearch(mandate);

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	@Test
	public void accSDD008IssueSDD() throws InterruptedException {
		TranscationManagement tr = new TranscationManagement();
		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();
		SDDPage sdd = new SDDPage();
		CommonSectionPage cs = new CommonSectionPage();

		login.loginIntoSPS("default");
		cs.selectMandateMangement();

		ms.mandateSearchWithActiveStatus(searchMandate);
		SDD updatedSdd =sdd.createSDD(createsdd);
		tr.searchTranscationAndValidateStatus(updatedSdd.getEndToEndId(), "Created");
		tr.sddCollectionAndPainFileGeneration();
		tr.searchTranscationAndValidateStatus(updatedSdd.getEndToEndId(), "Issued");

		cs.selectMandateMangement();

		ms.mandateSearchWithActiveStatus(searchMandate);
		SDD updatedSdd1 =sdd.createSDD(createsdd);
		tr.searchTranscationAndValidateStatus(updatedSdd1.getEndToEndId(), "Created");
	}
}
