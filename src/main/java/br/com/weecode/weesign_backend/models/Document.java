package br.com.weecode.weesign_backend.models;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Entity
@Data
public class Document implements Serializable {
	
	private static final long serialVersionUID = -4703898930362081187L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@CreatedBy
	@OneToOne
	private Account created_by_account;
	private int size;
	private String extension;
	private boolean signed;
	private boolean trash;
	private String document_path;
	@OneToOne
	private Model linked_model;
	@CreatedDate
	private Instant created_at;
	@LastModifiedDate
	private Instant updated_at;	

}
