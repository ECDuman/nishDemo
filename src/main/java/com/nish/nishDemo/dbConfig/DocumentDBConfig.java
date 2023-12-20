package com.nish.nishDemo.dbConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "documentsEntityManagerFactory",
						transactionManagerRef = "documentsTransactionManager",
						basePackages= {"com.nish.nishDemo.secondary.repository"})
public class DocumentDBConfig {

	@Bean(name="documentsDatasource")
	@ConfigurationProperties(prefix = "nish.documents.datasource")
	public DataSource dataSource(){
	    return DataSourceBuilder.create().build();
	}

	@Bean(name="documentsEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
	        EntityManagerFactoryBuilder builder,
	        @Qualifier("documentsDatasource") DataSource dataSource
	)
	{
	    return builder 
	            .dataSource(dataSource)
	            .packages("com.nish.nishDemo.model")
	            .persistenceUnit("Documents")
	            .build();
	}	

	@Bean(name="documentsTransactionManager")
	public PlatformTransactionManager documentsTransactionManager(
	        @Qualifier("documentsEntityManagerFactory") EntityManagerFactory entityManagerFactory
	)
	{
	    return new JpaTransactionManager(entityManagerFactory);
	}

}