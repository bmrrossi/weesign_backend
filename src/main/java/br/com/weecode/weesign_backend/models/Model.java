package br.com.weecode.weesign_backend.models;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Data
public class Model implements Serializable {
	
	private static final long serialVersionUID = -7921145470303932486L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String size;
	private String extension;
	@CreatedBy
	@OneToOne
	private Account created_by_account;
	@CreatedDate
	private Instant created_at;
	@LastModifiedDate
	private Instant updated_at;
}