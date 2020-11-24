package co.com.carrito.mybatis.sql;

import java.util.List;
import org.apache.ibatis.jdbc.SQL;
import co.com.carrito.domain.ProductCart;

public class ProductCartSqlProvider {
	
	public String insert(List<ProductCart> productCartList) {
		return new SQL() {{
			INSERT_INTO("product_cart");
			INTO_COLUMNS("prod_id","quantity");
			for(ProductCart productCart: productCartList) {
				INTO_VALUES(String.valueOf(productCart.getProduct().getProdId()), String.valueOf(productCart.getQuantity()));
				ADD_ROW();
			}
		}}.toString();
	}
	
	public String insertCartProductCart(int cartId, List<ProductCart> productCartList) {
		return new SQL() {{
			INSERT_INTO("cart_prod_cart");
			INTO_COLUMNS("cart_id, prod_cart_id");
			for(ProductCart productCart: productCartList) {
				INTO_VALUES(String.valueOf(cartId), String.valueOf(productCart.getProdCartId()));
				ADD_ROW();
			}
		}}.toString();
	}
	public String deleteCartProductCart(int cartId, List<ProductCart> productCartList) {
		return new SQL() {{
			DELETE_FROM("cart_product_cart");
			WHERE("cart_id = " +String.valueOf(cartId));
			for(ProductCart productCart: productCartList) {
				if(productCart.getProdCartId() > 0) {
					WHERE("prod_cart_id = " +String.valueOf(productCart.getProdCartId()));
				}
			}
		}}.toString();
	}
	public String delete(List<ProductCart> productCartList) {
		return new SQL() {{
			DELETE_FROM("product_cart");
			for(ProductCart productCart: productCartList) {
				if(productCart.getProdCartId() > 0) {
					WHERE("prod_cart_id = " +String.valueOf(productCart.getProdCartId()));
				}
			}
		}}.toString();
	}
}