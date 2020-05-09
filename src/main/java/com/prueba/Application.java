package com.prueba;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.core.env.Environment;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMongoRepositories("com.prueba")
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws UnknownHostException {

        System.setProperty("server.tomcat.max-threads","200");
        System.setProperty("server.connection-timeout","60000");

        Environment env = SpringApplication.run(Application.class, args).getEnvironment();

        LOG.info("\n----------------------------------------------------------\n\t"
                        + "Application '{}' is running! Access URLs:\n\t"
                        + "Local: \t\thttp://localhost:{}\n\t"
                        + "External: \thttp://{}:{}\n\t"
                        + "DB: \t{}\n\t"
                        + "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("spring.data.mongodb.database"),
                env.getActiveProfiles());

        LOG.info("\n----------------------------------------------------------\n\t"
                        + "Config Server: \t{}\n----------------------------------------------------------",
                env.getProperty("configserver.status") == null ? "Not found or not setup for this application" : env.getProperty("configserver.status"));

    }



}
