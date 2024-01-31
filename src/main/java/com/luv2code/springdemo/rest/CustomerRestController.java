package com.luv2code.springdemo.rest;

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

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	//autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	//this is how you inject the dependency
	
	//GetMapping for /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers()
	{
		return customerService.getCustomers();
	}
	
	//GetMapping for /customers/{customerId}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId)
	{
		Customer theCustomer = customerService.getCustomer(customerId);
		
		//add condition to throw exception
		if(theCustomer == null)
		{
			throw new CustomerNotFoundException("CustomerId not found "+customerId);
		}
		
		//executes as a else part
		return theCustomer;
	}
	
	//PostMapping for /customers - add a new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer)
	{
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	//PutMapping for /customers - Update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer)
	{
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	//DeleteMapping for /customers/{customerId} - Delete existing customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId)
	{
		//check if customerId exists
		Customer tempCustomer = customerService.getCustomer(customerId);
		
		if(tempCustomer == null)
		{
			throw new CustomerNotFoundException("CustomerId not found" + customerId);
		}
		
		//delete the customer
		customerService.deleteCustomer(customerId);
		return "Deleted CustomerId is " + customerId;
	}
}






















