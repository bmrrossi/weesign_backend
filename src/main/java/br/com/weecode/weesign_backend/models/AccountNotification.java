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
public class AccountNotification implements Serializable {

	private static final long serialVersionUID = 6378799207631298829L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private Account account;
	private boolean new_pendant_signature;
	private boolean new_task_workflow;
	@CreatedDate
	private Instant created_at;
	@LastModifiedDate
	private Instant updated_at;
}
