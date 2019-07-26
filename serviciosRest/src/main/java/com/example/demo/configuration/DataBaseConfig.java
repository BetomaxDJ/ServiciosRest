package com.example.demo.configuration;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataBaseConfig {
	
	@Autowired
    private Environment environment;
	
	@Bean(name = "dsMaster")
    @Primary
    public DataSource primaryDataSource() {
    	
    	
        org.apache.commons.dbcp2.BasicDataSource ds = new org.apache.commons.dbcp2.BasicDataSource();
        ds.setUrl(environment.getProperty("spring.datasource.url"));
        ds.setUsername(environment.getProperty("spring.datasource.username"));
        ds.setPassword(environment.getProperty("spring.datasource.password"));
        //ds.setInitialSize(Integer.valueOf(environment.getProperty("spring.datasource.primary.dbcp2.initial-size")));
        //ds.setMaxTotal(Integer.valueOf(environment.getProperty("spring.datasource.primary.dbcp2.max-total")));
        //ds.setPoolPreparedStatements(Boolean.valueOf(environment.getProperty("spring.datasource.primary.dbcp2.pool-prepared-statements")));
        ds.setDriverClassName(environment.getProperty("spring.datasource.primary.driver-class-name"));

        return ds;
    }

    @Bean(name = "jdbcMaster")
    public JdbcTemplate jdbcPrimaryTemplate(@Qualifier(value = "dsMaster") DataSource primaryDataSource) {
        return new JdbcTemplate(primaryDataSource);
    }
	/*
	@Bean("dsMaster")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource pricingDatasource() {
	      return DataSourceBuilder.create().build();
	}
	
    @Bean(name = "jdbcMaster")
    @Autowired
    public JdbcTemplate masterJdbcTemplate(@Qualifier("dsMaster") DataSource dsMaster) {
        return new JdbcTemplate(dsMaster);
    }
    */
}