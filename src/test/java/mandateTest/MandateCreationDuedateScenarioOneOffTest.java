package mandateTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

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

/*https://jira.worldline.com/browse/FPLSPSTEST-465 step 9 to 17
*/
public class MandateCreationDuedateScenarioOneOffTest extends BasePage {
	static Logger logger = LogManager.getLogger(MandateCreationDuedateScenarioOneOffTest.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "OnlyMandate");
	Mandates createmandate = new Mandates(mandate);
	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	@Description("To verify mandate creation in active status")
	@Test(description = "Test to check Mandate creation flow",enabled= false)

	public void MandateCreationDuedateScenarioOneOff() throws InterruptedException {
		LoginPage login = new LoginPage();
		MandatePage mp = new MandatePage();
		UserActions action = new UserActions();
		SDDPage sdd = new SDDPage();
		TranscationManagement tr = new TranscationManagement();

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

		login.logout();

	}
}
