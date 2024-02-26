package com.feedback.feedbackforum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "Cluster0"; // Replace with your MongoDB Atlas database name
    }

    @Override
    public MongoClient mongoClient() {
        String connectionString = "mongodb+srv://sandeep24:sandeep24@cluster0.w0zqkcl.mongodb.net/?retryWrites=true&w=majority";
        return MongoClients.create(connectionString);
        // Replace <username>, <password>, <cluster>, and <database> with your MongoDB Atlas credentials and database details
    }

    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory databaseFactory) {
        return new MongoTransactionManager(databaseFactory);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}
