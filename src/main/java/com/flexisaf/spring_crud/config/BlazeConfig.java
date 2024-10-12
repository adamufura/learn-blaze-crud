package com.flexisaf.spring_crud.config;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViews;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import com.flexisaf.spring_crud.view.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration

public class BlazeConfig {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy(false)
    public CriteriaBuilderFactory createCriteriaBuilderFactory() {
        CriteriaBuilderConfiguration config = Criteria.getDefault();
        return config.createCriteriaBuilderFactory(entityManagerFactory);
    }

    // Use the actual Blaze-Persistence EntityViews API to configure entity views
    @Bean
    public EntityViewConfiguration entityViewConfiguration() {
        // Automatically scan for entity views in the specified package
        EntityViewConfiguration entityViewConfiguration = EntityViews.createDefaultConfiguration();

        // You can register entity views manually like this if needed:
         entityViewConfiguration.addEntityView(UserView.class);
        entityViewConfiguration.addEntityView(PostView.class);
        entityViewConfiguration.addEntityView(CommentView.class);
        entityViewConfiguration.addEntityView(UserSummaryView.class);
        entityViewConfiguration.addEntityView(PostSummaryView.class);
        entityViewConfiguration.addEntityView(CommentSummaryView.class);

        return entityViewConfiguration;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy(false)
    public EntityViewManager createEntityViewManager(CriteriaBuilderFactory cbf, EntityViewConfiguration entityViewConfiguration) {
        return entityViewConfiguration.createEntityViewManager(cbf);
    }
}
