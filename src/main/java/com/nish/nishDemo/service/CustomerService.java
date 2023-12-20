package com.nish.nishDemo.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nish.nishDemo.model.Customer;
import com.nish.nishDemo.model.Document;
import com.nish.nishDemo.primary.repository.CustomerRepository;
import com.nish.nishDemo.primary.repository.DocumentRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    DocumentService documentService;

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

	public Customer addCustomerWithDocument(String jsonObject, MultipartFile[] file)
			throws JsonProcessingException, JsonMappingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Customer customerTemp = objectMapper.readValue(jsonObject, Customer.class);
		Customer customer = new Customer();
		if(customerTemp.getFirstName() != null)
			customer.setFirstName(customerTemp.getFirstName());
		if(customerTemp.getLastName() != null)
			customer.setLastName(customerTemp.getLastName());
		if(customerTemp.getEmail() != null)
			customer.setEmail(customerTemp.getEmail());
		if(customerTemp.getPhone() != null)
			customer.setPhone(customerTemp.getPhone());
//	    	Optional<Customer> customer = service.getCustomerByName(customerTemp.getFirstName(), customerTemp.getLastName());
		addCustomer(customer);
		for(MultipartFile document: file){
			documentService.saveDocument(document,customer.getId());
		}
//	    	documentController.uploadFiles(file, customer.getId());
		return customer;
	}
    public List<Customer> addCustomers(List<Customer> customers){
        return customerRepository.saveAll(customers);
    }

    public Customer getCustomerById(long id){
        return customerRepository.findById(id).orElseThrow(() ->
        new RuntimeException("Customer not found"));
    }

    public Optional<Customer> getCustomerByName(String firstName, String lastName){
    	return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Customer> getAllCustomers(){
        return StreamSupport
                .stream(customerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional(transactionManager="transactionManager")
    public Customer updateCustomer(Customer customer){
        Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
        if(existingCustomer.equals(null))
            return  null;
        if(customer.getFirstName() != null)
        	existingCustomer.setFirstName(customer.getFirstName());
        if(customer.getLastName() != null)
        	existingCustomer.setLastName(customer.getLastName());
        if(customer.getEmail() != null)
        	existingCustomer.setEmail(customer.getEmail());
        if(customer.getPhone() != null)
        	existingCustomer.setPhone(customer.getPhone());
        return customerRepository.save(existingCustomer);
    }

    public String deleteCustomer(long id){
    	Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
        return "The customer " + customer.getFirstName() + " " + customer.getLastName() + " is deleted!!";
    }
    
    @Transactional(transactionManager="transactionManager")
    public Customer addDocumentToCustomer(Long customerId, Long documentId){
    	Customer customer = getCustomerById(customerId);
        Document document = documentService.getDocument(documentId);
        if(Objects.nonNull(document.getCustomer())){
        	throw new RuntimeException("Document already exists");
        }
        customer.getDocuments().add(document);
        return customer;
    }

    @Transactional(transactionManager="transactionManager")
    public Customer removeDocumentFromCustomer(Long customerId, Long documentId){
    	Customer customer = getCustomerById(customerId);
        Document document = documentService.getDocument(documentId);
        customer.getDocuments().remove(document);
        return customer;
    }
}