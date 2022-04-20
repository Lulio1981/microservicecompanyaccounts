package bootcamp.microservices.app.companyaccounts.creditcardcompany;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bootcamp.microservices.app.companyaccounts.documents.CompanyCreditCard;
import reactor.core.publisher.Mono;

@FeignClient(name = "microservice-clients")
public interface CompanyAccountFeignCreditCardCompany {

	@GetMapping("/idCompany/{idCompany}")
	public Mono<CompanyCreditCard> searchByIdCompany(@PathVariable String idCompany);
}
