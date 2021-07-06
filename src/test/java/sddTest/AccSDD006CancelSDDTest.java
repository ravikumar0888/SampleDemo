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
import testdataobjects.Mandates;
import testdataobjects.MandatesSearch;
import testdataobjects.SDD;

public class AccSDD006CancelSDDTest extends BasePage {
	static Logger logger = LogManager.getLogger(AccSDD006CancelSDDTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");
	MandatesSearch searchMandate = new MandatesSearch(mandate);

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	LinkedTreeMap<String, ?> mandate2 = JsonManager.getMapfromJson("testdata/mandates.json", "EditMandate");
	Mandates createmandate = new Mandates(mandate2);

	@Test
	public void accSDD006CancelSDDTest() throws InterruptedException {
		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();
		SDDPage sdd = new SDDPage();
		TranscationManagement tr = new TranscationManagement();
		CommonSectionPage cs = new CommonSectionPage();

		login.loginIntoSPS("default");
		cs.selectMandateMangement();
		ms.mandateSearchWithActiveStatus(searchMandate);
		SDD updatedSdd =sdd.createSDD(createsdd);
		
		tr.searchTranscationAndValidateStatus(updatedSdd.getEndToEndId(), "Created");
		sdd.cancelSDDwithCheckNO();
		sdd.cancelSDDwithComments(); // till 9 step covered

		cs.selectMandateMangement();
		ms.mandateSearchWithActiveStatus(searchMandate);
		SDD updatedSdd1 =sdd.createSDD(createsdd);
	
		tr.searchTranscationAndValidateStatus(updatedSdd1.getEndToEndId(), "Created");
		tr.sddCollectionGeneration();
		tr.searchCollectionFile(updatedSdd1.getEndToEndId());

		tr.searchTranscationAndValidateStatus(updatedSdd1.getEndToEndId(), "Created"); // till step 16

		/*
		 * mp.searchUMR(createmandate); mp.validateLastSDDdetails(endId);
		 */

	}
}
