package com.nish.nishDemo.primary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nish.nishDemo.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
	
}
