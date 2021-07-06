package sddTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.CommonSectionPage;
import spspages.LoginPage;
import spspages.MandateSearchPage;
import testbase.BasePage;
import testdataobjects.MandatesSearch;
import testdataobjects.SDD;

public class AccSDD011ScheduleTest extends BasePage {
	static Logger logger = LogManager.getLogger(AccSDD011ScheduleTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");
	MandatesSearch searchMandate = new MandatesSearch(mandate);

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	@Test
	public void accSDD011ScheduleTest() throws InterruptedException {
		CommonSectionPage cs = new CommonSectionPage();

		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();
		// login.loginIntoSPS();
		login.loginIntoSPS("default");
		cs.selectMandateMangement();
		ms.searchActiveMandateandPaymentSchedule(searchMandate);
		ms.deletePaymentScheule();

	}
}