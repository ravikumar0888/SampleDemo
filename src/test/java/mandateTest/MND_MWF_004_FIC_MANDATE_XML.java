package mandateTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import io.qameta.allure.Description;
import spspages.FileSupervisionPage;
import spspages.LoginPage;
import spspages.MandateSearchPage;
import spspages.SDDPage;
import testbase.BasePage;
import testdataobjects.FileSupervision;
import testdataobjects.SDD;
import utilies.GenericFunctions;
import utilies.UserActions;

public class MND_MWF_004_FIC_MANDATE_XML extends BasePage {
	static Logger logger = LogManager.getLogger(MND_MWF_004_FIC_MANDATE_XML.class.getName());

	LinkedTreeMap<String, ?> mandateCreationData = JsonManager.getMapfromJson("testdata/fileSupervision.json",
			"MND_MWF_004_FIC_MANDATE_XML");

	FileSupervision fsCreateMandateTestData = new FileSupervision(mandateCreationData);
	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	@Description("TC FPLSPSTEST-438 MND_MWF_004_FIC_MANDATE_XML")
	@Test(description = "TC FPLSPSTEST-438 MND_MWF_004_FIC_MANDATE_XML")

	public void Mnd_mwf_004_fic_Mandate_Xml() throws InterruptedException {

		LoginPage login = new LoginPage();
		GenericFunctions gf = new GenericFunctions();
		UserActions action = new UserActions();
		FileSupervisionPage fs = new FileSupervisionPage();
		SDDPage sdd = new SDDPage();

		MandateSearchPage msp = new MandateSearchPage();
		String id = gf.randamGenerator();

		String createMandateXML = FileManager.readFile("fileupload/mandatecreationwithoutfirst.xml");
		createMandateXML = createMandateXML.replaceAll("replace_id", id);
		String createMandatefilepath = FileManager.writetempfile(createMandateXML, "createmandate", "xml");
		logger.info("The Create Mandate xml file is {}", createMandatefilepath);

		login.loginIntoSPS("default");
		action.selectMenu("File Supervision", "Upload File");
		String createMfilename = fs.uploadCSVForMandateCreation(fsCreateMandateTestData, createMandatefilepath);
		fs.searchFileAndValidate(createMfilename, fsCreateMandateTestData);
		
		action.selectMenu("Mandates Management", "Mandate Search");
		msp.searchMandate(fsCreateMandateTestData.getCreditorEntity(),id);
		sdd.createSDD(createsdd);
		SDD updatedSdd = sdd.enrichSddObject(createsdd);
		Assert.assertEquals(updatedSdd.getSequenceType(), "Recurring");
	}

}
