package com.sangng.accountservice.service;

import java.util.List;

import com.sangng.accountservice.dto.AccountDto;
import com.sangng.accountservice.model.Account;

public interface IAccountService {

    Account getAccountById(Long id);

    List<Account> getAllAccounts();

    Account createAccount(Account account);

    Account updateAccount(Long id, Account account);

    void deleteAccount(Long id);

    AccountDto toDto(Account account);

    List<AccountDto> toDtoList(List<Account> accounts);

    
}
