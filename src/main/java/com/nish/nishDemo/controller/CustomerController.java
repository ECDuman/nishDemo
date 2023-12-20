package com.nish.nishDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nish.nishDemo.model.Customer;
import com.nish.nishDemo.service.CustomerService;

@RestController
@RequestMapping("/nishapi")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping("/addCustomer")
	public Customer addCustomer(@RequestParam("customer") String jsonObject, @RequestParam("documents") MultipartFile[] file) throws JsonMappingException, JsonProcessingException{
	    	Customer customer = service.addCustomerWithDocument(jsonObject, file);
	    	return customer;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable long id){
        return service.getCustomerById(id);
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer){
        return service.updateCustomer(customer);
    }

    @DeleteMapping("deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable long id){
        return service.deleteCustomer(id);
    }
}