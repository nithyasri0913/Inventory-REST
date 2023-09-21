package com.wellsfargo.training.ims.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.ims.exception.ResourceNotFoundException;
import com.wellsfargo.training.ims.model.Product;
import com.wellsfargo.training.ims.service.ProductService;

/*
* Spring RestController annotation is used to create RESTful web services using Spring MVC. 
* Spring RestController takes care of mapping request data to the defined request handler method. 
* Once response body is generated from the handler method, it converts it to JSON or XML response. 
* 
* @RequestMapping - maps HTTP request with a path to a controller 
* */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class ProductController {

	@Autowired
	private ProductService pservice;

	// POSTMAN - REST API Testing Tool

	/*
	 * Open PostMan, make a POST Request - http://localhost:8085/pms/api/products/
	 * Select body -> raw -> JSON Insert JSON product object.
	 */

	/*
	 * @RequestBody annotation automatically deserializes the JSON into a Java type
	 */

	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@Validated @RequestBody Product product) {
		try {
			Product p = pservice.saveProduct(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(p);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// http://localhost:8085/ims/api/products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		try {
			List<Product> products = pservice.listAll();
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getByProductId(@PathVariable(value = "id") Long pid)
			throws ResourceNotFoundException {
		Product p = pservice.getSingleProduct(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id" + pid));
		return ResponseEntity.ok().body(p);
	}

	// Open PostMan, make a PUT Request -
	// http://localhost:8085/ims/api/products/1003
	// Select body -> raw -> JSON
	// Update JSON product object with new Values.
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long pid,
			@Validated @RequestBody Product p) throws ResourceNotFoundException {
		Product product = pservice.getSingleProduct(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id" + pid));
		product.setBrand(p.getBrand());
		product.setMadeIn(p.getMadeIn());
		product.setName(p.getName());
		product.setPrice(p.getPrice());
		final Product updatedProduct = pservice.saveProduct(product);
		return ResponseEntity.ok().body(updatedProduct);
	}

	@DeleteMapping("products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable(value = "id") Long pid)
			throws ResourceNotFoundException {

		pservice.getSingleProduct(pid)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id" + pid));
		pservice.deleteProduct(pid);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	
	// http://localhost:8085/ims/api/products/search?name=pen drive
	@GetMapping("/products/search")
	public ResponseEntity<?> searchProductsByName(@RequestParam("name") String name) {
		try {
			List<Product> products = pservice.searchProductByName(name);
			if (products.isEmpty()) {
				return new ResponseEntity<>("No products with the given name", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("DB Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
