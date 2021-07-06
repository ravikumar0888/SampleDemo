
package testdataobjects.initsession;

import java.util.HashMap;
import java.util.Map;

public class SignerInfos {

	private String customerId;
	private String customerType;
	private DebtorBankAccount debtorBankAccount;
	private String email;
	private String firstName;
	private String gender;
	private String lastName;
	private String phone;
	private PostalAddress postalAddress;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public DebtorBankAccount getDebtorBankAccount() {
		return debtorBankAccount;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getGender() {
		return gender;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public PostalAddress getPostalAddress() {
		return postalAddress;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public void setDebtorBankAccount(DebtorBankAccount debtorBankAccount) {
		this.debtorBankAccount = debtorBankAccount;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPostalAddress(PostalAddress postalAddress) {
		this.postalAddress = postalAddress;
	}

}
