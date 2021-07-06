package sddTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import sanitySPSTest.MinimumSDDCreationviaGUI03Test;
import spspages.CommonSectionPage;
import spspages.LoginPage;
import spspages.MandateSearchPage;
import spspages.SDDPage;
import testbase.BasePage;
import testdataobjects.MandatesSearch;
import testdataobjects.SDD;

public class AccSDD003CreateSDDTest extends BasePage {
	static Logger logger = LogManager.getLogger(MinimumSDDCreationviaGUI03Test.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");
	MandatesSearch searchMandate = new MandatesSearch(mandate);

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	@Test
	public void accSDD003CreateSDDTest() throws InterruptedException {
CommonSectionPage cs = new CommonSectionPage();
		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();
		SDDPage sdd = new SDDPage();

		login.loginIntoSPS("default");
		cs.selectMandateMangement();
		ms.mandateSearchWithActiveStatus(searchMandate);
		sdd.createSDD(createsdd);

	}
}