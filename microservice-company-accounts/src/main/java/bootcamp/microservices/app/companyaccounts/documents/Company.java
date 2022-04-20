package bootcamp.microservices.app.companyaccounts.documents;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document
public class Company implements Serializable {

	private static final long serialVersionUID = 7908837786258878828L;

	private String id;

	private String documentNumber;

	private String companyName;

	private String generalManager;

	private String emailGeneralManager;

	private Integer address;

	private String phoneNumber;

	private String profile;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createDate;

	private String createUser;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date modifyDate;

	private String modifyUser;

}
