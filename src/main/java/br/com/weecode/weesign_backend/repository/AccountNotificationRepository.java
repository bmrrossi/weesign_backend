package br.com.weecode.weesign_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.weecode.weesign_backend.models.AccountNotification;

public interface AccountNotificationRepository extends JpaRepository<AccountNotification, Long> {

}
