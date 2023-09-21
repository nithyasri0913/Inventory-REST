package com.wellsfargo.training.ims.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
// Model class for registration of users

@Entity
@Table(name="dealers")
public class Dealer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="did")
	private Long id;
	
	@Column(unique=true)
	private String email;
	
	@Column(name="first_name")
	private String fname;
	
	@Column(name="last_name")
	private String lname;
	
	private String password;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	@Column(name="phone", unique=true)
	private String phoneno;
	
	
	/* 	 Model 1-1 mapping between Dealer and Address objects */
	
	@OneToOne(mappedBy="dealer", cascade=CascadeType.ALL)
	private Address address;
	
	public Dealer() {
		super();
	}
	

	public Dealer(long id, String email, String fname, String lname, String password, Date dob, String phoneno) {
		super();
		this.id = id;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.dob = dob;
		this.phoneno = phoneno;
	}


	public Dealer(long id, String email, String fname, String lname, String password, Date dob, String phoneno,
			Address address) {
		super();
		this.id = id;
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.dob = dob;
		this.phoneno = phoneno;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Base64.Encoder encoder=Base64.getEncoder();
		String normalString=password;
		String encodedString = encoder.encodeToString(
				normalString.getBytes(StandardCharsets.UTF_8));
		this.password=encodedString;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
	this.address=address;	
	}
	
	

}
