package co.com.carrito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.carrito.domain.Product;
import co.com.carrito.exceptions.ResourceNotFoundException;
import co.com.carrito.mybatis.mappers.ProductMapper;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public Product insert(Product product) {
		this.productMapper.insert(product);
		return product;
	}

	@Override
	public Product update(Product product) {
		this.productMapper.update(product);
		return product;
	}

	@Override
	public void delete(Product product) {
		this.productMapper.delete(product);

	}

	@Override
	public List<Product> select(Product product){
		return this.productMapper.select(product);
	}
}
