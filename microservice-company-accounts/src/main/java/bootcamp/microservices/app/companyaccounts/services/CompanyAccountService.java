package bootcamp.microservices.app.companyaccounts.services;

import bootcamp.microservices.app.companyaccounts.documents.CompanyAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyAccountService {

	public Flux<CompanyAccount> findAll();

	public Mono<CompanyAccount> findById(String id);

	public Mono<CompanyAccount> save(CompanyAccount companyAccount);

	public Mono<CompanyAccount> update(CompanyAccount companyAccount);

	public Mono<Void> deleteNonLogic(CompanyAccount companyAccount);

	public Mono<CompanyAccount> deleteLogic(CompanyAccount companyAccount);

	public Mono<Long> countNumberOfAccount(String idCompany);

	public Flux<CompanyAccount> findByIdCompany(String idCompany);

}
