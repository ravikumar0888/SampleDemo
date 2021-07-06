package sanitySPSTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.CreditorPage;
import spspages.LoginPage;
import spspages.RootPage;
import testbase.BasePage;
import testdataobjects.Creditor;
import testdataobjects.Root;
import utilies.UserActions;

public class MinimumOrganisationCreation01Test extends BasePage {
	static Logger logger = LogManager.getLogger(MinimumOrganisationCreation01Test.class.getName());

	LinkedTreeMap<String, ?> root = JsonManager.getMapfromJson("testdata/root.json", "CreateRoot");
	Root createRoot = new Root(root);

	LinkedTreeMap<String, ?> creditor = JsonManager.getMapfromJson("testdata/creditors.json", "Creditor_One");
	Creditor creditor1 = new Creditor(creditor);

	LinkedTreeMap<String, ?> creditor2 = JsonManager.getMapfromJson("testdata/creditors.json", "Creditor_Two");
	Creditor creditor_2 = new Creditor(creditor2);

	@Test(description = "")
	public void minimumOrganisationCreation01() {
		UserActions action = new UserActions();

		LoginPage login = new LoginPage();
		CreditorPage cr = new CreditorPage();
		RootPage rp = new RootPage();

		login.loginIntoSPS("default");
		action.selectMenu("Creditors Management", "New Creditor");
		String CR1 = cr.createCreditor(creditor1);
		cr.selectNewCreditor();
		String CR2 = cr.createCreditor(creditor_2);
		// Create Root
		action.selectMenu("Organizations Management", "Create Root");

		createRoot = rp.createRoot(createRoot);

		rp.addSCI(CR1);
		rp.addSCI(CR2);
		rp.addBusinessCode(createRoot);
		rp.addBankAccount(createRoot);
		rp.emailSubmit(createRoot);
		
		// Delete Creditior FPLSPSTEST-3635
	//	action.selectMenu("Creditors Management", "");
	//	cr.deleteCreditor(CR1);
	//	cr.deleteCreditor(CR2);
		
		

	}

}