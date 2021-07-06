package mandateTest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.FileManager;
import datamanagement.JsonManager;
import io.qameta.allure.Description;
import spspages.LoginPage;
import spspages.MandatePage;
import spspages.SDDPage;
import spspages.TranscationManagement;
import testbase.BasePage;
import testdataobjects.Mandates;
import testdataobjects.SDD;
import utilies.UserActions;

/*https://jira.worldline.com/browse/FPLSPSTEST-466
*/
public class MandateCreationAmendmentsChangeBICTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateCreationAmendmentsChangeBICTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateRCRR");
	Mandates createmandate = new Mandates(mandate);
	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	@Description("To verify mandate creation in active status")
	@Test(description = "Test to check Mandate creation flow",enabled= false)

	public void MandateCreationAmendmentsChangeBIC() throws InterruptedException, IOException {
		LoginPage login = new LoginPage();
		MandatePage mp = new MandatePage();
		UserActions action = new UserActions();
		SDDPage sdd = new SDDPage();
		TranscationManagement tr = new TranscationManagement();
		FileManager fm = new FileManager();

		login.loginIntoSPS("mandateuser");

		action.selectMenu("Mandates Management", "New Mandate");
		mp.selectCreditorForMandateCreation(createmandate, "mandate");

		mp.searchUMR(createmandate);
		mp.editMandateWithSendForValidatation(createmandate);

		login.logout();

		login.loginIntoSPS("default");
		mp.selectMandateMangement();

		mp.searchUMR(createmandate);
		mp.validateMandate();
		login.logout();

		login.loginIntoSPS("mandateuser");
		mp.selectMandateMangement();

		mp.searchUMR(createmandate);

		sdd.clickOnCreateSDD();
		SDD updatedSdd = sdd.createSDD(createsdd);
		tr.searchTranscationAndValidateSequenceType(updatedSdd.getEndToEndId(), "First");

		mp.searchUMR(createmandate);
		sdd.clickOnCreateSDD();
		SDD updatedSdd2 = sdd.createSDD(createsdd);
		tr.searchTranscationAndValidateSequenceType(updatedSdd2.getEndToEndId(), "Recurrent");

		tr.sddCollectionAndPainFileGeneration();
		tr.searchTranscationAndValidateStatus(updatedSdd.getEndToEndId(), "Issued");
		tr.searchTranscationAndValidateStatus(updatedSdd2.getEndToEndId(), "Issued");

		mp.selectMandateMangement();

		mp.searchUMR(createmandate);
		Mandates mandate = mp.updateIBAN(createmandate);
		login.logout();

		login.loginIntoSPS("default");
		mp.selectMandateMangement();

		mp.searchUMR(createmandate);
		mp.validateMandate();
		login.logout();

		login.loginIntoSPS("mandateuser");
		mp.selectMandateMangement();

		mp.searchUMR(createmandate);

		sdd.clickOnCreateSDD();
		SDD updatedSdd3 = sdd.createSDD(createsdd);
		tr.searchTranscationAndValidateSequenceType(updatedSdd3.getEndToEndId(), "First");
		tr.sddCollectionAndPainFileGeneration();
		tr.searchTranscationAndValidateStatus(updatedSdd3.getEndToEndId(), "Issued");

		tr.searchCollectionFilePain008(updatedSdd3.getEndToEndId());

		String xmldata = fm.getLatestXmlDownload("xml");

		System.out.println("Content of file is  :" + xmldata);

		tr.validatePain008File(xmldata, mandate.getUpdateIBAN());

	}
}
