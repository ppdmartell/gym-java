package com.example.address.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "street_address")
	private String streetAddress;
	
	private String apartment;
	
	@Column(name = "zip_code")
	private Integer zipCode;
	
	@Column(name = "city_state")
	private String cityState;
	
	@Column(name = "country_region")
	private String countryRegion;
	
	@Column(name = "business")
	private boolean isBusinessAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_contacts_id", referencedColumnName = "id")
	private ContactInfo contactInfo;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;
	
	public Address() {}
	
	public Address(String firstName, String lastName, String streetAddress, String apartment, Integer zipCode,
			String cityState, String countryRegion, boolean isBusinessAddress, ContactInfo contactInfo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.apartment = apartment;
		this.zipCode = zipCode;
		this.cityState = cityState;
		this.countryRegion = countryRegion;
		this.isBusinessAddress = isBusinessAddress;
		this.contactInfo = contactInfo;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getApartment() {
		return apartment;
	}
	
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	
	public Integer getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getCityState() {
		return cityState;
	}
	
	public void setCityState(String cityState) {
		this.cityState = cityState;
	}
	
	public String getCountryRegion() {
		return countryRegion;
	}
	
	public void setCountryRegion(String countryRegion) {
		this.countryRegion = countryRegion;
	}
	
	public boolean isBusinessAddress() {
		return isBusinessAddress;
	}
	
	public void setBusinessAddress(boolean isBusinessAddress) {
		this.isBusinessAddress = isBusinessAddress;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
}
