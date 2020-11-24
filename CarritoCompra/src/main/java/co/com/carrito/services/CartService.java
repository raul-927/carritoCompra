package co.com.carrito.services;

import java.util.List;

import co.com.carrito.domain.Cart;

public interface CartService {
	
	Cart insert(Cart cart);
	Cart update(Cart cart);
	void delete(Cart cart);
	List<Cart>select(Cart cart);
	Cart insertProductCart(Cart cart);
	void deleteProductCart(Cart cart);
	

}
