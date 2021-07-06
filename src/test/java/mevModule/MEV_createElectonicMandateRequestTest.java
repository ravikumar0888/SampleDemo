package mevModule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.CreditorPage;
import spspages.ElectronicValidationPage;
import spspages.LoginPage;
import spspages.RootPage;
import spspages.UsersSearch;
import testbase.BasePage;
import testdataobjects.Creditor;
import testdataobjects.Root;
import utilies.UserActions;

public class MEV_createElectonicMandateRequestTest extends BasePage {
	static Logger logger = LogManager.getLogger(MEV_createElectonicMandateRequestTest.class.getName());

	LinkedTreeMap<String, ?> root = JsonManager.getMapfromJson("testdata/root.json", "CreateRoot");
	Root createRoot = new Root(root);



	@Test(description = "")
	public void minimumOrganisationCreation01() {
		UserActions action = new UserActions();
		UsersSearch user = new UsersSearch();
		LoginPage login = new LoginPage();
		RootPage rp= new RootPage();
		ElectronicValidationPage evp= new ElectronicValidationPage();
// Step 1
		/*login.loginIntoSPS("admin");
		user.selectLanguage();
		
		action.selectMenu("User management", "User Search");

		user.userSearch("mevUser01");
		user.clickEditForUser("mevUser01");
		
		
		user.clickEditGroupList();
		user.validateChkBoxElectronicMandate();
		login.logout();
	*/
		
	// Step 2 not automatate bez already coverd in step1 
		login.loginIntoSPS("mev");
		action.selectOragnization();
		rp.selectorgandtemplete();
		rp.createNotificationTemplete("Mandate's Electronic Validation");
		
		rp.createNotificationTemplete("Notification Mandate Creation(on demand)");
		
		//step 4 
	//	action.selectOragnization();
		rp.addSignatureId();
		
		//step5 
		rp.selectMenu("Electronic Validation Requests Management", "New Request");
		
		evp.createElectronicrequest();
		
	}

}