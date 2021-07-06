package sanitySPSTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import spspages.FileSupervisionPage;
import spspages.LoginPage;
import testbase.BasePage;
import testdataobjects.FileSupervision;
import utilies.GenericFunctions;
import utilies.UserActions;

public class MinimumImportMandateFIleCSV007Test extends BasePage {
	static Logger logger = LogManager.getLogger(MinimumImportMandateFIleCSV007Test.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/fileSupervision.json", "CSVMandate");

	FileSupervision csv = new FileSupervision(mandate);
	public MinimumImportMandateFIleCSV007Test() {
		super();
	}

	@Test
	public void minimumImportMandateFIleCSV007() throws InterruptedException {
		LoginPage login = new LoginPage();

		UserActions action = new UserActions();

		GenericFunctions gf = new GenericFunctions();
		FileSupervisionPage fs = new FileSupervisionPage();
		// orga = zTestRaviM
		String fileContent = FileManager.readFile("fileupload/mandate.csv");
		String id = gf.randamGenerator();
		fileContent = fileContent.replaceAll("unique", id).replaceAll("NNE_to_replace", "67890").replaceAll("ICS_to_replace", "FR65ZZZ222522").replaceAll("MxMANDAT", id + "abc");

		String filepath = FileManager.writetempfile(fileContent, "mandate", "csv");
		logger.info("the file is {}", filepath);

		login.loginIntoSPS("default");
		action.selectMenu("File Supervision", "Upload File");
		String filename = fs.uploadCSVForMandateCreation(csv, filepath);
		fs.searchFileAndValidate(filename,csv);

	}

}
