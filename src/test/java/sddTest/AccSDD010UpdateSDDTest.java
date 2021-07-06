package sddTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.LoginPage;
import spspages.SDDPage;
import spspages.TranscationManagement;
import testbase.BasePage;
import testdataobjects.MandatesSearch;
import testdataobjects.SDD;

public class AccSDD010UpdateSDDTest extends BasePage {
	static Logger logger = LogManager.getLogger(AccSDD010UpdateSDDTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");
	MandatesSearch searchMandate = new MandatesSearch(mandate);

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	@Test
	public void accSDD010UpdateSDDTest() {

		TranscationManagement tr = new TranscationManagement();
		LoginPage login = new LoginPage();
		SDDPage sdd = new SDDPage();

		login.loginIntoSPS("default");

		tr.searchTranscationUsingStatus("Issued", "Issued");
		sdd.verifySDDStatusAfterIssueStatus();

		tr.searchTranscationUsingStatus("Acknowledged by the bank", "Acknowledged by the bank");
		sdd.verifySDDStatusAfterAcknoledgeByBankStatus();

		tr.searchTranscationUsingStatus("Settled", "Settled");
		sdd.verifySDDStatusAfterAcknoledgeByBankStatus(); // same dropdown values so reuse. 
	}
}
