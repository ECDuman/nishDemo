package com.nish.nishDemo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nish.nishDemo.model.Document;
import com.nish.nishDemo.service.DocumentService;

@RestController
@RequestMapping("/nishapi")
public class DocumentController {
    @Autowired
    private DocumentService service;

    @PostMapping("/customers/{customerId}/uploadFile")
    public String uploadFiles(@RequestParam("documents")MultipartFile[]documents, @PathVariable long customerId){
        for(MultipartFile document: documents){
            service.saveDocument(document,customerId);
        }
        return "uploaded";
    }

    @GetMapping("/downloadDocument/{id}")
    public ResponseEntity<ByteArrayResource> downloadDocument(@PathVariable long id){
        Document file = service.getDocument(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+file.getName()+"\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @GetMapping("/documents")
    public List<Document> getAllFiles(){
        return service.getAllDocuments();
    }

    @GetMapping("/documents/{id}")
    public Document getFileById(@PathVariable long id){
        return service.getDocument(id);
    }

    @PutMapping("/updateDocument/{documentId}")
    public Document updateFile(@RequestParam("documents")MultipartFile document, @PathVariable long documentId) throws IOException{
    	return service.updateDocument(document,documentId);
    }

    @DeleteMapping("deleteFile/{documentId}")
    public String deleteFile(@PathVariable long documentId){
        return service.deleteDocument(documentId);
    }
}