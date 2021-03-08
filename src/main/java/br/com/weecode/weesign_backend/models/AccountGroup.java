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
public class AccountGroup implements Serializable {
	
	private static final long serialVersionUID = -1147958924096180084L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private boolean status;
	@CreatedBy
	@OneToOne
	private Account created_by_account;	
	@CreatedDate
	private Instant created_at;
	@LastModifiedDate
	private Instant updated_at;
}
