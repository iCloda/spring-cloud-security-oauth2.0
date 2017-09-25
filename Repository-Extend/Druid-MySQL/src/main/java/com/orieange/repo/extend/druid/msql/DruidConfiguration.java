package com.orieange.repo.extend.druid.msql;

import com.alibaba.druid.pool.DruidDataSource;
import com.orieange.repo.extend.druid.msql.support.factory.BaseRepositoryFactoryBean;
import com.orieange.repo.extend.druid.msql.support.impl.BasicRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
@EnableConfigurationProperties(DruidProperties.class)
@EnableTransactionManagement
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class,entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
public class DruidConfiguration {

    protected <T> T createDataSource(DataSourceProperties properties,
                                     Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties, DruidProperties druidProperties) {
        DruidDataSource dataSource = createDataSource(dataSourceProperties, DruidDataSource.class);
        dataSource.setInitialSize(druidProperties.getInitialSize());
        dataSource.setMinIdle(druidProperties.getMinIdle());
        dataSource.setMaxActive(druidProperties.getMaxActive());
        dataSource.setMaxWait(druidProperties.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidProperties.getValidationQuery());
        dataSource.setTestWhileIdle(druidProperties.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(druidProperties.isTestOnReturn());
        dataSource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            dataSource.setFilters(druidProperties.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setConnectionProperties(druidProperties.getConnectionProperties());
        dataSource.setUseGlobalDataSourceStat(druidProperties.isUseGlobalDataSourceStat());
        return dataSource;
    }

    @Bean(name = "entityManager")
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder, JpaProperties jpaProperties, DruidProperties druidProperties, DataSource dataSource) {
        return entityManagerFactory(builder, jpaProperties, druidProperties, dataSource).getObject().createEntityManager();
    }


    @Bean(name = "entityManagerFactory")
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, JpaProperties jpaProperties, DruidProperties druidProperties, DataSource dataSource) {
        EntityManagerFactoryBuilder.Builder b = builder.dataSource(dataSource);
        b.properties(jpaProperties.getHibernateProperties(dataSource));
        if (druidProperties.getPackagesToScan() != null && druidProperties.getPackagesToScan().length() > 0) {
            b.packages(druidProperties.getPackagesToScan().split(","));
        }
        b.persistenceUnit("persistenceUnit");
        return b.build();
    }

    @Bean(name = "transactionManager")
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder, JpaProperties jpaProperties, DruidProperties druidProperties, DataSource dataSource) {
        return new JpaTransactionManager(entityManagerFactory(builder, jpaProperties, druidProperties, dataSource).getObject());
    }

}
