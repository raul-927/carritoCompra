package co.com.carrito.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(value="co.com.carrito.mybatis.mappers")
public class MybatisContextConfig{
	
	@Value("${jdbc.driverClassName}")
	private String className;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String passWord;
	
	@Value("${mybatis.mapperLocations}")
	private String relativePath;
	
	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;
	
	@Value("${mybatis.typeHandlersPackage}")
	private String typeHandlersPackage;
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(this.className);
		driverManagerDataSource.setUrl(this.url);
		driverManagerDataSource.setUsername(this.userName);
		driverManagerDataSource.setPassword(this.passWord);
		return driverManagerDataSource;
		
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    Resource[] mapperLocations = resolver.getResources(this.relativePath);
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(this.dataSource());
		sqlSessionFactory.setTypeAliasesPackage(this.typeAliasesPackage);
		sqlSessionFactory.setTypeHandlersPackage(this.typeHandlersPackage);
		sqlSessionFactory.setMapperLocations(mapperLocations);
		
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		SqlSessionTemplate sqlSessionTemplate= new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}
	
}
