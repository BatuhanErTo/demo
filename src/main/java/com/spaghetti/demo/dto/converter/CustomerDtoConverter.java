package com.spaghetti.demo.dto.converter;

import com.spaghetti.demo.dto.AccountCustomerDto;
import com.spaghetti.demo.dto.CustomerDto;
import com.spaghetti.demo.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {
    private final CustomerAccountDtoConverter accountDtoConverter;

    public CustomerDtoConverter(CustomerAccountDtoConverter accountDtoConverter) {
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountCustomerDto convertToAccountCustomer(Customer customer) {
        return new AccountCustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getSurname());
    }

    public CustomerDto convert(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getSurname(),
                Objects.requireNonNull(customer.getAccounts())
                        .stream()
                        .map(accountDtoConverter::convertToCustomerAccountDto)
                        .collect(Collectors.toSet()));
    }
}
