package com.prueba;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mongodb.MongoClient;

@Configuration
public class MongoDbSettings {


    @Value("${spring.data.mongodb.uri}")
    private String url;

//    @Bean
//    public MongoClientOptions mongoOptions() {
//
//        int minuto = 90000000*10;
//
//        return MongoClientOptions.builder().socketKeepAlive(false)
//                .build();
//    }


    @Bean
    public MongoClient mongoClient() {

        MongoClientURI uri = new MongoClientURI(url);
        MongoClient mongoClient = new MongoClient(uri);

        System.out.println("getConnectTimeout...........: "+uri.getOptions().getConnectTimeout());
        System.out.println(uri.getOptions().toString());

        return mongoClient;
    }
}
