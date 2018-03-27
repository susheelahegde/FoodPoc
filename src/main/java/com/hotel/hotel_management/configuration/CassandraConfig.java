package com.hotel.hotel_management.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;

@Configuration
@PropertySource(value = { "classpath:application.properties" })

//public class CassandraConfig {
//
//}

public class CassandraConfig extends AbstractCassandraConfiguration {
    private static final String KEYSPACE = "cassandra.keyspace";
    private static final String CONTACTPOINTS = "cassandra.contactpoints";
    private static final String PORT = "cassandra.port";

    @Autowired
    private Environment environment;

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(environment.getProperty(CONTACTPOINTS));
        cluster.setPort(Integer.parseInt(environment.getProperty(PORT)));
        return cluster;
    }

    @Override
    protected String getKeyspaceName() {
        return "hotel_management";
    }
}
