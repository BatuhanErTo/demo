package com.spaghetti.demo.service;

import com.spaghetti.demo.dto.CustomerDto;
import com.spaghetti.demo.dto.converter.CustomerDtoConverter;
import com.spaghetti.demo.exception.CustomerNotFoundException;
import com.spaghetti.demo.model.Customer;
import com.spaghetti.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;
    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    public CustomerDto getCustomerById(String id){
        Customer customer = findCustomerById(id);
        return  customerDtoConverter.convert(customer);
    }

    public List<CustomerDto> getAllCustomers(){
        return customerRepository.findAll()
                .stream()
                .map(customerDtoConverter::convert)
                .collect(Collectors.toList());
    }
    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer does not exist"));
    }
}
