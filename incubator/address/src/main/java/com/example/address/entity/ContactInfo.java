package com.example.address.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "contacts")
public class ContactInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "email_address")
	private String emailAddress;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name = "updatedAt")
	@UpdateTimestamp
	private Date updatedAt;
	
	public ContactInfo() {}
	
	public ContactInfo(String emailAddress, String phoneNumber) {
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
