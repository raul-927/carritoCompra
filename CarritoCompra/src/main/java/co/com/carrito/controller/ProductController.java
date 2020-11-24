package co.com.carrito.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.carrito.domain.Product;
import co.com.carrito.exceptions.ResourceNotFoundException;
import co.com.carrito.services.ProductService;


@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(
			value ="/product", method =RequestMethod.POST,
			consumes ={MediaType.APPLICATION_JSON_VALUE},
			produces ={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<?>insert(@RequestBody @Valid Product product, BindingResult bindingResult){
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<List<FieldError>>(bindingResult.getFieldErrors(), headers,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Product productResult = this.productService.insert(product);
				return new ResponseEntity<Product>(productResult, headers, HttpStatus.OK);
	}
	
	@RequestMapping(
			value ="/product", method =RequestMethod.PUT,
			consumes ={MediaType.APPLICATION_JSON_VALUE},
			produces ={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<?>update(@RequestBody @Valid Product product, BindingResult bindingResult){
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<List<FieldError>>(bindingResult.getFieldErrors(), headers,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		verificarProduct(product);
		Product productResult = this.productService.update(product);
				return new ResponseEntity<Product>(productResult, headers, HttpStatus.OK);
	}
	
	@RequestMapping(
			value ="/product", method =RequestMethod.DELETE,
			consumes ={MediaType.APPLICATION_JSON_VALUE},
			produces ={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<?>delete(Product product){
		HttpHeaders headers = new HttpHeaders();
		verificarProduct(product);
		this.productService.delete(product);
		return new ResponseEntity<Product>(product, headers, HttpStatus.OK);
	}
	
	@RequestMapping(
			value ="/productSearch", method =RequestMethod.POST,
			consumes ={MediaType.APPLICATION_JSON_VALUE},
			produces ={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<?>showProduct(@RequestBody Product product){
		HttpHeaders headers = new HttpHeaders();
		verificarProduct(product);
		List<Product> productResult = this.productService.select(product);
		return new ResponseEntity<List<Product>>(productResult, headers, HttpStatus.OK);
	}
	
	private void verificarProduct(Product product) {
		
		List<Product> productResult = this.productService.select(product);
		if(productResult.size() == 0) {
			if(product != null) {
				if(product.getProdId() > 0) { 
					throw new ResourceNotFoundException("Producto con id: " +product.getProdId()+"  no encontrado");
				}
				else {
					throw new ResourceNotFoundException("Producto no encontrado");
				}
			}
			else {
				throw new ResourceNotFoundException("No hay productos ingresados");
			}
		}
	}
}