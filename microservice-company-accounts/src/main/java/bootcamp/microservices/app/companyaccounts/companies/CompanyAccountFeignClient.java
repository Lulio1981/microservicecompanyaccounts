package bootcamp.microservices.app.companyaccounts.companies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bootcamp.microservices.app.companyaccounts.documents.Company;
import reactor.core.publisher.Mono;

@FeignClient(name = "microservice-clients")
public interface CompanyAccountFeignClient {

	@GetMapping("/id/{id}")
	public Mono<Company> searchById(@PathVariable String id);

}
