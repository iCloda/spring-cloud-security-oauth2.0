package com.orieange.auth;

import com.orieange.repo.extend.druid.msql.DruidConfiguration;
import com.orieange.repo.extend.druid.msql.support.factory.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@Import(DruidConfiguration.class)
@EnableJpaRepositories(basePackages = "com.orieange.auth.dao")
@ImportResource("classpath:dubbo-provider.xml")
public class AuthProviderLauncher {

    public static void main(String[] args) {
        SpringApplication.run(AuthProviderLauncher.class, args);
    }

}
