package com.sangng.accountservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sangng.accountservice.dto.AccountDto;
import com.sangng.accountservice.model.Account;
import com.sangng.accountservice.respone.ApiRespone;
import com.sangng.accountservice.service.IAccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/accounts")
public class AccountController {

    private final IAccountService accountService;

    @GetMapping("/getByID/{id}")
    public ResponseEntity<ApiRespone> getAccountById(@PathVariable("id") Long id) {
        try {
            AccountDto accountDto = accountService.toDto(accountService.getAccountById(id));
            return ResponseEntity.ok(new ApiRespone("Account found", accountDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespone(e.getMessage(), null));
        }
    
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiRespone> getAllAccounts() {
        return ResponseEntity.ok(new ApiRespone("Accounts found",
                 accountService.toDtoList(accountService.getAllAccounts())));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiRespone> createAccount(@RequestBody Account account) {
        AccountDto accountDto = accountService.toDto(accountService.createAccount(account));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiRespone("Account created", accountDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiRespone> updateAccount(@PathVariable("id") Long id, @RequestBody Account account) {
        try {
            Account updatedAccount = accountService.updateAccount(id, account);
            AccountDto accountDto = accountService.toDto(updatedAccount);
            return ResponseEntity.ok(new ApiRespone("Account updated", accountDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespone(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiRespone> deleteAccount(@PathVariable("id") Long id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.ok(new ApiRespone("Account deleted", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespone(e.getMessage(), null));
        }
    }

}
