package br.com.weecode.weesign_backend.models;

import java.io.Serializable;
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
public class AccountSetting implements Serializable {

	private static final long serialVersionUID = 239795265025134011L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private Account account;
	private Integer session_timeout;
	private boolean receive_newsletter;
	private boolean share_personal_data;
	private String default_timezone;
	private String default_language;
	@CreatedDate
	private Instant created_at;
	@LastModifiedDate
	private Instant updated_at;

}
