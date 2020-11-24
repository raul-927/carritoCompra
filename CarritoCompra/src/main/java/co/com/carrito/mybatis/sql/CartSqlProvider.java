package co.com.carrito.mybatis.sql;

import org.apache.ibatis.jdbc.SQL;

import co.com.carrito.domain.Cart;
import co.com.carrito.domain.ProductCart;

public class CartSqlProvider {
	
	public String insert(Cart cart) {
		return new SQL() {{
			INSERT_INTO("cart");
			if(cart.getStatus()!=null) {
				VALUES("status", "'".concat(cart.getStatus().name()).concat("'"));
			}
		}}.toString();
	}
	
	public String update(Cart cart) {
		return new SQL() {{
			UPDATE("cart");
			if(cart.getStatus()!=null) {
				SET("status = " + "'".concat(cart.getStatus().name()).concat("'"));
			}
			WHERE("cart_id = " +String.valueOf(cart.getCartId()));
		}}.toString();
	}
	
	public String delete(Cart cart) {
		return new SQL() {{
			DELETE_FROM("cart");
			WHERE("cart_id = " + String.valueOf(cart.getCartId()));
		}}.toString();
	}
	
	public String select(Cart cart) {
		return new SQL() {{
			SELECT("c.cart_id, c.status");
			SELECT("pc.prod_cart_id, pc.quantity");
			SELECT("p.prod_id, p.nombre, p.sku, p.descripcion");
			FROM("cart c");
			FROM("cart_prod_cart cpc");
			FROM("product_cart pc");
			FROM("product p");
			
			WHERE("c.cart_id = cpc.cart_id");
			WHERE("cpc.prod_cart_id = pc.prod_cart_id");
			WHERE("pc.prod_id = p.prod_id");
			if(cart !=null) {
				if(cart.getCartId() > 0) {
					WHERE("c.cart_id = " + String.valueOf(cart.getCartId()));
				}
				else {
					if(cart.getStatus()!=null) {
						WHERE("c.status = " + "'".concat(cart.getStatus().name()).concat("'"));
					}
					if(cart.getProductCart()!=null) {
						if(cart.getProductCart().size() > 0) {
							for(ProductCart productCart: cart.getProductCart()) {
								if(productCart.getProdCartId() > 0) {
									WHERE("c.prod_cart_id = " + String.valueOf(productCart.getProdCartId()));
								}
								else {
									if(productCart.getProduct()!= null) {
										if(productCart.getProduct().getProdId() > 0) {
											WHERE("p.prod_id = " +String.valueOf(productCart.getProduct().getProdId()));
										}
										else {
											if(productCart.getProduct().getNombre()!=null && productCart.getProduct().getNombre()!="") {
												WHERE("p.nombre = " + "'".concat(productCart.getProduct().getNombre()).concat("'"));
											}
											if(productCart.getProduct().getDescripcion()!=null && productCart.getProduct().getDescripcion()!="") {
												WHERE("p.descripcion = " + "'".concat(productCart.getProduct().getDescripcion()).concat("'"));
											}
											if(productCart.getProduct().getSku()!=null && productCart.getProduct().getSku()!="") {
												WHERE("p.sku = " + "'".concat(productCart.getProduct().getSku()).concat("'"));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}}.toString();
	}

}
