package sanitySPSTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import datamanagement.PdfManager;
import io.qameta.allure.Description;
import spspages.LoginPage;
import spspages.MandatePage;
import testbase.BasePage;
import testdataobjects.Mandates;
import utilies.UserActions;

public class MinimumMandateCreationviaGUI02Test extends BasePage {
	static Logger logger = LogManager.getLogger(MinimumMandateCreationviaGUI02Test.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "EditMandate");
	Mandates createmandate = new Mandates(mandate);

	@Description("To verify mandate creation in active status")
	@Test(description = "Test to check Mandate creation flow")

	public void minimumMandateCreationviaGUI02() throws IOException, InterruptedException {
		String testcaseName = this.getClass().getSimpleName();

		UserActions action = new UserActions();
		PdfManager pdf = new PdfManager();
		LoginPage login = new LoginPage();

		MandatePage mp = new MandatePage();

		login.loginIntoSPS("default");
		action.selectMenu("Mandates Management", "New Mandate");

		mp.selectCreditorForMandateCreation(createmandate,"");
		mp.searchUMR(createmandate);
		mp.generatePDF();
		/*
		 * // mp.editMandateWithDebitorDetails(createmandate); File file; try { file = pdf.getFileNameAndRename(testcaseName); pdf.verifyPDFContent(file, "FR24ZZZ1236541222"); pdf.verifyPDFContent(file, "TESTRAM"); } catch (URISyntaxException e) { e.printStackTrace(); }
		 */

	}

}
