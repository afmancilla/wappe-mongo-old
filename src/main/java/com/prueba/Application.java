package com.prueba;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.core.env.Environment;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMongoRepositories("com.prueba")

@EnableScheduling
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    CasoService casoService;

    public static void main(String[] args) throws UnknownHostException {



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


//    @Scheduled(fixedRate = 1000*60*1)
//    public void reportCurrentTime() {
//        LOG.info("\n---------------------------------INICIO-------------------------\n\t");
//        casoService.agregate(1,2014);
//        LOG.info("\n---------------------------------FIN-------------------------\n\t");
//    }

}
