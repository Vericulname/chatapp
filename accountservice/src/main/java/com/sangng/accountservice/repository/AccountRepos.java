package com.sangng.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangng.accountservice.model.Account;

@Repository
public interface AccountRepos extends JpaRepository<Account, Long> {

}
