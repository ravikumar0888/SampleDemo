package sanitySPSTest;

import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.CommonSectionPage;
import spspages.LoginPage;
import spspages.MandateSearchPage;
import spspages.SDDPage;
import spspages.TranscationManagement;
import testbase.BasePage;
import testdataobjects.MandatesSearch;
import testdataobjects.SDD;

public class MinimumCollectionGenerationvia04GUITest extends BasePage {

	LinkedTreeMap<String, ?> mandate = JsonManager.getMapfromJson("testdata/mandates.json", "MandateSerch");
	MandatesSearch searchMandate = new MandatesSearch(mandate);

	LinkedTreeMap<String, ?> sdd1 = JsonManager.getMapfromJson("testdata/createsdd.json", "CreateSDD");
	SDD createsdd = new SDD(sdd1);

	@Test
	public void minimumCollectionGenerationvia04GUI() throws InterruptedException {

		CommonSectionPage cs = new CommonSectionPage();

		LoginPage login = new LoginPage();
		MandateSearchPage ms = new MandateSearchPage();
		SDDPage sdd = new SDDPage();
		TranscationManagement tr = new TranscationManagement();

		login.loginIntoSPS("default");
		cs.selectMandateMangement();
		ms.mandateSearchWithActiveStatus(searchMandate);
		
		SDD updatedSdd =sdd.createSDD(createsdd);
		tr.sddCollectionGeneration();
		tr.searchTranscationAndValidateStatus(updatedSdd.getEndToEndId(), "Generated");
	}

}
