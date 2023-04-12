package com.spaghetti.demo.dto.converter;

import com.spaghetti.demo.dto.AccountDto;
import com.spaghetti.demo.dto.CustomerAccountDto;
import com.spaghetti.demo.model.Account;
import com.spaghetti.demo.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {
    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConverter transactionDtoConverter;
    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter,
                               TransactionDtoConverter transactionDtoConverter1) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter1;
    }

    public AccountDto convert(Account account){
        return new AccountDto(account.getId(),
                account.getCreationDate(),
                account.getBalance(),
                customerDtoConverter.convertToAccountCustomer(Objects.requireNonNull(account.getCustomer())),
                Objects.requireNonNull(account.getTransactions())
                        .stream()
                        .map(transactionDtoConverter::convertToTransactionDto)
                        .collect(Collectors.toSet())
        );
    }
}
