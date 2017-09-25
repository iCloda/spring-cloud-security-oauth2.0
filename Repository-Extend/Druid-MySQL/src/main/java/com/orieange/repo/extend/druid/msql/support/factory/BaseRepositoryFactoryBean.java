package com.orieange.repo.extend.druid.msql.support.factory;

import com.orieange.repo.extend.druid.msql.support.impl.BasicRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, ID> {

    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new DefaultRepositoryFactory(em);
    }

    private static class DefaultRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager em;

        public DefaultRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @Override
        protected SimpleJpaRepository<T, ID> getTargetRepository(RepositoryInformation information,
                                                                 EntityManager entityManager) {
            JpaEntityInformation<T, ID> entityInformation = (JpaEntityInformation<T, ID>) getEntityInformation(
                    information.getDomainType());
            return new BasicRepository<T, ID>(entityInformation, entityManager); // custom
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BasicRepository.class;
        }
    }
}
