package com.spaghetti.demo.dto.converter;

import com.spaghetti.demo.dto.CustomerAccountDto;
import com.spaghetti.demo.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {
    private final TransactionDtoConverter transactionDtoConverter;

    public CustomerAccountDtoConverter(TransactionDtoConverter transactionDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public CustomerAccountDto convertToCustomerAccountDto(Account account) {
        return new CustomerAccountDto(
                account.getId(),
                account.getCreationDate(),
                account.getBalance(),
                Objects.requireNonNull(account.getTransactions())
                        .stream()
                        .map(transactionDtoConverter::convertToTransactionDto)
                        .collect(Collectors.toSet()));
    }
}
