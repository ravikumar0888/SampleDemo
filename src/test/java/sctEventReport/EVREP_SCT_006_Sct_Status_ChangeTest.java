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
import spspages.SCTPage;
import spspages.TranscationManagement;
import testbase.BasePage;
import testdataobjects.SCT;

public class EVREP_SCT_006_Sct_Status_ChangeTest extends BasePage {
	static Logger logger = LogManager.getLogger(EVREP_SCT_006_Sct_Status_ChangeTest.class.getName());

	
	LinkedTreeMap<String, ?> sct = JsonManager.getMapfromJson("testdata/createsct.json", "CreateSCT");
	SCT createsct = new SCT(sct);
	
	@Description("To verify mandate creation in active status")
	@Test(description = "Test to check Mandate creation flow")

	public void EVREP_SCT_006_Sct_Status_Change() throws IOException, InterruptedException {
		FileManager fm= new FileManager();
		LoginPage login = new LoginPage();
		EventReportPage erp = new EventReportPage();
		MandatePage mp = new MandatePage();
		SCTPage sct = new SCTPage();
		TranscationManagement tr = new TranscationManagement();
		
		login.loginIntoSPS("eventreportsct");
		mp.selectMenu("Transactions Management", "SCT creation");
		SCT updatedSCT =sct.createSCT(createsct);
		tr.searchSCTTranscation(updatedSCT.getEndToEndId());

		sct.cancelSCTwithComments();
		
		
		mp.selectMenu("File Supervision","Event Report Search");
		erp.eventReportGeneration();
		mp.selectMenu("File Supervision","Event Report Export Search");
		
		erp.downloadGenerateFile("SCTEventreport");
		
		String xmldata = fm.getLatestXmlDownload("xml");
		erp.validatesct(updatedSCT,xmldata);
	}

}
