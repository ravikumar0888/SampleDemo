package sddEventReport;

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
import spspages.SDDPage;
import spspages.TranscationManagement;
import testbase.BasePage;
import testdataobjects.Mandates;
import testdataobjects.SDD;

public class EVREP_SDD_006_Sdd_Status_ChangeTest extends BasePage {
	static Logger logger = LogManager.getLogger(EVREP_SDD_006_Sdd_Status_ChangeTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "EventReportMandate");
	Mandates createmandate = new Mandates(mandate);

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);
	
	@Description("To verify mandate creation in active status")
	@Test(description = "Test to check Mandate creation flow")

	public void EVREP_SDD_006_Sdd_Status_Change() throws IOException, InterruptedException {
		FileManager fm= new FileManager();
		LoginPage login = new LoginPage();
		EventReportPage erp = new EventReportPage();
		MandatePage mp = new MandatePage();
		SDDPage sdd = new SDDPage();
		TranscationManagement tr = new TranscationManagement();
		
		login.loginIntoSPS("eventreport");
	//	erp.selectLanguage();
		mp.selectMenu("Mandates Management", "New Mandate");

		mp.createActiveMandateEventReport(createmandate);
		sdd.clickOnCreateSDD();
		SDD updatedSdd =sdd.createSDD(createsdd);
		
		tr.searchTranscationAndValidateStatus(updatedSdd.getEndToEndId(), "Created");
		sdd.clickOnSDDDetails();
		sdd.cancelSDDwithComments();
	
	
		mp.selectMenu("File Supervision","Event Report Search");
		erp.eventReportGeneration();
		mp.selectMenu("File Supervision","Event Report Export Search");
		
		erp.downloadGenerateFile("TesteventreportAutomation");
		
		String xmldata = fm.getLatestXmlDownload("xml");
		erp.validatesdd(updatedSdd,xmldata);
		
		String path = fm.getLatestXmltoDelete("zip");
		fm.deleteDownloadedFile(path);
		
		String xmlpath = fm.getLatestXmltoDelete("xml");
		fm.deleteDownloadedFile(xmlpath);
	}

}
