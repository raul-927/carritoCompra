package co.com.carrito.mybatis.sql;

import org.apache.ibatis.jdbc.SQL;

import co.com.carrito.domain.Product;

public class ProductSqlProvider {
	
	public String insert(Product product) {
		return new SQL() {{
			INSERT_INTO("product");
			VALUES("nombre", "'".concat(product.getNombre()).concat("'"));
			VALUES("descripcion", "'".concat(product.getDescripcion()).concat("'"));
			VALUES("sku", "'".concat(product.getSku()).concat("'"));
		}}.toString();
	}
	
	public String update(Product product) {
		return new SQL() {{
			UPDATE("product");
			SET("nombre = "+ "'".concat(product.getNombre()).concat("'"));
			SET("descripcion = "+ "'".concat(product.getDescripcion()).concat("'"));
			SET("sku = "+ "'".concat(product.getSku()).concat("'"));
			WHERE("prod_id = " + String.valueOf(product.getProdId()));
		}}.toString();
	}
	
	public String delete(Product product) {
		return new SQL() {{
			DELETE_FROM("product");
			WHERE("prod_id = " + String.valueOf(product.getProdId()));
		}}.toString();
	}
	
	public String select(Product product) {
		return new SQL() {{
			SELECT("prod_id, nombre, descripcion, sku");
			FROM("product");
			if(product !=null) {
				if(product.getProdId() > 0) {
					WHERE("prod_id = " + String.valueOf(product.getProdId()));
				}
				else {
					if(product.getDescripcion()!=null && product.getDescripcion()!="") {
						WHERE("descripcion = " + "'".concat(product.getDescripcion()).concat("'"));
					}
					if(product.getNombre()!=null && product.getNombre()!="") {
						WHERE("nombre = " + "'".concat(product.getNombre()).concat("'"));
					}
					if(product.getSku()!=null && product.getSku()!="") {
						WHERE("sku = " + "'".concat(product.getSku()).concat("'"));
					}
				}
			}
		}}.toString();
	}
}
