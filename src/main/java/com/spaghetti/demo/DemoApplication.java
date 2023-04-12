package com.spaghetti.demo;

import com.spaghetti.demo.model.Customer;
import com.spaghetti.demo.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	private final CustomerRepository customerRepository;

	public DemoApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer1 = customerRepository.save(new Customer("Batuhan","Erol"));
		Customer customer2 = customerRepository.save(new Customer("Tuana","Erol"));
		System.out.println(customer1);
		System.out.println(customer2);
	}
}
