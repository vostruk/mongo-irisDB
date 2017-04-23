package pl.comp.datalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.comp.datalog.model.Rule;


/**
 * Created by Damian Ratajczak
 */
public interface RuleRepository extends MongoRepository<Rule, String> {

    Rule findById(String id);
}
