package com.nish.nishDemo.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nish.nishDemo.model.Customer;
import com.nish.nishDemo.model.Document;
import com.nish.nishDemo.primary.repository.CustomerRepository;
import com.nish.nishDemo.primary.repository.DocumentRepository;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional(transactionManager="transactionManager")
    public Document saveDocument(MultipartFile file, long customerId){
        Customer customer = customerRepository.findById(customerId).get();
        if(!customer.equals(null)){
            try{
                Document document = new Document();
                document.setName(file.getOriginalFilename());
                document.setData(file.getBytes());
                document.setCustomer(customer);
                return documentRepository.save(document);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Document getDocument(long id){
        return documentRepository.findById(id).orElseThrow(() ->
        new RuntimeException("Document not found"));
    }

    public List<Document> getAllDocuments(){
        return StreamSupport
                .stream(documentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional(transactionManager="transactionManager")
    public Document updateDocument(MultipartFile file, Long documentId) throws IOException{
        Document existingFile = documentRepository.findById(documentId).orElse(null);
        if(existingFile.equals(null))
            return  null;
        existingFile.setName(file.getOriginalFilename());
        existingFile.setData(file.getBytes());
        return documentRepository.save(existingFile);
    }

    public String deleteDocument(long documentId){
        Document document = getDocument(documentId);
        documentRepository.delete(document);
        return "The file with id: " + documentId + " was deleted!!";
    }


}
