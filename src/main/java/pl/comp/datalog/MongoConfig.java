package pl.comp.datalog;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by Damian Ratajczak
 */
@Configuration
public class MongoConfig {

    public static final String DATABASE_HOST = "localhost";
    public static final String DATABASE_NAME = "datalog";

    public @Bean MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new Mongo(DATABASE_HOST), DATABASE_NAME);
    }
}