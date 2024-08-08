package com.LMS.userManagement.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .baselineOnMigrate(true)
                .baselineVersion("0")
                // .baselineDescription("init")
                .locations("db/migration/default")
                .dataSource(dataSource)
                .schemas("public")
                .load();
        flyway.migrate();
        return flyway;
    }

}
