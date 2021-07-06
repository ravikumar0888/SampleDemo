package sctEventReport;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import io.qameta.allure.Description;
import spspages.EventReportPage;
import spspages.LoginPage;
import spspages.MandatePage;
import testbase.BasePage;
import testdataobjects.Mandates;
import testdataobjects.SDD;

public class EVREP_SCT_003_Export_Empty_ReportTest extends BasePage {
	static Logger logger = LogManager.getLogger(EVREP_SCT_003_Export_Empty_ReportTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "EventReportMandate");
	Mandates createmandate = new Mandates(mandate);

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);
	
	@Description("To verify mandate creation in active status")
	@Test(description = "Test to check Mandate creation flow")

	public void EVREP_SCT_003_Export_Empty_Report() throws IOException, InterruptedException {
		FileManager fm= new FileManager();
		LoginPage login = new LoginPage();
		EventReportPage erp = new EventReportPage();
		MandatePage mp = new MandatePage();
			
		login.loginIntoSPS("eventreportsct");
		mp.selectMenu("File Supervision","Event Report Search");
		erp.eventReportGeneration();
		mp.selectMenu("File Supervision","Event Report Export Search");
		
		erp.downloadGenerateFile("SCTEventreport");
		
		String xmldata = fm.getLatestXmlDownload("xml");
		erp.validateBlanksdd(xmldata);
	}

}
