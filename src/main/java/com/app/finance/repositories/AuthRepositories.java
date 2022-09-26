package com.app.finance.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.finance.entity.UserCredential;
public interface AuthRepositories extends JpaRepository<UserCredential, Long>{
	public UserCredential findByUserName(String userName);
}
