package com.mb.spring.alias;

import java.lang.annotation.Annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor scans the beans and looks up for @Alias definition on them. In case alias is found it will
 * be registered in the bean factory.
 *
 * @author Michael Bolgar
 * @version Jan 5, 2014
 */
// @Service
public class AliasBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            processBean(beanFactory, beanName);
        }
    }

    private static void processBean(ConfigurableListableBeanFactory beanFactory, String beanName) {
        BeanDefinition definition = beanFactory.getBeanDefinition(beanName);
        if (definition.getBeanClassName() == null) {
            return;
        }
        try {
            Class<?> clazz = Class.forName(definition.getBeanClassName());
            Annotation annotation = clazz.getAnnotation(Alias.class);
            if (annotation != null) {
                processAlias(beanFactory, (Alias) annotation, beanName);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void processAlias(ConfigurableListableBeanFactory beanFactory, Alias aliasAnnotation, String beanName) {
        for (String alias : aliasAnnotation.value()) {
            beanFactory.registerAlias(beanName, alias);
        }
    }

}
