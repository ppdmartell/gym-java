/*
This example is based on [1] to create a DataSource programmatically. It is used H2, but [2]
uses MySQL.

Here the DataSource is defined programmatically in its full, but you could add some parameters
here and set the rest by externalizing them in application.properties file.

Quote from [1]: "Then we can specify a few additional ones in the application.properties file:

spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driver-class-name=org.h2.Driver

The properties defined in an external source, such as the above application.properties file, or
via a class annotated with @ConfigurationProperties, will override the ones defined in the
Java API.

With this approach, we'll no longer keep our DataSource configuration settings stored in one
single place.
On the other hand, it allows us to keep compile-time and run-time configuration settings nicely
separated from each other.
This is really good, as it allows us to easily set a configuration binding point. That way we
can include different DataSource settings from other sources, without having to refactor our
bean factory methods."

Resources:
[1] https://www.baeldung.com/spring-boot-configure-data-source-programmatic
[2] https://www.youtube.com/watch?v=7vjlCcmwGiU
[3] https://www.youtube.com/watch?v=iDogrHEo4x0
*/

package com.datasource.example.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:test");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
}
