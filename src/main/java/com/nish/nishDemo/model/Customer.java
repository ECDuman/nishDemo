package com.nish.nishDemo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private long id;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column
	private String phone;

	@Column
	private String email;
	
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy="customer" , fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Document> documents;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getId() {
		return id;
	}
	public List<Document> getDocuments() {
		return documents;
	}
	public void setDocument(List<Document> documents) {
		this.documents = documents;
	}
}
