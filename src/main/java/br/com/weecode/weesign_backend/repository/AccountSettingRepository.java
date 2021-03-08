package br.com.weecode.weesign_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.weecode.weesign_backend.models.AccountSetting;

public interface AccountSettingRepository extends JpaRepository<AccountSetting, Long> {

}
