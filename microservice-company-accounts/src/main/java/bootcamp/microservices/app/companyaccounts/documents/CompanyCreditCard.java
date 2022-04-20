package bootcamp.microservices.app.companyaccounts.documents;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
@Document
public class CompanyCreditCard {

	private String id;

	private String creditCardNumber;

	private String idCompany;

	private Double creditLine;

	private Date expeditionDate;

	private Date expirationDate;
	
	private Date deadline;
	
	private Date paymentDate;

	private Double interestRate;

	private Double moratoriumInterestRate;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createDate;

	private String createUser;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date modifyDate;

	private String modifyUser;

	private Integer status;

}
