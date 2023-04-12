package com.spaghetti.demo.service;

import com.spaghetti.demo.dto.CustomerDto;
import com.spaghetti.demo.dto.converter.CustomerDtoConverter;
import com.spaghetti.demo.exception.CustomerNotFoundException;
import com.spaghetti.demo.model.Customer;
import com.spaghetti.demo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;
    private CustomerService customerService;

    @BeforeEach
    void setUp(){
        customerRepository = mock(CustomerRepository.class);
        customerDtoConverter = mock(CustomerDtoConverter.class);
        customerService = new CustomerService(customerRepository,customerDtoConverter);
    }

    @Test
    void testFindCustomerById_whenCustomerIdExist_shouldReturnCustomer(){
        Customer customer = new Customer("id","Batuhan","Erol", Set.of());

        when(customerRepository.findById("id")).thenReturn(Optional.of(customer));

        Customer result = customerService.findCustomerById("id");
        assertEquals(customer,result);
    }

    @Test
    void testFindCustomerById_whenCustomerIdNotExist_shouldThrowCustomerNotFoundException(){
        when(customerRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> customerService.findCustomerById("id"));
    }

    @Test
    void testGetCustomerById_whenCustomerIdExist_shouldReturnCustomerDto(){
        Customer customer = new Customer("id","Batuhan","Erol",Set.of());
        CustomerDto customerDto = new CustomerDto("id","Batuhan","Erol",Set.of());

        when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        when(customerDtoConverter.convert(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomerById("id");

        assertEquals(customerDto,result);
    }

    @Test
    void testGetCustomerById_whenCustomerIdExist_shouldThrowCustomerNotFoundException(){
        when(customerRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> customerService.getCustomerById("id"));

        verifyNoInteractions(customerDtoConverter);
    }
}