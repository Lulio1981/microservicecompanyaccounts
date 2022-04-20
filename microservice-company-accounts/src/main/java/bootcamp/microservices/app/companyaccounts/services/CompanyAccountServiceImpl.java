package bootcamp.microservices.app.companyaccounts.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootcamp.microservices.app.companyaccounts.documents.CompanyAccount;
import bootcamp.microservices.app.companyaccounts.exceptions.customs.CustomNotFoundException;
import bootcamp.microservices.app.companyaccounts.repository.CompanyAccountRepository;
import bootcamp.microservices.app.companyaccounts.utils.Validation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompanyAccountServiceImpl implements CompanyAccountService {

	@Autowired
	private CompanyAccountRepository companyAccountRepository;

	@Autowired
	private Validation validation;

	@Override
	public Flux<CompanyAccount> findAll() {
		return companyAccountRepository.findAll()
				.switchIfEmpty(Mono.error(new CustomNotFoundException("Accounts not exist")));
	}

	@Override
	public Mono<CompanyAccount> findById(String id) {
		return companyAccountRepository.findById(id)
				.switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyAccount not found")));
	}

	@Override
	public Mono<CompanyAccount> update(CompanyAccount account) {
		return companyAccountRepository.findById(account.getId()).flatMap(c -> {
			c.setModifyUser(account.getModifyUser());
			c.setModifyDate(new Date());
			return companyAccountRepository.save(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyAccount not found")));
	}

	@Override
	public Mono<CompanyAccount> save(CompanyAccount companyAccount) {
		Boolean authorize = validation.createAccountValidation(companyAccount.getIdCompany());
		Boolean haveSignatory = validation.signatoryAccountValidatio(companyAccount.getListOfSignatories());

		if (authorize == true && haveSignatory == true) {
			return companyAccountRepository.save(companyAccount);
		} else {
			return Mono.error(new CustomNotFoundException("Account must have signatories"));
		}
	}

	@Override
	public Mono<Void> deleteNonLogic(CompanyAccount companyAccount) {
		return companyAccountRepository.findById(companyAccount.getId()).flatMap(c -> {
			return companyAccountRepository.delete(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyAccount not found")));
	}

	@Override
	public Mono<CompanyAccount> deleteLogic(CompanyAccount companyAccount) {
		return companyAccountRepository.findById(companyAccount.getId()).flatMap(c -> {
			c.setModifyUser(companyAccount.getModifyUser());
			c.setModifyDate(new Date());
			return companyAccountRepository.save(c);
		}).switchIfEmpty(Mono.error(new CustomNotFoundException("CompanyAccount not found")));
	}

	@Override
	public Mono<Long> countNumberOfAccount(String idCompany) {
		return companyAccountRepository.findByIdCompany(idCompany).count();
	}

	@Override
	public Flux<CompanyAccount> findByIdCompany(String idCompany) {
		return companyAccountRepository.findByIdCompany(idCompany);
	}

}
