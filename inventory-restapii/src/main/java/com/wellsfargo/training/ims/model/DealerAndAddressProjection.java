package com.wellsfargo.training.ims.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//template class to fetch objects from 2 classes using custom queries
// projection - extract columns
// selection - extract rows
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DealerAndAddressProjection {

	private Long id;
	private String fname;
	private String lname;
	private String phoneno;
	private String email;
	private String street;
	private String city;
	private int pincode;
}
