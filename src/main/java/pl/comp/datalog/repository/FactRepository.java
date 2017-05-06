package pl.comp.datalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.comp.datalog.model.Fact;
import pl.comp.datalog.model.Rule;


/**
 * Created by Damian Ratajczak
 */
public interface FactRepository extends MongoRepository<Fact, String> {

    Fact  findById(String id);

}
