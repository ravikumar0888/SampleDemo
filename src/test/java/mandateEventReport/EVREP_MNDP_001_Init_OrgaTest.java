package mandateEventReport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.google.gson.internal.LinkedTreeMap;

import datamanagement.JsonManager;
import spspages.CreditorPage;
import spspages.LoginPage;
import spspages.NodeRootPage;
import spspages.RootPage;
import testbase.BasePage;
import testdataobjects.Creditor;
import testdataobjects.Node;
import testdataobjects.Root;

public class EVREP_MNDP_001_Init_OrgaTest extends BasePage {
	static Logger logger = LogManager.getLogger(EVREP_MNDP_001_Init_OrgaTest.class.getName());

	LinkedTreeMap<String, ?> root = JsonManager.getMapfromJson("testdata/root.json", "CreateRoot");
	Root createRoot = new Root(root);

	LinkedTreeMap<String, ?> creditor = JsonManager.getMapfromJson("testdata/creditors.json", "Creditor_One");
	Creditor creditor1 = new Creditor(creditor);

	LinkedTreeMap<String, ?> creditor2 = JsonManager.getMapfromJson("testdata/creditors.json", "Creditor_Two");
	Creditor creditor_2 = new Creditor(creditor2);

	LinkedTreeMap<String, ?> createnode = JsonManager.getMapfromJson("testdata/node.json", "createNode");
	Node node = new Node(createnode);
	
	@Test(description = "")
	public void EVREP_SDD_001_Init_Orga() {
		
		LoginPage login = new LoginPage();
		CreditorPage cr = new CreditorPage();
		RootPage rp = new RootPage();
		NodeRootPage nrp= new NodeRootPage();

		login.loginIntoSPS("default");
		cr.selectMenu("Creditors Management", "New Creditor");
		String cr1 = cr.createCreditor(creditor1);
		cr.searchCreditor(cr1);
		cr.addingAddressOfCreditor(creditor1);
		cr.selectNewCreditor();
		String cr2 = cr.createCreditor(creditor_2);
		cr.searchCreditor(cr2);
		
		// Create Root
		rp.selectMenu("Organizations Management", "Create Root");

		rp.createRoot(createRoot);

		rp.addSCI(cr1);
		rp.addSCI(cr2);
		rp.addBusinessCode(createRoot);
		rp.addBankAccount(createRoot);
		rp.emailSubmit(createRoot);
		// create node 
		nrp.backtoOrgSearch();
		nrp.createSubEntityNode(node);
		nrp.addSCItoNode();
		nrp.addBusinessCodeToNode();
		nrp.addAccountToNode();
		
		
		nrp.backtoOrgSearch();
		nrp.createSubEntityNode(node);
		nrp.addSCItoNode();
		nrp.addBusinessCodeToNode();
		nrp.addAccountToNode();
		
	}

}