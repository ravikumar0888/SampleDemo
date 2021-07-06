package sctEventReport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.EventReportPage;
import spspages.LoginPage;
import testbase.BasePage;
import testdataobjects.EventReport;

public class EVREP_SCT_002_Create_EventReportTest extends BasePage {
	static Logger logger = LogManager.getLogger(EVREP_SCT_002_Create_EventReportTest.class.getName());

	LinkedTreeMap<String, ?> er = JsonManager.getMapfromJson("testdata/eventReport.json", "createEventReportSCT");
	EventReport errorreport = new EventReport(er);

	
	
	@Test(description = "")
	public void EVREP_SCT_002_Create_EventReport() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		EventReportPage erp = new EventReportPage();
		

		login.loginIntoSPS("eventreportsct");
	//	erp.selectLanguage();
		erp.selectMenu("File Supervision", "New Event Report");
		erp.createEventReport(errorreport,"SCT");
		
		
		
		
		
		
		
	}

}