package pl.training.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Arkadiusz Parzych
 */
@Configuration
@ComponentScan("pl.training.jpa")
@EnableTransactionManagement
public class DatabaseConfig {

  @Bean
  public DataSource db() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(db());
    em.setPackagesToScan("pl.training.jpa");
    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    em.setJpaProperties(additionalProperties());

    return em;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }

  Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "create");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    properties.setProperty("hibernate.show_sql", "true");
    properties.setProperty("format_sql", "true");
    properties.setProperty("use_sql_comments", "true");
    properties.setProperty("cache.use_second_level_cache", "true");
    properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.testing.cache.CachingRegionFactory");
    return properties;
  }
}
