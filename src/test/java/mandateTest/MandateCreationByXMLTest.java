package mandateTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import io.qameta.allure.Description;
import spspages.FileSupervisionPage;
import spspages.LoginPage;
import testbase.BasePage;
import testdataobjects.FileSupervision;
import utilies.GenericFunctions;
import utilies.UserActions;

public class MandateCreationByXMLTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateCreationByXMLTest.class.getName());

	LinkedTreeMap<String, ?> mandateCreationData = JsonManager.getMapfromJson("testdata/fileSupervision.json",
			"XMLMandateCreate");

	FileSupervision fsCreateMandateTestData = new FileSupervision(mandateCreationData);

	LinkedTreeMap<String, ?> mandateUpdateData = JsonManager.getMapfromJson("testdata/fileSupervision.json",
			"XMLMandateUpdate");
	FileSupervision fSUpdateMandateTestData = new FileSupervision(mandateUpdateData);

	@Description("To verify mandate creation by xml file upload")
	@Test(description = "To verify mandate creation by xml file upload")

	public void mandateCreationByXML() throws InterruptedException {
		LoginPage login = new LoginPage();
		GenericFunctions gf = new GenericFunctions();
		UserActions action = new UserActions();
		FileSupervisionPage fs = new FileSupervisionPage();

		String id = gf.randamGenerator();

		String createMandateXML = FileManager.readFile("fileupload/createMandate.xml");
		createMandateXML = createMandateXML.replaceAll("replace_id", id).replaceAll("AAMFA10000", id);
		String createMandatefilepath = FileManager.writetempfile(createMandateXML, "createmandate", "xml");
		logger.info("The Create Mandate xml file is {}", createMandatefilepath);

		login.loginIntoSPS("default");
		action.selectMenu("File Supervision", "Upload File");
		String createMfilename = fs.uploadCSVForMandateCreation(fsCreateMandateTestData, createMandatefilepath);
		fs.searchFileAndValidate(createMfilename, fsCreateMandateTestData);

		String updateMandateXML = FileManager.readFile("fileupload/updateMandate.xml");
		updateMandateXML = updateMandateXML.replaceAll("replace_id", id).replaceAll("AAMFA10000", id);
		String updateMandatefilepath = FileManager.writetempfile(updateMandateXML, "updatemandate", "xml");
		logger.info("The Update Mandate xml file is {}", updateMandatefilepath);

		action.selectMenu("File Supervision", "Upload File");
		String updateMfilename = fs.uploadCSVForMandateCreation(fSUpdateMandateTestData, updateMandatefilepath);
		fs.searchFileAndValidate(updateMfilename, fSUpdateMandateTestData);
	}

}
