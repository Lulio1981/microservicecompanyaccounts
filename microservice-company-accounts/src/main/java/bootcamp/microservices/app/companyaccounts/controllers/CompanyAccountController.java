package bootcamp.microservices.app.companyaccounts.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.microservices.app.companyaccounts.documents.CompanyAccount;
import bootcamp.microservices.app.companyaccounts.services.CompanyAccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CompanyAccountController {

	@Autowired
	private CompanyAccountService service;

	@PostMapping
	public Mono<CompanyAccount> createAccount(@Valid @RequestBody CompanyAccount companyAccount) {
		return service.save(companyAccount);
	}

	@GetMapping("/all")
	public Flux<CompanyAccount> searchAll() {
		return service.findAll();
	}

	@GetMapping("/id/{id}")
	public Mono<CompanyAccount> searchById(@PathVariable String id) {
		return service.findById(id);
	}

	@GetMapping("/idCompany/{idCompany}")
	public Flux<CompanyAccount> searchByIdCompany(@PathVariable String idCompany) {
		return service.findByIdCompany(idCompany);
	}

	@PutMapping
	public Mono<CompanyAccount> updateAccountCompany(@RequestBody CompanyAccount companyAccount) {
		return service.update(companyAccount);
	}

	@DeleteMapping
	public Mono<CompanyAccount> deleteAccountCompany(@Valid @RequestBody CompanyAccount companyAccount) {
		return service.deleteLogic(companyAccount);
	}

}
