package co.com.carrito.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import co.com.carrito.enumerador.StatusEnum;

public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int	cartId;
	
	@NotNull(message = "Satus no puede ser null")
	private StatusEnum	status;
	
	@NotNull(message = "Lista de productos no puede ser null")
	private List<ProductCart> productCart;
	
	
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public List<ProductCart> getProductCart() {
		return productCart;
	}
	public void setProductCart(List<ProductCart> productCart) {
		this.productCart = productCart;
	}
}
