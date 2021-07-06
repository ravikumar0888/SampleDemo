package spspages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import testbase.BasePage;
import testdataobjects.Node;
import utilies.GenericFunctions;
import utilies.UserActions;

public class NodeRootPage extends BasePage {
	static Logger logger = LogManager.getLogger(NodeRootPage.class.getName());
	GenericFunctions gf = new GenericFunctions();
	UserActions action = new UserActions();
	WebDriver driver = getDriver();

	By btnBackToOrag = By.xpath(".//*[@id='btbartop']/div/a[contains(text(),'Back to Organizations Management')]");
	By btnCreditorSubEntity = By.xpath(".//*[@id='btbartop']/div[2]/a[contains(text(),'Create sub-entity')]");

	public void backtoOrgSearch()
	{
		action.clickLink(btnBackToOrag);
	}

	By txtnodeName = By.id("label");

	By txtSteetNumber = By.xpath(".//*[@id='addresseditor-addressNumber']");
	By txtSteetName = By.xpath(".//*[@id='addresseditor-addressStreet']");
	By txtPostalCode = By.xpath(".//*[@id='addresseditor-addressPostCode']");
	By txtCity = By.xpath(".//*[@id='addresseditor-addressCity']");

	By ddCountry = By.xpath(".//*[@id='addresseditor-addressCountry']");
	By btnSubmit = By.name("submit");

	public Node createSubEntityNode(Node createnode)
	{
		action.clickButton(btnCreditorSubEntity);
		action.waitForPageToLoad();

		action.getCurrentDateTime();
		String childNode = "ChildNode_" + action.getCurrentDateTime();

		action.setValueInTextField(txtnodeName, childNode);
		action.setValueInTextField(txtSteetNumber,createnode.getStreetNumber());
		action.setValueInTextField(txtSteetName,createnode.getStreetName());
		action.setValueInTextField(txtPostalCode, createnode.getPostalCode());
		action.setValueInTextField(txtCity, createnode.getCity());
		action.selectvaluesdropdown(ddCountry,createnode.getCountryDropdown());

		action.clickButton(btnSubmit);
		return createnode;
	}
	// ADD SCI
	By sciList = By.xpath("//*[@id='sciList-avail']/option[1]");
	By leftArrow = By.xpath("//*[@id='sciList-select']");
	By btnSubmitSCI = By.xpath(".//*[@id='sciForm']/div[3]/input[1]");

	public void addSCItoNode() 
	{
		driver.navigate().refresh();
		action.waitForElementVisible(driver, sciList);
		action.clickLink(sciList);
		//action.selectvaluefromMultipleDropdown(sciList, value);
		action.clickLink(leftArrow);
		action.clickLink(btnSubmitSCI);
	}

	By businessList  = By.xpath(".//*[@id='businessCodeList-avail']/option[1]");
	By leftArrowBusniess = By.xpath(".//*[@id='businessCodeList-select']");
	By btnSubmitBusiness = By.xpath(".//*[@id='businessCodeForm']/div[3]/input[1]");

	public void addBusinessCodeToNode() 
	{
		action.waitForElementVisible(driver, businessList);
		action.clickLink(businessList);
	//	action.selectvaluefromMultipleDropdown(businessList, value);
		action.clickLink(leftArrowBusniess);
		action.clickLink(btnSubmitBusiness);
	}

	
	By accountList  = By.xpath(".//*[@id='accountList-avail']/option");
	By leftArrowAccount = By.xpath(".//*[@id='accountList-select']");
	By btnSubmitAccount = By.xpath(".//*[@id='bankAccountForm']/div[6]/input[1]");

	public void addAccountToNode() 
	{
		action.waitForElementVisible(driver, accountList);
		action.clickLink(accountList);
		//action.selectvaluefromMultipleDropdown(accountList, value);
		action.clickLink(leftArrowAccount);
		action.clickLink(btnSubmitAccount);
	}
	
	By nodeEmail = By.xpath(".//*[@id='masterEmailAlertGroup']");
	By submitEmail = By.xpath(".//*[@id='emailAddressesForm']/div[3]/input[1]");
	public void addEmailAndSubmitNode()
	{
		action.clickLink(nodeEmail);
		action.clickButton(submitEmail);
	}
}
