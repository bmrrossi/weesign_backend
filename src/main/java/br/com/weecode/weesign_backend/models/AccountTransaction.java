package br.com.weecode.weesign_backend.models;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Data
public class AccountTransaction implements Serializable {

	private static final long serialVersionUID = 2776494299744939978L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private Account account;
	private Date date;
	private Double price;
	private String reference_code;
	@CreatedDate
	private Instant created_at;
	@LastModifiedDate
	private Instant updated_at;
}
