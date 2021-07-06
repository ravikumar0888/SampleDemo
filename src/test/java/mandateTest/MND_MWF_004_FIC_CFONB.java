package mandateTest;

import org.apache.commons.lang3.StringUtils;
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

public class MND_MWF_004_FIC_CFONB extends BasePage {
	static Logger logger = LogManager.getLogger(MND_MWF_004_FIC_CFONB.class.getName());

	LinkedTreeMap<String, ?> mandateCreationData = JsonManager.getMapfromJson("testdata/fileSupervision.json",
			"MND_MWF_004_FIC_CFONB");

	FileSupervision fsUploadCFONBTestData = new FileSupervision(mandateCreationData);
	
	@Description("TC FPLSPSTEST-438 MND_MWF_004_FIC_CFONB")
	@Test(description = "TC FPLSPSTEST-438 MND_MWF_004_FIC_CFONB")

	public void mnd_mwf_004_fic_CFONB() throws InterruptedException {

		LoginPage login = new LoginPage();
		GenericFunctions gf = new GenericFunctions();
		UserActions action = new UserActions();
		FileSupervisionPage fs = new FileSupervisionPage();
		SDDPage sdd = new SDDPage();

		MandateSearchPage msp = new MandateSearchPage();
		String id = gf.randamGenerator();
		String filledId=  StringUtils.leftPad(id, 11, "0");

		String createMandateXML = FileManager.readFile("fileupload/MND_MWF_004_FIC_CFONB.txt");
		createMandateXML = createMandateXML.replaceAll("replace_id", filledId);
		String createMandatefilepath = FileManager.writetempfile(createMandateXML, "createSDDCFONB", "txt");
		logger.info("The Create Mandate xml file is {}", createMandatefilepath);

		login.loginIntoSPS("default");
		action.selectMenu("File Supervision", "Upload File");
		String createMfilename = fs.uploadCSVForMandateCreation(fsUploadCFONBTestData, createMandatefilepath);
		fs.searchFileAndValidate(createMfilename, fsUploadCFONBTestData);

	
	}

}
