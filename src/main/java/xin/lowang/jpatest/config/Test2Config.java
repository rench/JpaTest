package xin.lowang.jpatest.config;

import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "test2EntityManagerFactory",
  transactionManagerRef = "test2TransactionManager",
  basePackages = {"xin.lowang.jpatest.test2"}
)
public class Test2Config {
  @Resource(name = "test2ds")
  private DataSource dataSource;

  @Autowired private JpaProperties jpaProperties;

  @Bean(name = "test2EntityManager")
  public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
    return mytestEntityManagerFactory(builder).getObject().createEntityManager();
  }

  @Bean(name = "test2EntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean mytestEntityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(dataSource)
        .properties(getVendorProperties(dataSource))
        .packages("xin.lowang.jpatest")
        .persistenceUnit("test2")
        .build();
  }

  private Map<String, String> getVendorProperties(DataSource dataSource) {
    return jpaProperties.getHibernateProperties(dataSource);
  }

  @Bean(name = "test2TransactionManager")
  public PlatformTransactionManager mytestTransactionManager(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(mytestEntityManagerFactory(builder).getObject());
  }
}
