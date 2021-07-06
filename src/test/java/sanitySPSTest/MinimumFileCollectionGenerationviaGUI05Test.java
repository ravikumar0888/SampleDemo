package sanitySPSTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.CommonSectionPage;
import spspages.LoginPage;
import spspages.MandateSearchPage;
import spspages.RootPage;
import spspages.SDDPage;
import spspages.TranscationManagement;
import testbase.BasePage;
import testdataobjects.MandatesSearch;
import testdataobjects.SDD;

public class MinimumFileCollectionGenerationviaGUI05Test extends BasePage {
	static Logger logger = LogManager.getLogger(MinimumFileCollectionGenerationviaGUI05Test.class.getName());

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");

	MandatesSearch searchMandate = new MandatesSearch(mandate);
	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");

	SDD createsdd = new SDD(sdd1);
	public MinimumFileCollectionGenerationviaGUI05Test() {
		super();
	}

	@Test
	public void minimumFileCollectionGenerationviaGUI05() throws InterruptedException {

		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();
		SDDPage sdd = new SDDPage();
		TranscationManagement tr = new TranscationManagement();
		RootPage rp = new RootPage();
		CommonSectionPage cs = new CommonSectionPage();

		login.loginIntoSPS("default");

		rp.sendDeliverySheetByEmail();
cs.selectMandateMangement();
		ms.mandateSearchWithActiveStatus(searchMandate);
		SDD updatedSdd =sdd.createSDD(createsdd);
		tr.sddCollectionAndPainFileGeneration();
		tr.searchTranscationAndValidateStatus(updatedSdd.getEndToEndId(), "Issued");

		// remaing few steps are releated to email
	}

}
