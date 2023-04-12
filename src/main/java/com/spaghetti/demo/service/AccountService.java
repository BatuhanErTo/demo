package com.spaghetti.demo.service;

import com.spaghetti.demo.dto.AccountDto;
import com.spaghetti.demo.dto.CreateAccountRequest;
import com.spaghetti.demo.dto.converter.AccountDtoConverter;
import com.spaghetti.demo.model.Account;
import com.spaghetti.demo.model.Customer;
import com.spaghetti.demo.model.Transaction;
import com.spaghetti.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    private final AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService,
                          AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());
        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());
        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0){
            Transaction transaction = new Transaction(
                    createAccountRequest.getInitialCredit(),
                    LocalDateTime.now(),
                    account);
            account.getTransactions().add(transaction);
        }
        return accountDtoConverter.convert(accountRepository.save(account));
    }
}
