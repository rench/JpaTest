package xin.lowang.jpatest.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
  /**
   * 第一数据源
   *
   * @return
   */
  @Bean("test1ds")
  @Qualifier("test1ds")
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource test1() {
    return DataSourceBuilder.create().build();
  }
  /**
   * 第二数据源
   *
   * @return
   */
  @Bean("test2ds")
  @Qualifier("test2ds")
  @ConfigurationProperties(prefix = "spring.datasource2")
  public DataSource test2() {
    return DataSourceBuilder.create().build();
  }
}
