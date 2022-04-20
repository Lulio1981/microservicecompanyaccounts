package bootcamp.microservices.app.companyaccounts.utils;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bootcamp.microservices.app.companyaccounts.companies.CompanyAccountFeignClient;
import bootcamp.microservices.app.companyaccounts.creditcardcompany.CompanyAccountFeignCreditCardCompany;
import bootcamp.microservices.app.companyaccounts.documents.Company;
import bootcamp.microservices.app.companyaccounts.documents.CompanyCreditCard;
import bootcamp.microservices.app.companyaccounts.documents.Signatory;

@Component
public class Validation {

	@Autowired
	private CompanyAccountFeignClient companyAccountFeignClient;

	@Autowired
	private CompanyAccountFeignCreditCardCompany companyAccountFeignCreditCardCompany;

	public Boolean createAccountValidation(String idCompany) {
		Boolean createAccount = false;
		Company company = new Company();
		company = companyAccountFeignClient.searchById(idCompany).block();

		if (company.getProfile().equals("MYPE")) {
			Optional<CompanyCreditCard> creditCardQuanty = companyAccountFeignCreditCardCompany
					.searchByIdCompany(idCompany).blockOptional();
			if (!creditCardQuanty.isEmpty()) {
				createAccount = true;
			} else {
				createAccount = false;
			}
		} else {
			createAccount = true;
		}
		return createAccount;
	}

	public Boolean signatoryAccountValidatio(List<Signatory> list) {
		Boolean createAccount = false;
		if (list.size() > 0) {
			createAccount = true;
		}
		return createAccount;
	}
}
