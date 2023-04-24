package com.example.demowebjms.config;

import org.apache.activemq.artemis.jms.client.ActiveMQDestination;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        jndiTemplate.setEnvironment(createJndiProperties());
        return jndiTemplate.lookup("jms/ConnectionFactory", ConnectionFactory.class);
    }

    private Properties createJndiProperties() {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        props.put(Context.PROVIDER_URL, "t3://localhost:7001");
//        props.put(Context.SECURITY_PRINCIPAL, "weblogic");
//        props.put(Context.SECURITY_CREDENTIALS, "Welcome1");
        return props;
    }

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> weblogicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    JndiTemplate jndiTemplate() {
        return new JndiTemplate(createJndiProperties());
    }


    @Bean
    JndiDestinationResolver jndiDestinationResolver() {
        JndiDestinationResolver jndiDestinationResolver = new JndiDestinationResolver();
        jndiDestinationResolver.setJndiTemplate(jndiTemplate());
        jndiDestinationResolver.setCache(true);
        return jndiDestinationResolver;
    }
}
