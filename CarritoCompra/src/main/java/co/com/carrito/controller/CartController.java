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

import co.com.carrito.domain.Cart;

import co.com.carrito.exceptions.ResourceNotFoundException;
import co.com.carrito.services.CartService;


@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	@RequestMapping(
			value ="/cart", method =RequestMethod.POST,
			consumes ={MediaType.APPLICATION_JSON_VALUE},
			produces ={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<?>insert(@RequestBody @Valid Cart cart, BindingResult bindingResult){
		HttpHeaders headers = new HttpHeaders();
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<List<FieldError>>(bindingResult.getFieldErrors(), headers,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Cart cartResult = this.cartService.insert(cart);
		
		return new ResponseEntity<Cart>(cartResult, headers, HttpStatus.OK);
	}
	
	@RequestMapping(
			value ="/cart", method =RequestMethod.PUT,
			consumes ={MediaType.APPLICATION_JSON_VALUE},
			produces ={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<?>update(@RequestBody Cart cart, BindingResult bindingResult){
		HttpHeaders headers = new HttpHeaders();
		verificarCart(cart);
		Cart productResult = this.cartService.update(cart);
		
		return new ResponseEntity<Cart>(productResult, headers, HttpStatus.OK);
	}
	
	@RequestMapping(
			value ="/cart", method =RequestMethod.DELETE,
			consumes ={MediaType.APPLICATION_JSON_VALUE},
			produces ={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<?>delete(@RequestBody Cart cart){
		HttpHeaders headers = new HttpHeaders();
		verificarCart(cart);
		this.cartService.delete(cart);
		
		return new ResponseEntity<Cart>(cart, headers, HttpStatus.OK);
	}
	
	@RequestMapping(
			value ="/carts", method =RequestMethod.POST,
			consumes ={MediaType.APPLICATION_JSON_VALUE},
			produces ={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<?>showProduct(@RequestBody Cart cart){
		HttpHeaders headers = new HttpHeaders();
		verificarCart(cart);
		List<Cart> cartResult = this.cartService.select(cart);
		
		return new ResponseEntity<List<Cart>>(cartResult, headers, HttpStatus.OK);
	}
	
	private void verificarCart(Cart cart) {
		List<Cart> cartResult = this.cartService.select(cart);
		if(cartResult.size() == 0) {
			if(cart != null) {
				if(cart.getCartId() > 0) { 
					throw new ResourceNotFoundException("Carro con id: " +cart.getCartId()+"  no encontrado");
				}
				else {
					throw new ResourceNotFoundException("Carro no encontrado");
				}
			}
			else {
				throw new ResourceNotFoundException("No hay carros ingresados");
			}
		}
	}
}