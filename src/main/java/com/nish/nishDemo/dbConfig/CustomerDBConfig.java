package com.nish.nishDemo.dbConfig;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
						transactionManagerRef = "transactionManager",
						basePackages= {"com.nish.nishDemo.primary.repository"})
public class CustomerDBConfig {
	@Primary
	@Bean(name="datasource")
	@ConfigurationProperties(prefix = "nish.customers.datasource")
	public DataSource dataSource(){
	    return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("datasource") DataSource dataSource){
	    return builder
	            .dataSource(dataSource)
	            .packages("com.nish.nishDemo.model")
	            .persistenceUnit("Customers")
	            .build();
	}	

	@Primary
	@Bean(name="transactionManager")
	public PlatformTransactionManager customersTransactionManager(
	        @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
	)
	{
	    return new JpaTransactionManager(entityManagerFactory);
	}
}