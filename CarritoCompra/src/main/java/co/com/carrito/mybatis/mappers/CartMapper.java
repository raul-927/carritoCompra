package co.com.carrito.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import co.com.carrito.domain.Cart;
import co.com.carrito.mybatis.sql.CartSqlProvider;

public interface CartMapper {
	
	@InsertProvider(type = CartSqlProvider.class, method ="insert")
	@Options(useGeneratedKeys=true, keyProperty="cartId", keyColumn = "cart_id") 
	void insert(@Param("cart")Cart cart);
	
	@UpdateProvider(type = CartSqlProvider.class, method ="update")
	void update(@Param("cart")Cart cart);
	
	@DeleteProvider(type = CartSqlProvider.class, method ="delete")
	void delete(@Param("cart")Cart cart);
	
	@SelectProvider(type = CartSqlProvider.class, method ="select")
	@ResultMap("co.com.carrito.mybatis.mappers.CartMapper.CartResult")
	List<Cart>select(@Param("cart")Cart cart);
}
