package book.manager.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@ComponentScans({
        @ComponentScan("book.manager.service")
})
@MapperScan("book.manager.mapper")
@Configuration
@EnableTransactionManagement
public class RootConfiguration {

    @Bean
    public DataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(readConfig("book.db.url", "BOOK_DB_URL", "jdbc:mysql://127.0.0.1:3306/bookmanage"));
        dataSource.setDriverClassName(readConfig("book.db.driver", "BOOK_DB_DRIVER", "com.mysql.cj.jdbc.Driver"));
        dataSource.setUsername(readConfig("book.db.username", "BOOK_DB_USERNAME", "bookmanager"));
        dataSource.setPassword(readRequiredConfig("book.db.password", "BOOK_DB_PASSWORD"));
        return dataSource;
    }

    private String readConfig(String propertyKey, String envKey, String defaultValue) {
        String propertyValue = System.getProperty(propertyKey);
        if (propertyValue != null && !propertyValue.trim().isEmpty()) {
            return propertyValue.trim();
        }
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue.trim();
        }
        return defaultValue;
    }

    private String readRequiredConfig(String propertyKey, String envKey) {
        String value = readConfig(propertyKey, envKey, null);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("Missing required database config: set " + envKey + " or -D" + propertyKey);
        }
        return value;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }

    @Bean
    public TransactionManager transactionManager(@Autowired DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
