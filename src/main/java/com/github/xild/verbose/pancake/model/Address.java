package com.github.xild.verbose.pancake.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.github.xild.verbose.pancake.model.to.AddressInput;

@Entity(name = "Address")
@Table(name = "ADDRESS")
@SequenceGenerator(name = "SEQ_ADDRESS", sequenceName = "SEQ_ADDRESS", initialValue = 1, allocationSize = 1)
public class Address implements com.github.xild.verbose.pancake.model.Entity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "SEQ_ADDRESS")
	private Long id;

	@Column(name = "STREET", nullable = false)
	private String street;

	@Column(name = "CITY", nullable = false)
	private String city;

	@Column(name = "STATE", nullable = false)
	private String state;

	@Column(name = "ADDRESS_NUMBER", nullable = false)
	private String addressNumber;

	@Column(name = "ZIP_CODE", nullable = false)
	private String zipCode;

	@Column(name = "address_detail")
	private String addressDetail;

	@Column(name = "NEIGHBORHOOD")
	private String neighborhood;

	public Address() {
		super();
	}

	public Address(AddressInput input) {
		super();
		this.street = input.getRua();
		this.city = input.getCidade();
		this.state = input.getEstado();
		this.addressNumber = input.getNumero();
		this.zipCode = input.getCep();
		this.addressDetail = input.getComplemento();
		this.neighborhood = input.getBairro();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", addressNumber="
				+ addressNumber + ", zipCode=" + zipCode + ", addressDetail=" + addressDetail + ", neighborhood="
				+ neighborhood + "]";
	}

}
