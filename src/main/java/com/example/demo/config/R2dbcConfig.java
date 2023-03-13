package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.client.SSLMode;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    @Value("${postgresql.host}")
    private String host;

    @Value("${postgresql.port}")
    private Integer port;

    @Value("${postgresql.database}")
    private String database;

    @Value("${postgresql.username}")
    private String username;

    @Value("${postgresql.password}")
    private String password;

    @Bean(name="postgresql")
    @Override
    public ConnectionFactory connectionFactory() {
        PostgresqlConnectionConfiguration configuration = PostgresqlConnectionConfiguration.builder()
            .host(host)
            .port(port)
            .database(database)
            .username(username)
            .password(password)
            .sslMode(SSLMode.DISABLE)
            .build();
        return new PostgresqlConnectionFactory(configuration);
    }
    
}
