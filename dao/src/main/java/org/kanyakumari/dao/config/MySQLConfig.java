package org.kanyakumari.dao.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.kanyakumari.dao.constants.DBConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = DBConstants.MySQLConfig.Beans.ENTITY_MANAGER_FACTORY_REF,
        transactionManagerRef = DBConstants.MySQLConfig.Beans.TRANSACTION_MANAGER_REF,
        basePackages = {
                "org.kanyakumari.dao.repository",
        }
)
public class MySQLConfig {

    @Value("${spring.datasource.hikari.dialect}")
    private String dialect;

    @Primary
    @Bean(name = DBConstants.MySQLConfig.Beans.CONFIG)
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean(name = DBConstants.MySQLConfig.Beans.DATA_SOURCE)
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Primary
    @Bean(name = DBConstants.MySQLConfig.Beans.TRANSACTION_MANAGER_REF)
    public PlatformTransactionManager transactionManager(
            @Qualifier(DBConstants.MySQLConfig.Beans.ENTITY_MANAGER_FACTORY_REF) EntityManagerFactory bulletinEntityManagerFactory) {
        return new JpaTransactionManager(bulletinEntityManagerFactory);
    }

//    @Bean
//    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
//    }

    @Primary
    @Bean(name = DBConstants.MySQLConfig.Beans.ENTITY_MANAGER_FACTORY_REF)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier(DBConstants.MySQLConfig.Beans.DATA_SOURCE) DataSource dataSource) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("org.kanyakumari.dao.entity")
                .persistenceUnit(DBConstants.MySQLConfig.PERSISTENCE_UNIT).properties(properties)
                .build();
    }
}
