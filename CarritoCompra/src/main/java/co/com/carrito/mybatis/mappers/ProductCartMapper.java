package co.com.carrito.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import co.com.carrito.domain.ProductCart;
import co.com.carrito.mybatis.sql.ProductCartSqlProvider;

public interface ProductCartMapper {
	
	@InsertProvider(type = ProductCartSqlProvider.class, method ="insert")
	@Options(useGeneratedKeys=true, keyProperty="prodCartId", keyColumn = "prod_cart_id") 
	void insert(@Param("productCartList")List<ProductCart> productCartList);
	
	@InsertProvider(type = ProductCartSqlProvider.class, method ="insertCartProductCart")
	@Options(useGeneratedKeys=true, keyColumn = "pcp_id") 
	void insertCartProductCart( @Param("cartId")int cartId, @Param("productCartList")List<ProductCart> productCartList);
	
	@DeleteProvider(type = ProductCartSqlProvider.class, method ="delete")
	void delete(@Param("productCartList")List<ProductCart> productCartList);
	
	@DeleteProvider(type = ProductCartSqlProvider.class, method ="deleteCartProductCart")
	void deleteCartProductCart(@Param("cartId")int cartId, @Param("productCartList")List<ProductCart> productCartList);
	
	//@SelectProvider(type = ProductCartSqlProvider.class, method ="select")
	//@ResultMap("co.com.carrito.mybatis.mappers.ProductCartMapper.ProductCartResult")
	//List<ProductCart>select(@Param("productCart")ProductCart productCart);
}
