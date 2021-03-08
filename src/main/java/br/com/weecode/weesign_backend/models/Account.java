package br.com.weecode.weesign_backend.models;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Data
public class Account implements Serializable {

	private static final long serialVersionUID = 2873130029265983156L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private String email;
	private String first_name;
	private String last_name;
	private boolean is_active;
	private Date last_login;
	private Date date_joined;
	private String phone;
	private String cpf;
	private String genre;
	private String signatureSvg;
	private String headingSvg;
	private Date birthday;
	private String bio;
	private String address;
	private String address_number;
	private String address_complement;
	@CreatedDate
	private Instant created_at;
	@LastModifiedDate
	private Instant updated_at;
	
}
