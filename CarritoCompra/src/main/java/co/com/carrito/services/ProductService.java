package co.com.carrito.services;

import java.util.List;

import co.com.carrito.domain.Product;

public interface ProductService {
	Product insert(Product product);
	Product update(Product product);
	void delete(Product product);
	List<Product>select(Product product);

}
