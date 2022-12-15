package com.farmmonitorbackend.controllers;

import com.farmmonitorbackend.configs.MongoConfig;
import com.farmmonitorbackend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import(MongoConfig.class)
public class UserControllerTest {
    @Autowired
    UserRepository repository;

    @TestConfiguration
    static class MongoMapKeyDotReplacementConfiguration {
        @Bean
        public LocalValidatorFactoryBean localValidatorFactoryBean() {
            return new LocalValidatorFactoryBean();
        }
    }

    @Test
    public void testUserCreateWhenGivenDuplicateValueShouldReturnDuplicateKeyException() {
        User a = new User();
        a.setPassword("password1");
        a.setEmail("user@test.com");

        repository.insert(a);

        User b = new User();
        //b.setPassword("password2");
        b.setEmail("user@test.com");
    }
}
