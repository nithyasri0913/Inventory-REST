package com.wellsfargo.training.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ims.exception.ResourceNotFoundException;
import com.wellsfargo.training.ims.model.Address;
import com.wellsfargo.training.ims.model.Dealer;
import com.wellsfargo.training.ims.model.DealerAndAddressProjection;
import com.wellsfargo.training.ims.service.DealerService;

// Controller for registering and logging process of a dealer

//Spring MVC provides @CrossOrigin annotation that marks the annotated method or type as permitting cross-origin requests.
//The CORS (Cross-Origin Resource Sharing) allows a webpage to request additional resources into the browser from other domains
//such as API data using AJAX, font files, style sheets etc. 

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class DealerController {

	@Autowired // DI
	private DealerService dservice;

	/*
	 * ResponseEntity represents an HTTP response, including headers, body, and
	 * status.
	 */
	@PostMapping("/register")
	public ResponseEntity<String> createDealer(@Validated @RequestBody Dealer dealer) {

		try {
			Address address = dealer.getAddress();
			address.setDealer(dealer);
			dealer.setAddress(address);

			Dealer registeredDealer = dservice.registerDealer(dealer);
			if (registeredDealer != null) {
				return ResponseEntity.ok("Reg succ");
			} else {
				return ResponseEntity.badRequest().body("Reg failed");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured" + e.getMessage());
		}

	}

	// Open Postman and make POST request with email & password -
	// http://localhost:8085/ims/api/login

	@PostMapping("/login")
	public Boolean loginDealer(@Validated @RequestBody Dealer d) throws ResourceNotFoundException {
		Boolean isLoggedin = false;

		String email = d.getEmail();
		String password = d.getPassword();

		Dealer dealer = dservice.loginDealer(email)
				.orElseThrow(() -> new ResourceNotFoundException("Dealer not found for this email id."));

		if (email.equals(dealer.getEmail()) && password.equals(dealer.getPassword())) {
			isLoggedin = true;
		}

		return isLoggedin;

	}

	@GetMapping("/dealers")
	public ResponseEntity<List<DealerAndAddressProjection>> getDealerInfo() {
		try {
			List<DealerAndAddressProjection> selectedFields = dservice.getDealerInfo();
			return ResponseEntity.ok(selectedFields);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
