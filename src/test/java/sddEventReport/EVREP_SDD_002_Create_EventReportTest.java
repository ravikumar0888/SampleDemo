 package sddEventReport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.EventReportPage;
import spspages.LoginPage;
import testbase.BasePage;
import testdataobjects.EventReport;

public class EVREP_SDD_002_Create_EventReportTest extends BasePage {
	static Logger logger = LogManager.getLogger(EVREP_SDD_002_Create_EventReportTest.class.getName());

	LinkedTreeMap<String, ?> er = JsonManager.getMapfromJson("testdata/eventReport.json", "createEventReport");
	EventReport errorreport = new EventReport(er);

	
	
	@Test(description = "")
	public void EVREP_SDD_002_Create_EventReport() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		EventReportPage erp = new EventReportPage();
		

		login.loginIntoSPS("eventreport");
	//	erp.selectLanguage();
		erp.selectMenu("File Supervision", "New Event Report");
		erp.createEventReport(errorreport,"SDD");
	
	}

}