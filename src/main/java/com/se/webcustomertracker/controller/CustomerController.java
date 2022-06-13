package com.se.webcustomertracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.webcustomertracker.entity.Customer;
import com.se.webcustomertracker.service.CustomerService;

@RestController
@RequestMapping("api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customers")
	public Customer saveCustomer(@RequestBody Customer theCustomer) {

		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;

	}

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}

	@DeleteMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable int id) {
		Customer theCustomer = customerService.getCustomer(id);
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer is not found - " + id);
		}
		customerService.deleteCustomer(id);
		return "Delete customer id= " + id;
	}

	@GetMapping("/customers")
	public List<Customer> listCustomers() {
		return customerService.getCustomers();
	}

	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable int id) {
		Customer theCustomer = customerService.getCustomer(id);
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer is not found - " + id);
		}
		return theCustomer;
	}

}
