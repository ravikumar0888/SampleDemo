package mandateTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import io.qameta.allure.Description;
import spspages.LoginPage;
import spspages.MandatePage;
import spspages.MandateSearchPage;
import spspages.SDDPage;
import spspages.TranscationManagement;
import testbase.BasePage;
import testdataobjects.Mandates;
import testdataobjects.SDD;
import utilies.UserActions;

// create a 2 program due to too much steps 
public class MandateManagement462_Part1Test extends BasePage {
	static Logger logger = LogManager.getLogger(MandateManagement462_Part1Test.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "EditMandate");

	Mandates createmandate = new Mandates(mandate);
	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");

	SDD createsdd = new SDD(sdd1);

	public MandateManagement462_Part1Test() {
		super();
	}

	@Description("To verify mandate creation in active status")
	@Test(description = "Test to check Mandate creation flow")

	public void MandateManagement462_Part1() throws InterruptedException {
		LoginPage login = new LoginPage();
		MandatePage mp = new MandatePage();
		SDDPage sdd = new SDDPage();
		TranscationManagement tr = new TranscationManagement();
		MandateSearchPage msp = new MandateSearchPage();
		UserActions action = new UserActions();
		// step 1 to 6

		login.loginIntoSPS("default");

		mp.searchInvalidUMR(createmandate);
		mp.selectMenuMandate();
	mp.selectCreditorForMandateCreation(createmandate, "");
		mp.searchUMR(createmandate);
		mp.editMandateWithDebitorDetails(createmandate);

		// Step 7 to 12
		mp.validateMandatePage(createmandate);

		// Step 13 and 14
		mp.createMandatewithFutureDate(createmandate);

		/*// Step 15,16,17,18
		mp.mandateCreationWithUMR(createmandate);
		mp.searchUMR(createmandate);
		mp.editMandateWithDebitorDetails(createmandate);

		// Step 19,20,21
		mp.mandateCreationWithUIR(createmandate);
		mp.searchUMR(createmandate);
		mp.editMandateWithDebitorDetails(createmandate);

		// Step 22 and 23 Pending

		// setp 24,25
		mp.mandateUpdateWithNewData(createmandate);
		// step 26 27
		mp.modifiedUMR(createmandate);
		// step 28 29 30 31
		mp.createMandateAndSenttoDebitor(createmandate);

		// Step 32,33,34,35
		mp.createMandateAndSenttoDebitor(createmandate);
		mp.sentToDebitorTOActive(createmandate);

		// Step 36
		msp.btnSDDCreate();
		SDD updatedSdd = sdd.createSDD(createsdd);
		tr.sddCollectionAndPainFileGeneration();
		tr.searchTranscationAndValidateStatus(updatedSdd.getEndToEndId(), "Issued");

		
		 * mp.searchUMR(createmandate); String endid2 =sdd.createSDD(createsdd);
		 * tr.searchTranscationAndValidateStatus(endid2,"Created");
		 

		// Step36 and 37 - 41 as of now skip bez it need db access to update

		action.selectMenu("Mandates Management", "New Mandate");

		mp.createActiveMandateWithAttachement(createmandate);*/

	}

}

//Todo file upload and data validation with PDf 