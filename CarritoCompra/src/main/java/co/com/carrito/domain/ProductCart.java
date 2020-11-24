package co.com.carrito.domain;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class ProductCart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int	prodCartId;
	
	@NotNull(message = "Producto no puede ser null")
	private Product  product;
	
	@Min(value =1,  message ="Cantidad debe ser mínimo 1")
	private int	quantity;
	
	public int getProdCartId() {
		return prodCartId;
	}
	public void setProdCartId(int prodCartId) {
		this.prodCartId = prodCartId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
