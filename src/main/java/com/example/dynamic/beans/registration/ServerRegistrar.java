package com.example.dynamic.beans.registration;

import java.util.Map;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

public class ServerRegistrar implements BeanDefinitionRegistryPostProcessor {

    private static final String PROPERTY_NAME = "servers";

    private final Map<String, Server> servers;

    public ServerRegistrar(Environment environment) {
        this.servers = Binder.get(environment)
            .bind(PROPERTY_NAME, Bindable.mapOf(String.class, Server.class))
            .orElseThrow(() -> new BeanInstantiationException(ServerRegistrar.class,
                "Property '" + PROPERTY_NAME + "' not found in configuration"));
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.servers.forEach((beanName, server) -> {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(Server.class);
            beanDefinition.setInstanceSupplier(() -> server);
            registry.registerBeanDefinition(beanName, beanDefinition);
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // Do nothing
    }
}
