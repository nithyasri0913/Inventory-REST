package com.wellsfargo.training.ims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.ims.model.Product;
import com.wellsfargo.training.ims.repository.ProductRepository;

import jakarta.transaction.Transactional;

/*
 * A service layer is an additional layer in an MVC application that 
 * mediates communication between a controller and repository layer. 
 * The service layer contains business logic. 
 * In particular, it contains validation logic. */

/* @Service annotation allows developers to add business functionalities.
 *  @Transactional annotation allows to manage Database transactions efficiently */

@Service
@Transactional
public class ProductService {
	/*
	 * @Autowired - marks a constructor, field, or setter method to be autowired by
	 * Spring dependency injection.
	 */

	@Autowired
	private ProductRepository prepo;

	public Product saveProduct(Product p) {
		return prepo.save(p); // invoke save() method predefined in JPA
	}

	public List<Product> listAll() {
		return prepo.findAll(); // invoke findAll() method predefined in JPA
	}

	// if not primary key filed, have to write custom method. Otherwise, available
	// in JpRepo
	public Optional<Product> getSingleProduct(Long id) {
		return prepo.findById(id);
	}

	public void deleteProduct(Long id) {
		prepo.deleteById(id);
	}

	public List<Product> searchProductByName(String name) {
		return prepo.findProductByNameContainingIgnoreCase(name);
	}
}
