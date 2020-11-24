package co.com.carrito.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import co.com.carrito.domain.Product;
import co.com.carrito.mybatis.sql.ProductSqlProvider;

public interface ProductMapper {
	
	@InsertProvider(type = ProductSqlProvider.class, method ="insert")
	@Options(useGeneratedKeys=true, keyProperty="prodId", keyColumn = "prod_id") 
	void insert(@Param("product")Product product);
	
	@UpdateProvider(type = ProductSqlProvider.class, method ="update")
	void update(@Param("product") Product product);
	
	@DeleteProvider(type = ProductSqlProvider.class, method ="delete")
	void delete(@Param("product") Product product);
	
	@SelectProvider(type = ProductSqlProvider.class, method ="select")
	@ResultMap("co.com.carrito.mybatis.mappers.ProductMapper.ProductResult")
	List<Product>select(@Param("product") Product product);
}
