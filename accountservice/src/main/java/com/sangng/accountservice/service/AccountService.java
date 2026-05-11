package com.sangng.accountservice.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sangng.accountservice.dto.AccountDto;
import com.sangng.accountservice.model.Account;
import com.sangng.accountservice.repository.AccountRepos;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepos accountRepos;
    private final ModelMapper modelMapper;

    @Override
    public Account getAccountById(Long id) {
        return Optional.ofNullable(accountRepos.findById(id)).get()
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepos.findAll();
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepos.save(account);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        return Optional.ofNullable(accountRepos.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(existingAccount -> {
                    existingAccount.setUsername(account.getUsername());
                    existingAccount.setPassword(account.getPassword());
                    return accountRepos.save(existingAccount);
                })
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepos.deleteById(id);

    }

    @Override
    public AccountDto toDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    @Override
    public List<AccountDto> toDtoList(List<Account> accounts) {
        return accounts.stream().map(this::toDto).toList();
    }
}

    