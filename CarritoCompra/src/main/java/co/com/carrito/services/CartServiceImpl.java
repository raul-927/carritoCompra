package co.com.carrito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.carrito.domain.Cart;

import co.com.carrito.mybatis.mappers.CartMapper;
import co.com.carrito.mybatis.mappers.ProductCartMapper;

@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private ProductCartMapper productCartMapper;
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public Cart insert(Cart cart) {
		this.cartMapper.insert(cart);
		this.productCartMapper.insert(cart.getProductCart());
		this.productCartMapper.insertCartProductCart(cart.getCartId(), cart.getProductCart());
		return cart;
	}

	@Override
	public Cart update(Cart cart) {
		this.cartMapper.update(cart);
		return cart;
	}

	@Override
	public void delete(Cart cart) {
		this.productCartMapper.deleteCartProductCart(cart.getCartId(), cart.getProductCart());
		this.productCartMapper.delete(cart.getProductCart());
		this.cartMapper.delete(cart);
		
	}

	@Override
	public List<Cart> select(Cart cart) {
		return this.cartMapper.select(cart);
	}

	@Override
	public Cart insertProductCart(Cart cart) {
		this.productCartMapper.insert(cart.getProductCart());
		this.productCartMapper.insertCartProductCart(cart.getCartId(),cart.getProductCart());
		
		return cart;
	}
}
